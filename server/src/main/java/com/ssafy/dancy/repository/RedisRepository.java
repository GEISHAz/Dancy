package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.verify.VerifySystemBlockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisRepository {

    private final RedisTemplate<Object, Object> redisTemplate;

    private static final String EMAIL_VERIFY_PREFIX = "VERIFY";
    private static final String EMAIL_VERIFY_SUCCESS_PREFIX = "SUCCESS";
    private static final String REFRESH_TOKEN_PREFIX = "RT";
    private static final String BLACKLIST_TOKEN_PREFIX = "BLACK";
    private static final String PASSWORD_FIND_CODE_PREFIX = "PFIND";
    private static final String STACK_WRONG_CODE_PREFIX = "WRONG";
    private static final String EMAIL_BLOCK_PREFIX = "BLOCK";
    private static final String PASSWORD_FIND_AUTHORIZED_PREFIX = "PFAUTH";

    public String saveEmailVerifyCode(String targetEmail, String code, int timeLimit){
        String key = String.format("%s:%s", EMAIL_VERIFY_PREFIX, targetEmail);
        return saveKeyValue(key, code, timeLimit, TimeUnit.MINUTES);
    }

    public String getEmailVerifyCode(String targetEmail){
        String key = String.format("%s:%s", EMAIL_VERIFY_PREFIX, targetEmail);
        return (String)redisTemplate.opsForValue().get(key);
    }

    public void saveVerifySuccess(String targetEmail, int timeLimit){
        String key = getVerifySuccessKey(targetEmail);
        saveKeyValue(key, "1", timeLimit, TimeUnit.MINUTES);
    }
    public Boolean checkVerifiedEmail(String targetEmail){
        return redisTemplate.hasKey(getVerifySuccessKey(targetEmail));
    }

    public void saveRefreshToken(String email, String token, int timeLimit){
        String key = getRefreshTokenKey(email);
        saveKeyValue(key, token, timeLimit, TimeUnit.DAYS);
    }

    public void logoutProcess(User user, int timeLimit) {
        String key = getRefreshTokenKey(user.getEmail());
        String token = (String)redisTemplate.opsForValue().get(key);

        redisTemplate.delete(key);
        saveKeyValue(getTokenBlacklistKey(token), user.getEmail(), timeLimit, TimeUnit.DAYS);
    }

    public void savePasswordFindCode(String targetEmail, String code, int timeLimit){
        String key = getPasswordFindCodeKey(targetEmail);
        saveKeyValue(key, code, timeLimit, TimeUnit.MINUTES);
    }

    public Optional<String> getPasswordFindCode(String targetEmail){
        String key = getPasswordFindCodeKey(targetEmail);
        return Optional.ofNullable((String)redisTemplate.opsForValue().get(key));

    }

    public int stackWrongPasswordFindCode(String targetEmail, int timeLimit, int blockTIme){
        String key = getStackWrongCodeKey(targetEmail);
        String stackCountString = (String) redisTemplate.opsForValue().get(key);

        int newCount = getNewWrongStackCount(stackCountString);

        if(newCount >= 5){
            String blockKey = getBlockEmailKey(targetEmail);
            saveKeyValue(blockKey, "BLOCK", blockTIme, TimeUnit.MINUTES);
            throw new VerifySystemBlockException("인증번호를 5회 이상 틀려, 비밀번호 찾기 시스템을 일정 시간동안 사용할 수 없습니다.");
        }

        saveKeyValue(key, Integer.toString(newCount), timeLimit, TimeUnit.MINUTES);
        return newCount;
    }

    private int getNewWrongStackCount(String stackCountString) {
        if(stackCountString == null){
            return 1;
        }
        return Integer.parseInt(stackCountString) + 1;
    }

    public Boolean isEmailBlocked(String targetEmail) {
        return redisTemplate.hasKey(getBlockEmailKey(targetEmail));
    }

    public void deletePasswordFindInfo(String targetEmail) {
        String passwordFindKey = getPasswordFindCodeKey(targetEmail);
        String stackWrongCodeKey = getStackWrongCodeKey(targetEmail);

        redisTemplate.delete(List.of(passwordFindKey, stackWrongCodeKey));
    }

    public void savePasswordFindAuthorizedInfo(String targetEmail, int timeLimit){
        String authorizedKey = getPasswordFindAuthorizedKey(targetEmail);
        saveKeyValue(authorizedKey, "AUTH", timeLimit, TimeUnit.MINUTES);
    }

    public boolean getPasswordFindAuthInfo(String targetEmail){
        String key = getPasswordFindAuthorizedKey(targetEmail);
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    private static String getVerifySuccessKey(String targetEmail) {
        return String.format("%s:%s", EMAIL_VERIFY_SUCCESS_PREFIX, targetEmail);
    }

    private static String getRefreshTokenKey(String email){
        return String.format("%s:%s", REFRESH_TOKEN_PREFIX, email);
    }

    private static String getTokenBlacklistKey(String token){
        return String.format("%s:%s", BLACKLIST_TOKEN_PREFIX, token);
    }

    private static String getPasswordFindCodeKey(String email){
        return String.format("%s:%s", PASSWORD_FIND_CODE_PREFIX, email);
    }

    private static String getStackWrongCodeKey(String email){
        return String.format("%s:%s", STACK_WRONG_CODE_PREFIX, email);
    }

    private static String getBlockEmailKey(String email){
        return String.format("%s:%s", EMAIL_BLOCK_PREFIX, email);
    }

    private static String getPasswordFindAuthorizedKey(String email){
        return String.format("%s:%s", PASSWORD_FIND_AUTHORIZED_PREFIX, email);
    }

    private String saveKeyValue(String key, String value, int limitMinute, TimeUnit timeUnit){
        ValueOperations<Object, Object> operation = redisTemplate.opsForValue();
        try{ // 미봉책. 나중에 더 상세히 파 볼 것.
            operation.set(key, value, limitMinute, timeUnit);
            log.info("key: {}, value: {} 로 {} 간 redis 저장", key, value, limitMinute);
        }catch(NullPointerException ignored){}
        return String.format("%s -> %s", key, value);
    }
}
