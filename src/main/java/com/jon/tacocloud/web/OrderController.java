package com.jon.tacocloud.web;

// 1. import java.security.Principal;
// 2. import org.springframework.security.core.Authentication;
import javax.validation.Valid;

import com.jon.tacocloud.TacoOrder;
import com.jon.tacocloud.User;
import com.jon.tacocloud.data.OrderRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderProps props;
    private OrderRepository orderRepo;
    //1. private UserRepository userRepository;
   
    public OrderController(OrderRepository orderRepo, 
                        OrderProps props) {
        this.orderRepo = orderRepo;
        this.props = props;
        //1. this.userRepository = userRepositor;
    }

    @GetMapping("/current")
    public String orderForm(@ModelAttribute TacoOrder tacoOrder,
            @AuthenticationPrincipal User user
            //1. Principal principal
            //2. Authentication authentication
            ) {
        //1. User user = userRepository.findByUsername(principal.getName());
        //2. User user = (User) authentication.getPrincipal();

        if (tacoOrder.getDeliveryName() == null) {
            tacoOrder.setDeliveryName(user.getFullname());
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user
            //1. Principal principal
            //2. Authentication authentication
            ) {
        // if (errors.hasErrors()) {
        // return "orderForm";
        // }

        //1. User user = userRepository.findByUsername(principal.getName());
        //2. User user = (User) authentication.getPrincipal();
        //3. Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //3. User user = (User) authentication.getPrincipal();

        order.setUser(user);

        log.info("Order submitted: {}", order);
        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders", orderRepo.findByUser(user, pageable));
        return "orderList";
    }

}