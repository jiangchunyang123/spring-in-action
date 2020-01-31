package com.learn.springinaction.dao;

import com.learn.springinaction.model.Spittle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpittleRepositoryImpl implements SpittleRepository {

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return initSpittles(count);
    }

    List<Spittle> initSpittles(int count) {
        List<Spittle> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Spittle(Long.valueOf(i + ""),
                    "spittle-" + i, new Date()));
        }
        return list;
    }
}
