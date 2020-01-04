package com.learn.springinaction.springdatajpa;

import com.learn.springinaction.domain.Spitter;
import org.springframework.data.repository.CrudRepository;

public interface JpaSpitterRepository extends CrudRepository<Spitter, Long> {
    Spitter findById(Long id);
}
