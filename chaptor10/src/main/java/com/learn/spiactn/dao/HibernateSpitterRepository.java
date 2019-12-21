package com.learn.spiactn.dao;

import com.learn.spiactn.model.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class HibernateSpitterRepository {
    private SessionFactory sessionFactory ;

    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
    public Long count(){
        return 0L;
    }
    public void save(Spitter spitter){
        Serializable id = currentSession().save(spitter);
    }
}
