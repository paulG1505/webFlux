package com.rafael.webflux.WebFlux.repositories;

import com.rafael.webflux.WebFlux.documents.Product;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface ProductRepository  extends ReactiveSortingRepository<Product,String> {
}
