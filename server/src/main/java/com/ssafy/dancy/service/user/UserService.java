package com.ssafy.dancy.service.user;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.user.*;
import com.ssafy.dancy.exception.verify.EmailNotVerifiedException;
import com.ssafy.dancy.message.request.auth.ChangePasswordRequest;
import com.ssafy.dancy.message.request.auth.LoginUserRequest;
import com.ssafy.dancy.message.request.user.ChangeProfileImageRequest;
import com.ssafy.dancy.message.request.user.IntroduceTextChangeRequest;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.message.response.user.ChangeIntroduceResponse;
import com.ssafy.dancy.message.response.user.ChangedProfileImageResponse;
import com.ssafy.dancy.message.response.user.UpdatedUserResponse;
import com.ssafy.dancy.message.response.user.UserDetailInfoResponse;
import com.ssafy.dancy.repository.RedisRepository;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.type.AuthType;
import com.ssafy.dancy.type.Gender;
import com.ssafy.dancy.type.Role;
import com.ssafy.dancy.util.FileStoreUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RedisRepository redisRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileStoreUtil fileStoreUtil;

    private static final String PROFILE_IMAGE_TARGET = "profileImage";

    public UpdatedUserResponse signup(SignUpRequest request, Set<Role> roles) {
        userRepository.findByEmail(request.email()).ifPresent(
                (user) -> {throw new UserAlreadyExistException("이미 가입된 이메일입니다.");}
        );

        Boolean isVerifiedEmail = redisRepository.checkVerifiedEmail(request.email());
        if(!isVerifiedEmail){
            throw new EmailNotVerifiedException("인증받지 않은 이메일이거나, 인증 받은지 30분이 지난 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        LocalDate parsedDate = LocalDate.parse(request.birthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String profileImageUrl = fileStoreUtil.uploadProfileImageToS3(request.profileImage(), PROFILE_IMAGE_TARGET);

        User user = User.builder()
                .email(request.email())
                .nickname(request.nickname())
                .password(encodedPassword)
                .gender(Gender.valueOf(request.gender()))
                .birthDate(Date.valueOf(parsedDate))
                .authType(AuthType.valueOf(request.authType()))
                .roles(roles)
                .profileImageUrl(profileImageUrl)
                .build();

        User registeredUser = userRepository.save(user);

        return UpdatedUserResponse.builder()
                .email(registeredUser.getEmail())
                .nickname(registeredUser.getNickname())
                .build();
    }

    public void checkDuplicateNickname(String nickname){
        if(userRepository.existsByNickname(nickname)){
            throw new DuplicateNicknameException("중복되는 닉네임입니다.");
        }
    }

    public User login(LoginUserRequest request){
        Optional<User> foundUser = userRepository.findByEmail(request.email());

        if(foundUser.isPresent()){
            User user = foundUser.get();
            if(AuthType.isSocialAccount(user)){
                throw new SocialAccountException("소셜 로그인을 진행해야 하는 계정입니다.");
            }
            if(passwordEncoder.matches(request.password(), foundUser.get().getPassword())){
                return user;
            }
        }

        throw new UserInfoNotMatchException("아이디나 패스워드가 일치하지 않습니다.");
    }

    public void logout(User user) {
        redisRepository.logoutProcess(user, 15);
    }


    public UpdatedUserResponse changeNickname(User user, String nickname) {

        if(userRepository.existsByNickname(nickname)){
            throw new DuplicateNicknameException("중복되는 닉네임입니다.");
        }

        user.setNickname(nickname);
        userRepository.save(user);

        return UpdatedUserResponse.builder()
                .email(user.getEmail())
                .nickname(nickname)
                .build();
    }

    public ChangeIntroduceResponse changeIntroduceText(User user, IntroduceTextChangeRequest request) {
        user.setIntroduceText(request.introduceText());
        User updatedUser = userRepository.save(user);

        return ChangeIntroduceResponse.builder()
                .email(updatedUser.getEmail())
                .introduceText(updatedUser.getIntroduceText())
                .build();
    }

    public UserDetailInfoResponse getOwnDetailInfo(User user) {

        String birthDateAsString = user.getBirthDate().toString().split(" ")[0];

        return UserDetailInfoResponse.builder()
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .birthDate(birthDateAsString)
                        .introduceText(user.getIntroduceText())
                        .profileImageUrl(user.getProfileImageUrl())
                        .build();
    }

    public void changePassword(User user, ChangePasswordRequest request) {

        if(AuthType.isSocialAccount(user)){
            throw new SocialAccountException("소셜 로그인 아이디입니다.");
        }

        checkPassword(request.currentPassword(), user.getPassword());

        String newEncodedPassword = passwordEncoder.encode(request.newPassword());
        user.setPassword(newEncodedPassword);
        userRepository.save(user);

        logout(user);
    }

    public void deleteUser(User user, String password) {
        checkPassword(password, user.getPassword());
        logout(user);

        userRepository.delete(user);
    }

    private void checkPassword(String inputPassword, String userPassword){
        if(!passwordEncoder.matches(inputPassword, userPassword)){
            throw new UserPasswordNotMatchException("기존 비밀번호가 일치하지 않습니다.");
        }
    }

    public ChangedProfileImageResponse changeProfileImage(User user, ChangeProfileImageRequest request) {
        String profileImageUrl = fileStoreUtil.uploadProfileImageToS3(request.profileImage(), PROFILE_IMAGE_TARGET);
        user.setProfileImageUrl(profileImageUrl);

        User savedUser = userRepository.save(user);
        return ChangedProfileImageResponse.builder()
                .email(savedUser.getEmail())
                .profileImageUrl(savedUser.getProfileImageUrl())
                .build();
    }
}
