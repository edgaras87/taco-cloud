package com.jon.tacocloud;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AccessLevel;

@Data
@Document // (collection="ingredient") default
@AllArgsConstructor
// does not work with this!!!  @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)  
public class Ingredient {

  @Id // must be Serializable, String is
  private final String id;
  private final String name;
  private final Type type;

  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
