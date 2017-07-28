package com.michol.configuration;

import com.michol.api.converter.LoginUserModelToUserConverter;
import com.michol.api.usecase.HandlerExecutor;
import com.michol.api.usecase.impl.AuthenticationHandler;
import com.michol.api.usecase.impl.ChangePasswordHandler;
import com.michol.api.usecase.impl.LoginHandler;
import com.michol.api.usecase.impl.SignHandler;
import com.michol.auth.Authenticator;
import com.michol.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlersConfiguration {

    @Bean
    public HandlerExecutor handlerExecutor(ApplicationContext applicationContext) {
        return new HandlerExecutor(applicationContext);
    }

    @Bean
    public SignHandler signHandler(UserDao userDao, LoginUserModelToUserConverter loginUserModelToUserConverter, Authenticator authenticator) {
        return new SignHandler(userDao, loginUserModelToUserConverter, authenticator);
    }

    @Bean
    public LoginHandler loginHandler(Authenticator authenticator) {
        return new LoginHandler(authenticator);
    }

    @Bean
    public ChangePasswordHandler changePasswordHandler(Authenticator authenticator, UserDao userDao) {
        return new ChangePasswordHandler(authenticator, userDao);
    }

    @Bean
    public AuthenticationHandler authenticationHandler(Authenticator authenticator){
        return new AuthenticationHandler(authenticator);
    }
}
