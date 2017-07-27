package com.michol.configuration;

import com.michol.api.converter.LoginUserModelToUserConverter;
import com.michol.auth.Authenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConvertersConfiguration {

    @Bean
    public LoginUserModelToUserConverter loginUserModelToUserConverter(Authenticator authenticator){
        return new LoginUserModelToUserConverter(authenticator);
    }

}
