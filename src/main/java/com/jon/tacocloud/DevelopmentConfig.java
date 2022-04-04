package com.jon.tacocloud;

/*
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.context.annotation.Bean;
*/
import org.springframework.context.annotation.Configuration;

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


}
