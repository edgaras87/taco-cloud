package com.jon.tacocloud;

import com.jon.tacocloud.Ingredient.Type;
import com.jon.tacocloud.data.IngredientRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
/*
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.context.annotation.Bean;
*/
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DevelopmentConfig {
    
    /*
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("taco_schema.sql")
            .addScripts("user_data.sql", "ingredient_data.sql")
            .build();
    }
    */
	/** CommandLineRunner and ApplicationRunner - uses the repositories to
	 *  create the persisted objects instead of a SQL script. This means 
	 * that they’ll work equally well for relational databases and nonrelational
	 *  databases */
	/** CommandLineRunner - accepts a String vararg, which is a raw 
	 * 		representation of arguments passed on the command line.	*/
	
	@Bean
	@Profile({"dev", "qa"})
	public CommandLineRunner dataLoaderCLR(IngredientRepository repo) {
		return args -> {
			repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		};
	}
	


	/** ApplicationRunner - accepts an ApplicationArguments parameter that
	 * 		offers methods for accessing the arguments as parsed components
	 * 		of the command line. */
	@Bean
	@Profile({"!dev"})
	public ApplicationRunner dataLoaderAR(IngredientRepository repo) {
		return args -> {
			repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		};
	}


}
