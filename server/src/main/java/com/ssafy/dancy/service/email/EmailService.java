package com.ssafy.dancy.service.email;

import com.ssafy.dancy.exception.user.UserAlreadyExistException;
import com.ssafy.dancy.exception.verify.VerifyCodeNotFoundException;
import com.ssafy.dancy.exception.verify.VerifyCodeNotMatchException;
import com.ssafy.dancy.message.response.email.EmailVerifyResponse;
import com.ssafy.dancy.repository.RedisRepository;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.util.VerifyCodeMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private final VerifyCodeMaker codeMaker;
    private final UserRepository userRepository;
    private final RedisRepository redisRepository;

    private static final int VERIFY_EMAIL_TIME_LIMIT = 30;

    public void sendVerifyCode(String targetEmail){
        checkUserExist(targetEmail);

        String title = "DANCY 서비스 가입 이메일 인증코드입니다.";
        String code = codeMaker.makeVerifyCode();

        redisRepository.saveEmailVerifyCode(targetEmail, code, VERIFY_EMAIL_TIME_LIMIT);
        sendEmail(targetEmail, title, makeBody(code));
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

    private String makeBody(String code){
        return String.format("가입 창에 인증번호를 입력해 주세요! \n\nDANCY 의 인증코드 : %s", code);
    }

    private void checkUserExist(String targetEmail) {
        boolean checkUserExists = userRepository.existsByEmail(targetEmail);
        if(checkUserExists){
            throw new UserAlreadyExistException("이미 가입된 이메일 계정입니다.");
        }
    }

}
