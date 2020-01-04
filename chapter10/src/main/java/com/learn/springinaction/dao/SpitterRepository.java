package com.learn.springinaction.dao;

import com.learn.springinaction.model.Spitter;

import java.util.List;

public interface SpitterRepository {

    Spitter findOne(Long id);

    long save(Spitter spitter);

    List<Spitter> listAll();
}
