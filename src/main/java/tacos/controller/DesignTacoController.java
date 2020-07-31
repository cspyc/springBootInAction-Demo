package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Taco;
import tacos.repository.IngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientRepository.findAll().forEach(in->ingredientList.add(in));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientList, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco tacoDesign, Errors errors){
        if(errors.hasErrors()){
            return "design";
        }
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
