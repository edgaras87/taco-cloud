package com.jon.tacocloud.data;

import com.jon.tacocloud.Ingredient;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
