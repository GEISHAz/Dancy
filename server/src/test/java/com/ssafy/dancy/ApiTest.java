package com.ssafy.dancy;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class ApiTest {

    @Autowired
    private DatabaseCleanup databaseCleanup;
    @MockBean
    protected RedisTemplate<Object, Object> redisTemplate;
    @MockBean
    protected RedisKeyValueAdapter adapter;
    @MockBean
    protected ValueOperations<Object, Object> mockValueOp;
    @MockBean
    protected JavaMailSender mailSender;


    @LocalServerPort
    private int port;

    protected static final String DEFAULT_RESTDOC_PATH = "{class_name}/{method_name}/";
    protected RequestSpecification spec;

    @BeforeEach
    void setUp(){
        if(RestAssured.port == RestAssured.UNDEFINED_PORT){
            RestAssured.port = port;
            databaseCleanup.afterPropertiesSet();
        }

        databaseCleanup.truncateAllTables();
        Mockito.doReturn(false).when(redisTemplate).hasKey(matches("^BLOCK:"));
        Mockito.doReturn(true).when(redisTemplate).hasKey(matches("^(?!BLOCK:)"));
        Mockito.doReturn(mockValueOp).when(redisTemplate).opsForValue();
        Mockito.when(redisTemplate.opsForValue().get(matches("^WRONG:"))).thenReturn("1");
        Mockito.when(redisTemplate.opsForValue().get(matches("^(?!WRONG:)"))).thenReturn("123456");
        Mockito.doNothing().when(mockValueOp).set(anyString(), anyString(), anyLong(), any());
        Mockito.when(redisTemplate.delete(anyString())).thenReturn(true);
        Mockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));
    }

    @AfterEach
    void resetMocks(){
        Mockito.reset(redisTemplate, adapter, mockValueOp, mailSender);
    }

    @BeforeEach
    void setUpRestDocs(RestDocumentationContextProvider restDocumentation){
        this.spec = new RequestSpecBuilder()
                .setPort(port)
                .addFilter(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();
    }
}
