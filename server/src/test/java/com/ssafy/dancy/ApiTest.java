package com.ssafy.dancy;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;

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


    @LocalServerPort
    private int port;

    protected static final String DEFAULT_RESTDOC_PATH = "{class_name}/{method_name}/";
    protected RequestSpecification spec;

    @BeforeEach
    void setUp(){
        if(RestAssured.port == RestAssured.UNDEFINED_PORT){
            RestAssured.port = port;
            databaseCleanup.init();
        }

        databaseCleanup.truncateAllTables();
        Mockito.doReturn(true).when(redisTemplate).hasKey(anyString());
        Mockito.doReturn(mockValueOp).when(redisTemplate).opsForValue();
        Mockito.when(redisTemplate.opsForValue().get(anyString())).thenReturn("123456");
        Mockito.doNothing().when(mockValueOp).set(anyString(), anyString(), anyLong(), any());
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
