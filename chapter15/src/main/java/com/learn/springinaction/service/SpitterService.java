package com.learn.springinaction.service;

import com.learn.springinaction.model.Spitter;
import com.learn.springinaction.model.Spittle;

import java.util.List;

public interface SpitterService {

    List<Spittle> getRecentSpittles(int count);

    void saveSpittle(Spittle spittle);

    void saveSpitter(Spitter spitter);
}
