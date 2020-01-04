package com.learn.springinaction;

import com.learn.springinaction.domain.Spitter;
import com.learn.springinaction.hibernatejpa.JpaConfig;
import com.learn.springinaction.hibernatejpa.SpitterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
public class SpitterRepositoryTest {

    @Autowired
    private SpitterRepository spitterRepository;

    @Test
    public void save() {
        Spitter spitter = new Spitter();
        spitter.setFirstName("zhangsan");
        spitter.setUsername("admin");
        spitterRepository.save(spitter);
    }

    @Test
    public void findOne() {
        Spitter spitter = new Spitter();
        spitter.setFirstName("lisi");
        spitter.setUsername("admin2");
        spitterRepository.save(spitter);
        Spitter spitter1 = spitterRepository.findOne(1L);
        assert spitter1 != null;
    }


}