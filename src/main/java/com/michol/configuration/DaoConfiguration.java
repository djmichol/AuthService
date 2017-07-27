package com.michol.configuration;

import com.michol.dao.UserDao;
import com.michol.dao.implementation.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfiguration {

    @Bean
    public UserDao userDao(){
        return new UserDaoImpl();
    }
}
