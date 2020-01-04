package com.learn.springinaction;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

public interface SpittleRepository {

    Spittle findOne(Long spittleId);

    Spittle findOneWithCache(Long spittleId);

    long save(Spittle spittle);

    @CachePut(value = "spittleCache", key = "#result.id")
    Spittle saveWithCache(Spittle spittle);

    @CacheEvict()
    void remove(Long id);
}
