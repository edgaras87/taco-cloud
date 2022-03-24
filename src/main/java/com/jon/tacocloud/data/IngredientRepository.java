package com.jon.tacocloud.data;

import java.util.Optional;

import com.jon.tacocloud.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();
    
    Optional<Ingredient> findById(String id);
    
    Ingredient save(Ingredient ingredient);
    
  }