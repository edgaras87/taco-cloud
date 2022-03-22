package com.jon.tacocloud;

import java.util.List;

import lombok.Data;

@Data
public class Taco {
    private String name;
    List<Ingredient> ingredients;
}
