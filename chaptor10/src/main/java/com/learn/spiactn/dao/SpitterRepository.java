package com.learn.spiactn.dao;

import com.learn.spiactn.model.Spitter;

public interface SpitterRepository {
    Spitter findOne(Long id);

    void save(Spitter spitter);
}
