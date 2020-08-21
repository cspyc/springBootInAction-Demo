package tacos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import tacos.data.IngredientSpringDataRepository;
import tacos.data.OrderSpringDataRepository;
import tacos.data.TacoSpringDataRepository;
import tacos.data.UserRepository;
import tacos.model.Ingredient;
import tacos.model.Taco;
import tacos.model.Ingredient.Type;
import tacos.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Taco design;
    private List<Ingredient> ingredients;

    @MockBean
    private IngredientSpringDataRepository ingredientRepository;

    @MockBean
    private TacoSpringDataRepository tacoRepository;

    @MockBean
    private OrderSpringDataRepository orderRepository;

    @MockBean
    private UserRepository userRepository;



    @BeforeTestClass
    public void setUp() {
        ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        //通过Mockito框架，模拟相关方法返回值  FAKE
        when(ingredientRepository.findAll())
                .thenReturn(ingredients);

        when(ingredientRepository.findById("FLTO")).thenReturn(Optional.of(new Ingredient("FLTO","Flour Tortilla",Type.WRAP)));
        when(ingredientRepository.findById("GRBF")).thenReturn(Optional.of(new Ingredient("GRBF","Ground Beef",Type.PROTEIN)));
        when(ingredientRepository.findById("CHEN")).thenReturn(Optional.of(new Ingredient("CHED","Cheddar",Type.CHEESE)));

        design = new Taco();
        design.setIngredients(Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CHED", "Cheddar", Type.CHEESE)
        ));

        when(userRepository.findByUsername("testuser"))
                .thenReturn(new User("testuser","testpass","Test User", "123 Street", "Someville", "CO", "12345", "123-123-1234"));
    }

    @Test
    public void testDesignTacoController() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"));
    }

    @Test
    @WithMockUser(username="testuser", password="testpass", authorities="ROLE_USER")
    public void testProcessDesign() throws Exception {
        when(tacoRepository.save(design))
                .thenReturn(design);
        mockMvc.perform(post("/design").with(csrf())
                    .content("name=Test+Taco&ingredients=FLTO,GRBF,CHED")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location","/orders/current"));

    }
}
