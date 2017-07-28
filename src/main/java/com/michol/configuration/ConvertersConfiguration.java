package com.michol.configuration;

import com.michol.api.converter.LoginUserModelToUserConverter;
import com.michol.auth.Authenticator;
import com.michol.auth.HashService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConvertersConfiguration {

    @Bean
    public LoginUserModelToUserConverter loginUserModelToUserConverter(HashService hashService){
        return new LoginUserModelToUserConverter(hashService);
    }

}
