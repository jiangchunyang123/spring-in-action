package com.learn.springinaction.dao;

import com.learn.springinaction.model.Spitter;
import com.learn.springinaction.model.Spittle;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {
    private static final String INSERT_SPITTTER = "insert into spitter (username,password,firstname,lastname) values (?,?,?,?);";
    private static final String FIND_SPITTER_BY_ID = "select * from spitter where id =?;";
    private JdbcOperations jdbcOperations;

    @Inject
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
        return jdbcOperations.queryForObject(FIND_SPITTER_BY_ID,
                (rs, rownum) ->
                        new Spitter(rs.getLong("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("firstname"),
                                rs.getString("lastname")),
                spittleId);
    }

    /**
     * 基于jdbcTemplate的保存方法如下
     *
     * @param spitter
     * @return
     */
    @Override
    public long save(Spitter spitter) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into spitter (username,password,firstname,lastname) values ('" +
                spitter.getUsername() + "','" + spitter.getPassword() +
                "','" + spitter.getFirstname() + "','" + spitter.getLastname() + "');";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            //设置返回主键的字段名
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            return preparedStatement;
        };
        jdbcOperations.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Spitter> listAll() {
        return jdbcOperations.query("select * from spitter",
                (rs, rownum) ->
                        new Spitter(rs.getLong("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("firstname"),
                                rs.getString("lastname")));
    }

    /**
     * 使用命名参数
     */

}
