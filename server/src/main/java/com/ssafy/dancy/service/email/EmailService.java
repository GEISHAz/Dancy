package com.ssafy.dancy.service.email;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.user.UserAlreadyExistException;
import com.ssafy.dancy.exception.user.UserNotFoundException;
import com.ssafy.dancy.exception.verify.VerifyCodeNotFoundException;
import com.ssafy.dancy.exception.verify.VerifyCodeNotMatchException;
import com.ssafy.dancy.exception.verify.VerifySystemBlockException;
import com.ssafy.dancy.message.response.email.EmailVerifyResponse;
import com.ssafy.dancy.repository.RedisRepository;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.type.AuthType;
import com.ssafy.dancy.util.VerifyCodeMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender emailSender;
    private final VerifyCodeMaker codeMaker;
    private final UserRepository userRepository;
    private final RedisRepository redisRepository;

    private static final int VERIFY_EMAIL_TIME_LIMIT = 30;
    private static final int PASSWORD_FIND_TIME_LIMIT = 5;

    public void sendVerifyCode(String targetEmail){
        log.info("유저 있는지 확인");
        checkUserExist(targetEmail);

        String title = "DANCY 서비스 가입 이메일 인증코드입니다.";
        String code = codeMaker.makeVerifyCode();

        log.info("레디스 저장 시도");
        redisRepository.saveEmailVerifyCode(targetEmail, code, VERIFY_EMAIL_TIME_LIMIT);

        log.info("이메일 전송 시도");
        sendEmail(targetEmail, title, makeJoinBody(code));
    }


    public EmailVerifyResponse checkJoinVerifyCode(String targetEmail, String verifyCode){
        checkUserExist(targetEmail);

        String storedVerifyCode = redisRepository.getEmailVerifyCode(targetEmail);
        if(storedVerifyCode == null){
            throw new VerifyCodeNotFoundException("인증코드가 저장되어 있지 않습니다.");
        }

        if(!storedVerifyCode.equals(verifyCode)){
            throw new VerifyCodeNotMatchException("인증번호가 일치하지 않습니다.");
        }

        redisRepository.saveVerifySuccess(targetEmail, VERIFY_EMAIL_TIME_LIMIT);

        return EmailVerifyResponse.builder()
                .targetEmail(targetEmail)
                .verified(true)
                .build();
    }

    public void sendPasswordFindCode(String targetEmail) {
        User user = userRepository.findByEmail(targetEmail).orElseThrow(
                () -> new UserNotFoundException("가입된 이메일이 아닙니다.")
        );

        AuthType.checkSocialAccount(user);

        if(redisRepository.isEmailBlocked(targetEmail)){
            throw new VerifySystemBlockException("비밀번호 찾기 시스템을 사용할 수 없는 사용자입니다.");
        }

        // TODO : 어떤 URL 을 전송할지 프론트앤드와 상의해서 URL 만들기
        String mockUrl = "https://www.naver.com";

        String title = "DANCY 서비스 비밀번호 찾기 인증코드입니다.";
        String code = codeMaker.makeVerifyCode();

        redisRepository.savePasswordFindCode(targetEmail, code, PASSWORD_FIND_TIME_LIMIT);
        sendEmail(targetEmail, title, makePasswordFindBody(code, mockUrl));
    }

    private void sendEmail(String targetEmail, String title, String body){
        SimpleMailMessage emailForm = createEmailForm(targetEmail, title, body);
        emailSender.send(emailForm);
    }

    private SimpleMailMessage createEmailForm(String toEmail, String title, String text){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }

    private String makeJoinBody(String code){
        return String.format("가입 창에 인증번호를 입력해 주세요! \n\nDANCY 의 인증코드 : %s", code);
    }

    private String makePasswordFindBody(String code, String url){
        return String.format("아래 URL 에서 인증번호를 입력해 주세요! \n\nURL : %s\n\n인증코드 : %s", url, code);
    }

    private void checkUserExist(String targetEmail) {
        boolean checkUserExists = userRepository.existsByEmail(targetEmail);
        if(checkUserExists){
            throw new UserAlreadyExistException("이미 가입된 이메일 계정입니다.");
        }
    }
}
