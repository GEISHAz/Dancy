package com.ssafy.dancy.service.user;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.user.UserAlreadyExistException;
import com.ssafy.dancy.exception.user.UserInfoNotMatchException;
import com.ssafy.dancy.exception.verify.EmailNotVerifiedException;
import com.ssafy.dancy.message.request.auth.LoginUserRequest;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.message.response.user.SignUpResultResponse;
import com.ssafy.dancy.repository.RedisRepository;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.type.AuthType;
import com.ssafy.dancy.type.Gender;
import com.ssafy.dancy.type.Role;
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


    public SignUpResultResponse signup(SignUpRequest request, Set<Role> roles) {
        userRepository.findByEmail(request.email()).ifPresent(
                (user) -> {throw new UserAlreadyExistException("이미 가입된 이메일입니다.");}
        );

        Boolean isVerifiedEmail = redisRepository.checkVerifiedEmail(request.email());
        if(!isVerifiedEmail){
            throw new EmailNotVerifiedException("인증받지 않은 이메일이거나, 인증 받은지 30분이 지난 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        LocalDate parsedDate = LocalDate.parse(request.birthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        User user = User.builder()
                .email(request.email())
                .nickname(request.nickname())
                .password(encodedPassword)
                .gender(Gender.valueOf(request.gender()))
                .birthDate(Date.valueOf(parsedDate))
                .authType(AuthType.valueOf(request.authType()))
                .build();

        User registeredUser = userRepository.save(user);

        return SignUpResultResponse.builder()
                .email(registeredUser.getEmail())
                .nickname(registeredUser.getNickname())
                .build();
    }

    public User login(LoginUserRequest request){
        Optional<User> foundUser = userRepository.findByEmail(request.email());

        if(foundUser.isEmpty() || !passwordEncoder.matches(request.password(), foundUser.get().getPassword())){
            throw new UserInfoNotMatchException("아이디나 패스워드가 일치하지 않습니다.");
        }
        return foundUser.get();
    }
}
