package com.learn.springinaction.hibernatejpa;

import com.learn.springinaction.domain.Spitter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class JpaSpitterRepository implements SpitterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Spitter findOne(Long id) {
        return entityManager.find(Spitter.class, id);
    }

    @Override
    public long save(Spitter spitter) {
        entityManager.persist(spitter);
        return 0;
    }
}
