package com.learn.springinaction.hibernatejpa;

import com.learn.springinaction.domain.Spitter;

public interface SpitterRepository {
    Spitter findOne(Long id);

    long save(Spitter spitter);
}
