package com.learn.springinaction.dao;

import com.learn.springinaction.EmbeddedDatasourceConfig;
import com.learn.springinaction.model.Spitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedDatasourceConfig.class)
@ActiveProfiles("test")
public class SpitterRepositoryTest {

    @Autowired
    private SpitterRepository spitterRepository;

    @Test
    public void save() {
        Spitter spitter = new Spitter();
        spitter.setUsername("hello");
        spitter.setPassword("hello");
        spitterRepository.save(spitter);
    }

    @Test
    public void findOne() {
        Spitter spitter = new Spitter();
        spitter.setUsername("hello");
        spitter.setPassword("h2db");
        long id = spitterRepository.save(spitter);
        Spitter spitterInDb = spitterRepository.findOne(id);
        assert spitterInDb != null && spitterInDb.getPassword().equals(spitter.getPassword());
    }

}