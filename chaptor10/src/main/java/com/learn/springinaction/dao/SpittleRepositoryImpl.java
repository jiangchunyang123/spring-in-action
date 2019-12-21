package com.learn.springinaction.dao;


import com.learn.springinaction.model.Spitter;
import com.learn.springinaction.model.Spittle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return createSpittleList(20);
    }

    @Override
    public Spittle findOne(Long spittleId) {
        return null;
    }

    @Override
    public void save(Spitter spitter) {

    }

    private List<Spittle> createSpittleList(int number) {
        List<Spittle> spittles = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            spittles.add(new Spittle((long) i, "Spittle" + i, new Date()));
        }
        return spittles;
    }
}
