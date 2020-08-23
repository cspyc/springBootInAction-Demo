package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.configure.OrderProps;
import tacos.data.UserRepository;
import tacos.model.Order;
import tacos.model.User;
import tacos.repository.OrderRepository;

import javax.validation.Valid;
import java.awt.print.Pageable;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")

public class OrderController {

    private OrderProps orderProps;
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository,
                           UserRepository userRepository,
                           OrderProps orderProps) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderProps = orderProps;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = (Pageable) PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @PostMapping
    public String processOrders(@Valid Order order, Errors errors,
                                SessionStatus sessionStatus,
                                @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
