package com.learn.springinaction.dao;

import com.learn.springinaction.model.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Spitter findOne(Long id) {
        return null;
    }

    @Override
    public long save(Spitter spitter) {
        return 0;
    }

    @Override
    public List<Spitter> listAll() {
        return null;
    }
}
