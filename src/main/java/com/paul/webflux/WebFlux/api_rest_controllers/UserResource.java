package com.paul.webflux.WebFlux.api_rest_controllers;

import com.paul.webflux.WebFlux.business_controllers.UserController;
import com.paul.webflux.WebFlux.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/users";
    public static final String LOGINID = "/{loginId}";


    private UserController userController;

    @Autowired
    public UserResource(UserController userController) {
        this.userController = userController;
    }

    @PostMapping(produces = {"application/json"})
    public Mono<ResponseEntity> create(@RequestBody UserDto userDto) {
        return this.userController.createUser(userDto);
    }

    @GetMapping(value = LOGINID)
    public Mono<UserDto> readUser(@PathVariable String loginId) {
        return this.userController.findUserByLoginId(loginId);
    }

    @GetMapping
    public Flux<UserDto> search() {
        return this.userController.search();
    }

    @PutMapping(value = LOGINID)
    public Mono<ResponseEntity> updateUser(@PathVariable String loginId, @RequestBody UserDto userDto){
        return  this.userController.editUser(loginId,userDto);
    }
}
