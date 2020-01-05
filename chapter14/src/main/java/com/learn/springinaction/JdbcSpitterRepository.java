package com.learn.springinaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {
    private static final String INSERT_SPITTTER = "insert into spitter (username,password,firstname,lastname) values (?,?,?,?);";
    private static final String FIND_SPITTER_BY_ID = "select * from spitter where id =?;";
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    /**
     * lambda表达式
     *
     * @param spittleId
     * @return
     */
    @Override
    @PostAuthorize("returnObject.username == principal.username")
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

    @Override
    @PreAuthorize("hasAnyRole('ROLE_SPITTER','ROLE_ADMIN')")
    @PostFilter("hasRole('ROLE_ADMIN') || filterObject.username == principal.username")
    public List<Spitter> getOffensiveSpittles() {
        return jdbcOperations.query("select * from spitter",
                (rs, rownum) ->
                        new Spitter(rs.getLong("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("firstname"),
                                rs.getString("lastname")));
    }

    public void deleteSpitters(List<Spitter> spitters) {

    }

    /**
     * 基于jdbcTemplate的保存方法如下
     *
     * @param spitter
     * @return
     */
    @Override
    @Secured("ROLE_SPITTER")
    @PreAuthorize("hasRole('ROLL_SPITTER' and #spitter.username.length()<140)")
    public long save(Spitter spitter) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            //设置返回主键的字段名
            PreparedStatement ps = connection.prepareStatement(INSERT_SPITTTER, new String[]{"id"});
            ps.setString(1, spitter.getUsername());
            ps.setString(2, spitter.getPassword());
            ps.setString(3, spitter.getFirstname());
            ps.setString(4, spitter.getLastname());
            return ps;
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

}
