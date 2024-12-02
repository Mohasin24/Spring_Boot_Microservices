package com.ecom_project.inventory_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/v1/inventory")
public class Test
{

    private final WebClient.Builder builder;

    @Autowired
    public Test(WebClient.Builder builder){
        this.builder=builder;
    }


    @GetMapping("/test")
    public String test(){
        String ret =builder.build().get()
                .uri("http://order-service/api/v1/order/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();

       return ret;
    }

}
