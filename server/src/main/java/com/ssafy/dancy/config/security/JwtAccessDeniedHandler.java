package com.ssafy.dancy.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.dancy.message.response.ErrorResponse;
import com.ssafy.dancy.type.JwtCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        String token = jwtTokenProvider.resolveToken(request);
        JwtCode code = jwtTokenProvider.validateToken(token);

        response.setContentType("application/json");
        response.setStatus(getStatusInfo(code));
        response.getWriter().write(objectMapper.writeValueAsString(getErrorResponse(code)));
    }
    private int getStatusInfo(JwtCode code){
        if(code == JwtCode.ACCESS){
            return HttpStatus.FORBIDDEN.value();
        }
        return HttpStatus.UNAUTHORIZED.value();
    }
    private static ErrorResponse getErrorResponse(JwtCode code) {
        return switch (code) {
            case ACCESS -> errorResponseBuilder("AccessDeniedException", "유저 권한이 부족합니다", "");
            case EXPIRED -> errorResponseBuilder("TokenExpiredException", "토근이 만료되었습니다.", "");
            case DENIED -> errorResponseBuilder("TokenInvalidException", "토큰이 유효하지 않습니다.", "");
            default -> null;
        };
    }
    private static ErrorResponse errorResponseBuilder(String errorType, String errorMessage, String fieldName){
        return ErrorResponse.builder()
                .errorType(errorType)
                .message(errorMessage)
                .fieldName(fieldName).build();
    }

}
