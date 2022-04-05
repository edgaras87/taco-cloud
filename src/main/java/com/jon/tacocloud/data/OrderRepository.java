package com.jon.tacocloud.data;

import java.util.List;

import com.jon.tacocloud.TacoOrder;
import com.jon.tacocloud.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {

        List<TacoOrder> findByUser(User user, Pageable pageable);

}
