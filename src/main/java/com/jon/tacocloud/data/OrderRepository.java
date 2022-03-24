package com.jon.tacocloud.data;

import java.util.Optional;

import com.jon.tacocloud.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
  
    Optional<TacoOrder> findById(Long id);
  
  }
