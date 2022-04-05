package com.jon.tacocloud.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PropertySource("classpath:application.yml")
public class HomeController {
    
    @Value("${myVar}")
    private String property;

    @GetMapping("/")
    public String home() {
        System.out.println("=====================");
        System.out.println(property);
        System.out.println("=====================");
        return "home";
    }

}
