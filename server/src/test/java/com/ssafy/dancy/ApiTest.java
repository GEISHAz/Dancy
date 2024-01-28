package com.ssafy.dancy;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;



@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {


    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        if(RestAssured.port == RestAssured.UNDEFINED_PORT){
            RestAssured.port = port;
        }
    }
}
