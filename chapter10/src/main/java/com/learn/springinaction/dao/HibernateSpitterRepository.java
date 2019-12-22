package com.learn.springinaction.dao;

import com.learn.springinaction.model.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class HibernateSpitterRepository {
    private SessionFactory sessionFactory;

    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Long count() {
        return 0L;
    }

    public void save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);
    }
}
