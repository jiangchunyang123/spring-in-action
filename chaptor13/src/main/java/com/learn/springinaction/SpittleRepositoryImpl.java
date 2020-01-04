package com.learn.springinaction;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;

@Service
public class SpittleRepositoryImpl implements SpittleRepository {
    private Logger logger = LoggerFactory.getLogger(SpittleRepositoryImpl.class);


    private static final String FIND_SPITTLE_BY_ID = "SELECT * from spittle where id = ?";

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Spittle findOne(Long spittleId) {
        Spittle spittle = jdbcOperations.queryForObject(FIND_SPITTLE_BY_ID,
                (rs, rownum) ->
                        new Spittle(rs.getLong("id"),
                                rs.getString("message"),
                                rs.getDate("time")
                        ),
                spittleId);
        logger.info("method has been invoked !");
        return spittle;
    }

    @Override
    @Cacheable(value = "spittleCache", unless = "result.message.contains('noCache')")
    public Spittle findOneWithCache(Long spittleId) {
        Spittle spittle = jdbcOperations.queryForObject(FIND_SPITTLE_BY_ID,
                (rs, rownum) ->
                        new Spittle(rs.getLong("id"),
                                rs.getString("message"),
                                rs.getDate("time")
                        ),
                spittleId);
        logger.info("method has been invoked !");
        return spittle;
    }

    /**
     * 基于jdbcTemplate的保存方法如下
     *
     * @param spittle
     * @return
     */
    @Override
    public long save(Spittle spittle) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator preparedStatementCreator = connection -> {
            //设置返回主键的字段名
            String sql = "insert into spittle (message,time) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, spittle.getMessage());
            preparedStatement.setDate(2, new java.sql.Date(spittle.getTime().getTime()));
            return preparedStatement;
        };
        jdbcOperations.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    @CachePut(value = "spittleCache")
    public Spittle saveWithCache(Spittle spittle) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator preparedStatementCreator = connection -> {
            //设置返回主键的字段名
            String sql = "insert into spittle (message,time) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, spittle.getMessage());
            preparedStatement.setDate(2, new java.sql.Date(spittle.getTime().getTime()));
            return preparedStatement;
        };
        jdbcOperations.update(preparedStatementCreator, keyHolder);
        return spittle;
    }

    @Override
    @CacheEvict(value = "spittleCache")
    public void remove(Long id) {
        jdbcOperations.update("delete from spittle where id =" + id);
    }
}
