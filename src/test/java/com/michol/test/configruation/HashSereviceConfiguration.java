package com.michol.test.configruation;

import com.michol.auth.HashService;
import com.michol.auth.impl.HashServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashSereviceConfiguration {

    @Bean
    public HashService hashService(){
        return new HashServiceImpl();
    }

}
