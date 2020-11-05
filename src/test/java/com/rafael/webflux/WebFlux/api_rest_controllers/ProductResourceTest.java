package com.rafael.webflux.WebFlux.api_rest_controllers;

import com.rafael.webflux.WebFlux.dtos.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
class ProductResourceTest {

    @Autowired
    private RestService restService;



    @Test
    void create() {
        ProductDto productDto= new ProductDto("code","name","description",106.6);
        String get= this.restService.restbuildert().post()
                .uri(ProductResource.PRODUCTS).body(BodyInserters.fromObject(productDto))
                .exchange().expectStatus().isCreated().expectBody(String.class).returnResult().getResponseBody();

        assertNotNull(get);
        assertEquals("\"product created\"",get);
    }

    @Test
    void readProduct(){
        ProductDto product= this.restService.restbuildert().get()
                .uri(ProductResource.PRODUCTS+ProductResource.CODE,"code")
                .exchange().expectStatus().isOk().expectBody(ProductDto.class)
                .returnResult().getResponseBody();
        assertNotNull(product);
        assertEquals("code",product.getCode());
    }
    @Test
    void search(){
        this.restService.restbuildert().get().uri(ProductResource.PRODUCTS).exchange().expectStatus().isOk();
    }

    @Test
    void update(){
        ProductDto articleDto = new ProductDto("t123434", "a4", "hoja de papel bond", 0.04);
        String get=
                this.restService.restbuildert()
                        .put().uri(ProductResource.PRODUCTS+ProductResource.CODE,"code")
                        .body(BodyInserters.fromObject(articleDto))
                        .exchange()
                        .expectStatus().isAccepted()
                        .expectBody(String.class).returnResult().getResponseBody();
        assertNotNull(get);
        assertEquals("\"product updated\"", get);

    }
}