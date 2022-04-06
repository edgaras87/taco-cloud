package com.jon.tacocloud.data;

import com.jon.tacocloud.Taco;

import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
