package com.learn.springinaction.hibernate;

import com.learn.springinaction.domain.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
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

    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);
        spitter.setId((Long) id);
        return spitter;
    }

    public Spitter findByUsername(String userName) {
        return (Spitter) currentSession().createCriteria(Spitter.class).add(Restrictions.eq("username", userName)).list().get(0);
    }

}
