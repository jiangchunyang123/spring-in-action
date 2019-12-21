package com.learn.springinaction.neo4j;

import com.learn.springinaction.domain.Order;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * 混合实现repository查询功能
 */
public interface OrderMixRepository extends GraphRepository<Order>, OrderOperations {
}
