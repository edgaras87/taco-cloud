package com.jon.tacocloud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Table // (Taco) by default
public class Taco implements Serializable {

    // what is this
    // https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    // @Column("created_at") by default
    private Date createdAt = new Date();

    // @NotNull
    // @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    // @NotNull
    // @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient taco) {
        this.ingredients.add(new IngredientRef(taco.getId()));
    }

}
