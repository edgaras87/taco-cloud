package com.jon.tacocloud;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table // (Ingredient) by default
@AllArgsConstructor // Lombok generates an all-args constructor
/**
 * Lombok will generate a no arguments/default constructor, by default generated constructor will be public.
 * 
 * force attribute - will get a compiler error if class has any non initialized final fields.
 * access attribute - allows you to change the access modifier of the generated constructor
 *
 *  https://javabydeveloper.com/lombok-noargsconstructor-examples/
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true) 
public class Ingredient implements Persistable<String> {

  @Id
  private final String id;
  // @Column("name") by default
  private final String name;
  private final Type type;

  @Override
  public boolean isNew() {
    return true;
  }

  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
