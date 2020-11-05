package com.paul.webflux.WebFlux.repositories;

import com.paul.webflux.WebFlux.documents.User;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface UserRepository extends ReactiveSortingRepository<User,String> {
}
