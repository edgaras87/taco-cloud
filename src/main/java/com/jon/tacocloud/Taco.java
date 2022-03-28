package com.jon.tacocloud;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

import lombok.Data;

// Members of the aggregate as subdocument. Doesn’t need to be annotated as a @Document, nor does it need an @Id property
@Data
public class Taco implements Serializable {

    // what is this https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date createdAt = new Date();

    // @NotNull
    // @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    // @NotNull
    // @Size(min = 1, message = "You must choose at least 1 ingredient")
    List<Ingredient> ingredients;
}
