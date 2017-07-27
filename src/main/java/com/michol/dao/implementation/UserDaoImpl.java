package com.michol.dao.implementation;

import com.michol.dao.UserDao;
import com.michol.dao.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public User get(String login) {
        Query query = entityManager.createQuery("Select data from User data where data.login = :login", User.class);
        query.setParameter("login", login);
        return (User) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public String getUserToken(String login) {
        Query query = entityManager.createQuery("Select data.token from User data where data.login = :login", String.class);
        query.setParameter("login", login);
        return (String) query.getSingleResult();
    }

    @Override
    @Transactional
    public User create(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }
}
