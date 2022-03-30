package com.jon.tacocloud.data;

import com.jon.tacocloud.User;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
}
