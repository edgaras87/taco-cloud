package com.jon.tacocloud;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Data
@Entity // (name = ingredient) default
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
// default @Table(name = Entity name)
public class Ingredient {
  
  @Id
  private final String id;
  private final String name;
  @Enumerated(EnumType.STRING)
  private final Type type;
    
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
