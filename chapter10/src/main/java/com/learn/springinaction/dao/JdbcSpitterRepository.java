package com.learn.springinaction.dao;

import com.learn.springinaction.model.Spitter;
import com.learn.springinaction.model.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {
    private static final String INSERT_SPITTTER = "insert into spitter (username,password,firstName,lastname) values (?,?,?,?);";
    private static final String FIND_SPITTER_BY_ID = "select * from spitter where id =?;";
    private JdbcOperations jdbcOperations;

    //TODO @Inject inject注解在java8中未找到，后面再看一下为什么

    @Autowired
    public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public List<Spittle> findSpittles(long max, int count) {
        return null;
    }

    /**
     * lambda表达式
     *
     * @param spittleId
     * @return
     */
    @Override
    public Spitter findOne(Long spittleId) {
        Spitter spitter = jdbcOperations.queryForObject(FIND_SPITTER_BY_ID,
                (rs, rownum) ->
                        new Spitter(rs.getLong("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("fullName"),
                                rs.getString("email")),
                spittleId);
        return spitter;
    }

    /**
     * 基于jdbcTemplate的保存方法如下
     *
     * @param spitter
     * @return
     */
    @Override
    public void save(Spitter spitter) {
        jdbcOperations.update(INSERT_SPITTTER, spitter.getUsername(), spitter.getPassword(),
                spitter.getFirstName(), spitter.getLastName());
    }

    /**
     * 使用命名参数
     */

}
