package com.rafael.webflux.WebFlux.api_rest_controllers;

import com.rafael.webflux.WebFlux.business_controllers.ProductController;
import com.rafael.webflux.WebFlux.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ProductResource.PRODUCTS)
public class ProductResource {

    public static final String PRODUCTS = "/products";
    public static final String CODE = "/{code}";


    private ProductController productController;

    @Autowired
    public ProductResource(ProductController productController) {
        this.productController = productController;
    }

    @PostMapping(produces = {"application/json"})
    public Mono<ResponseEntity> create(@RequestBody ProductDto productDto) {
        return this.productController.createProduct(productDto);
    }

    @GetMapping(value = CODE)
    public Mono<ProductDto> readProduct(@PathVariable String code) {
        return this.productController.findProductByCode(code);
    }

    @GetMapping
    public Flux<ProductDto> search() {
        return this.productController.search();
    }

    @PutMapping(value = CODE)
    public Mono<ResponseEntity> updateProduct(@PathVariable String code, @RequestBody ProductDto productDto){
        return  this.productController.editProduct(code,productDto);
    }

}
