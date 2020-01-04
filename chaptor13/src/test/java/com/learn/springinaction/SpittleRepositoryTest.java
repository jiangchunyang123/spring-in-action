package com.learn.springinaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CachingConfig.class)
public class SpittleRepositoryTest {


    @Autowired
    private SpittleRepository spittleRepository;

    @Test
    public void findOne() {
        Spittle spittle = spittleRepository.findOne(1L);
        assertNotNull(spittle);
    }

    @Test
    public void save() {
        Spittle spittle = new Spittle(2L, "hello", new Date());
        long id = spittleRepository.save(spittle);
        assert id == 2L;
    }

    @Test
    public void findOneWithoutCache() {
        Spittle spittle = spittleRepository.findOne(1L);
        assertNotNull(spittle);
        spittle = spittleRepository.findOne(1L);
        assertNotNull(spittle);
    }


    @Test
    public void findOneWithCache() {
        Spittle spittle = spittleRepository.findOneWithCache(1L);
        assertNotNull(spittle);
        spittle = spittleRepository.findOneWithCache(1L);
        assertNotNull(spittle);
    }
}