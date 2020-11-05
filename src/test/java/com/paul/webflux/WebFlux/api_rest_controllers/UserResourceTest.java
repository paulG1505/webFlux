package com.paul.webflux.WebFlux.api_rest_controllers;

import com.paul.webflux.WebFlux.dtos.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
class UserResourceTest {

    @Autowired
    private RestService restService;

    public int[] arreglo= new int[4];

    @Test
    void create() {
        UserDto userDto= new UserDto("loginId","name","type", "user",Boolean.FALSE,arreglo);
        String get= this.restService.restbuildert().post()
                .uri(UserResource.USERS).body(BodyInserters.fromObject(userDto))
                .exchange().expectStatus().isCreated().expectBody(String.class).returnResult().getResponseBody();

        assertNotNull(get);
        assertEquals("\"user created\"",get);
    }

    @Test
    void readUser(){
        UserDto user= this.restService.restbuildert().get()
                .uri(UserResource.USERS+UserResource.LOGINID,"loginId")
                .exchange().expectStatus().isOk().expectBody(UserDto.class)
                .returnResult().getResponseBody();
        assertNotNull(user);
        assertEquals("loginId",user.getLoginId());
    }
    @Test
    void search(){
        this.restService.restbuildert().get().uri(UserResource.USERS).exchange().expectStatus().isOk();
    }

    @Test
    void update(){
        UserDto articleDto = new UserDto("t123434", "paul", "estudiante","paul14", Boolean.TRUE,arreglo);
        String get=
                this.restService.restbuildert()
                        .put().uri(UserResource.USERS+UserResource.LOGINID,"loginId")
                        .body(BodyInserters.fromObject(articleDto))
                        .exchange()
                        .expectStatus().isAccepted()
                        .expectBody(String.class).returnResult().getResponseBody();
        assertNotNull(get);
        assertEquals("\"user updated\"", get);

    }
}