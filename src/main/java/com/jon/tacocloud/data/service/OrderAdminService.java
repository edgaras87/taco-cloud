package com.jon.tacocloud.data.service;

import com.jon.tacocloud.data.OrderRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class OrderAdminService {

  private OrderRepository orderRepository;

  public OrderAdminService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void deleteAllOrders() {
    orderRepository.deleteAll();
  }

}
