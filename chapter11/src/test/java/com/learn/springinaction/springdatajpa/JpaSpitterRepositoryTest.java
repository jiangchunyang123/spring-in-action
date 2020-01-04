package com.learn.springinaction.springdatajpa;

import com.learn.springinaction.domain.Spitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataJpaConfig.class)
public class JpaSpitterRepositoryTest {

    @Autowired
    private JpaSpitterRepository jpaSpitterRepository;

    @Test
    public void findOne() {
        Spitter spitter = new Spitter();
        spitter.setFirstName("hello");
        spitter.setLastName("world");
        spitter = jpaSpitterRepository.save(spitter);
        assert spitter != null;
        Spitter spitterInDb = jpaSpitterRepository.findById(spitter.getId());
        assert spitterInDb.equals(spitter);
    }
}