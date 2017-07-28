package com.michol.configuration;

import com.michol.api.AuthenticationServiceHttp;
import com.michol.api.LoginServiceHttp;
import com.michol.api.converter.LoginUserModelToUserConverter;
import com.michol.api.usecase.HandlerExecutor;
import com.michol.auth.Authenticator;
import com.michol.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthApiConfiguration {

    @Bean
    public AuthenticationServiceHttp authenticationServiceHttp(HandlerExecutor handlerExecutor){
        return new AuthenticationServiceHttp(handlerExecutor);
    }

    @Bean
    public LoginServiceHttp loginServiceHttp(HandlerExecutor handlerExecutor){
        return new LoginServiceHttp(handlerExecutor);
    }
}
