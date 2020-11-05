package com.paul.webflux.WebFlux.business_controllers;

import com.paul.webflux.WebFlux.documents.User;
import com.paul.webflux.WebFlux.dtos.UserDto;
import com.paul.webflux.WebFlux.exceptions.NotFoundException;
import com.paul.webflux.WebFlux.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ResponseEntity> createUser(UserDto userDto) {
        User user = new User();
        user.setLoginId(userDto.getLoginId());
        user.setName(userDto.getName());
        user.setType(userDto.getType());
        user.setUser(userDto.getUser());
        user.setActive(userDto.getActive());
        user.setData(userDto.getData());
        return this.userRepository.save(user).map(calback -> {
            return new ResponseEntity("\"user created\"", HttpStatus.CREATED);
        }).onErrorReturn(new ResponseEntity("\" user non't created\"", HttpStatus.NOT_ACCEPTABLE));
    }

    public Mono<UserDto> findUserByLoginId(String loginId) {
        return this.userRepository.findById(loginId).switchIfEmpty(Mono.error(new NotFoundException(" user " + loginId)))
                .map(UserDto::new);
    }

    public Flux<UserDto> search() {
        return this.userRepository.findAll().map(UserDto::new);
    }

    public Mono<ResponseEntity> editUser(String loginId, UserDto userDto) {
        Mono<User> user = this.userRepository.findById(loginId).switchIfEmpty(Mono.error(new NotFoundException(" user " + loginId)))
                .map(userDb -> {
                    userDb.setActive(userDto.getActive());
                    userDb.setName(userDto.getName());
                    userDb.setType(userDto.getType());
                    userDb.setUser(userDto.getUser());
                    userDb.setData(userDto.getData());
                    return userDb;
                });
        return Mono.when(user).
                then(this.userRepository.save(user.block())
                        .map(
                                calback -> {
                                    return new ResponseEntity("\"user updated\"", HttpStatus.ACCEPTED);
                                }
                        )
                );
    }
}
