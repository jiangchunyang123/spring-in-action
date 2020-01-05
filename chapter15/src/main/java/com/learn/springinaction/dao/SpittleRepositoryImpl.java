package com.learn.springinaction.dao;


import com.learn.springinaction.model.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return createSpittleList(20);
    }

    @Override
    public Spittle findOne(Long spittleId) {
        return null;
    }

    @Override
    public void save(Spittle spittle) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator preparedStatementCreator = connection -> {
            //设置返回主键的字段名
            String sql = "insert into spittle (message,time,latitude,longitude) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, spittle.getMessage());
            preparedStatement.setDate(2, new java.sql.Date(spittle.getTime().getTime()));
            preparedStatement.setDouble(3, spittle.getLatitude());
            preparedStatement.setDouble(4, spittle.getLongitude());
            return preparedStatement;
        };
        jdbcOperations.update(preparedStatementCreator, keyHolder);
    }

    private List<Spittle> createSpittleList(int number) {
        List<Spittle> spittles = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            spittles.add(new Spittle((long) i, "Spittle" + i, new Date()));
        }
        return spittles;
    }
}
