package com.jon.tacocloud.data;

import com.jon.tacocloud.TacoOrder;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {

}
