package com.learn.springinaction.dao;

import com.learn.springinaction.model.Spitter;

public interface SpitterRepository {
    Spitter findOne(Long id);

    void save(Spitter spitter);
}
