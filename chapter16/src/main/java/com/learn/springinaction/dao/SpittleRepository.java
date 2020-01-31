package com.learn.springinaction.dao;

import com.learn.springinaction.model.Spittle;

import java.util.List;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);
}
