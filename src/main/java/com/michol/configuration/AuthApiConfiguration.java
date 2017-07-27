package com.michol.configuration;

import com.michol.api.AuthenticationServiceHttp;
import com.michol.api.LoginServiceHttp;
import com.michol.api.converter.LoginUserModelToUserConverter;
import com.michol.auth.Authenticator;
import com.michol.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthApiConfiguration {

    @Bean
    public AuthenticationServiceHttp authenticationServiceHttp(Authenticator authenticator){
        return new AuthenticationServiceHttp(authenticator);
    }

    @Bean
    public LoginServiceHttp loginServiceHttp(Authenticator authenticator, UserDao userDao, LoginUserModelToUserConverter loginUserModelToUserConverter){
        return new LoginServiceHttp(authenticator, userDao, loginUserModelToUserConverter);
    }
}
