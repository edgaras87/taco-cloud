package com.jon.tacocloud.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;

import com.jon.tacocloud.Ingredient;
import com.jon.tacocloud.Taco;
import com.jon.tacocloud.TacoOrder;
import com.jon.tacocloud.User;
import com.jon.tacocloud.Ingredient.Type;
import com.jon.tacocloud.data.IngredientRepository;
import com.jon.tacocloud.data.UserRepository;

import org.springframework.validation.Errors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private UserRepository userRepo;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal){
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        return user;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(
            @Valid Taco taco, Errors errors,
            @ModelAttribute TacoOrder tacoOrder) {

        // if (errors.hasErrors()) {
        // return "design";
        // }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}