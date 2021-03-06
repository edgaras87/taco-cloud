package com.jon.tacocloud;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity // (name = taco) default
// default @Table(name = Entity name)
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date createdAt;

  // @NotNull
  // @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;
  // @NotNull
  // @Size(min = 1, message = "You must choose at least 1 ingredient")
  @ManyToMany(targetEntity = Ingredient.class)
  private List<Ingredient> ingredients;

  // method should be called before the entity is inserted (persisted) into the database.
  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
}
