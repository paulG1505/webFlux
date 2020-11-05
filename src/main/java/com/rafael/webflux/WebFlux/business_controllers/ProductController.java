package com.rafael.webflux.WebFlux.business_controllers;

import com.rafael.webflux.WebFlux.documents.Product;
import com.rafael.webflux.WebFlux.dtos.ProductDto;
import com.rafael.webflux.WebFlux.exceptions.NotFoundException;
import com.rafael.webflux.WebFlux.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ProductController {


    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<ResponseEntity> createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return this.productRepository.save(product).map(calback -> {
            return new ResponseEntity("\"product created\"", HttpStatus.CREATED);
        }).onErrorReturn(new ResponseEntity("\" product non't created\"", HttpStatus.NOT_ACCEPTABLE));
    }

    public Mono<ProductDto> findProductByCode(String code) {
        return this.productRepository.findById(code).switchIfEmpty(Mono.error(new NotFoundException(" product " + code)))
                .map(ProductDto::new);
    }

    public Flux<ProductDto> search() {
        return this.productRepository.findAll().map(ProductDto::new);
    }

    public Mono<ResponseEntity> editProduct(String code, ProductDto productDto) {
        Mono<Product> product = this.productRepository.findById(code).switchIfEmpty(Mono.error(new NotFoundException(" product " + code)))
                .map(productDb -> {
                    productDb.setPrice(productDto.getPrice());
                    productDb.setName(productDto.getName());
                    productDb.setDescription(productDto.getDescription());
                    return productDb;
                });
        return Mono.when(product).
                then(this.productRepository.save(product.block())
                        .map(
                                calback -> {
                                    return new ResponseEntity("\"product updated\"", HttpStatus.ACCEPTED);
                                }
                        )
                );
    }
}
