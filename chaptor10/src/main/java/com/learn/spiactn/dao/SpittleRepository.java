package com.learn.spiactn.dao;

import com.learn.spiactn.model.Spitter;
import com.learn.spiactn.model.Spittle;

import java.util.List;


public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(Long spittleId);

    void save(Spitter spitter);
}
