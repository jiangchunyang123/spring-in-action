package com.learn.springinaction.service;

import com.learn.springinaction.dao.SpitterRepository;
import com.learn.springinaction.dao.SpittleRepository;
import com.learn.springinaction.model.Spitter;
import com.learn.springinaction.model.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpitterServiceImpl implements SpitterService {

    @Autowired
    private SpitterRepository spitterRepository;

    @Autowired
    private SpittleRepository spittleRepository;


    @Override
    public List<Spittle> getRecentSpittles(int count) {
        return null;
    }

    @Override
    public void saveSpittle(Spittle spittle) {
        spittleRepository.save(spittle);
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        spitterRepository.save(spitter);
    }
}
