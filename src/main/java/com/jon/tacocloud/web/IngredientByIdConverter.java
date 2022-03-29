package com.jon.tacocloud.web;

import com.jon.tacocloud.Ingredient;
import com.jon.tacocloud.data.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;


public class IngredientByIdConverter implements Converter<String, Ingredient> {

        private IngredientRepository ingredientRepo;

        @Autowired
        public IngredientByIdConverter(IngredientRepository ingredientRepo) {
                this.ingredientRepo = ingredientRepo;
        }

        @Override
        public Ingredient convert(String id) {
                return ingredientRepo.findById(id).orElse(null);
        }

}