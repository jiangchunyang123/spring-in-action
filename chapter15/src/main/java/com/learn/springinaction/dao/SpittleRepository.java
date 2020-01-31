package com.learn.springinaction.dao;

import com.learn.springinaction.model.Spittle;

import java.util.List;


public interface SpittleRepository {
    List<Spittle> findSpittles(long start, int count);

    Spittle findOne(Long spittleId);

    void save(Spittle spitter);
}
