package com.learn.springinaction;


import java.util.List;

public interface SpitterRepository {

    Spitter findOne(Long id);


    List<Spitter> getOffensiveSpittles();

    long save(Spitter spitter);

    List<Spitter> listAll();
}
