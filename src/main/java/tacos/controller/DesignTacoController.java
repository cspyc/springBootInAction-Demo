package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.data.IngredientSpringDataRepository;
import tacos.data.TacoSpringDataRepository;
import tacos.data.UserRepository;
import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.model.User;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientSpringDataRepository ingredientRepository;
    private TacoSpringDataRepository designRepo;
    private UserRepository userRepository;

    @Autowired
    public DesignTacoController(IngredientSpringDataRepository ingredientRepository,
                                TacoSpringDataRepository designRepo,
                                UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.designRepo = designRepo;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        log.info("   ---Designing taco");
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientRepository.findAll().forEach(in -> ingredientList.add(in));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientList, type));
        }

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco tacoDesign, Errors errors,
                                @ModelAttribute Order order) {
        log.info("   -----saving taco");
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saved = designRepo.save(tacoDesign);
        order.addDesign(saved);

        log.info("Processing design: " + tacoDesign);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredientList, Type type) {
        return ingredientList
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
