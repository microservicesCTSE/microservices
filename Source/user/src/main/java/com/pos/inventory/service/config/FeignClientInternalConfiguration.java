package com.pos.inventory.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignClientInternalConfiguration {

//    @Bean
//    public Contract feignContract(){
//        return new Contract.Default();
//    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        // TODO : The service not going to use from outside. No need to configure username and password.
        return new BasicAuthRequestInterceptor("user", "password");
    }
}
