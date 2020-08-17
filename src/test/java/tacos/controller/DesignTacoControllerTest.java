//package tacos.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.event.annotation.BeforeTestClass;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import tacos.model.Taco;
//import tacos.repository.FakeIngredientRepositoryImpl;
//import tacos.repository.IngredientRepository;
//import tacos.repository.impl.JdbcTemplateIngredientRepository;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(DesignTacoController.class)
//public class DesignTacoControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private DesignTacoController designTacoController;
//
//
//    @BeforeTestClass
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        designTacoController = new DesignTacoController(new FakeIngredientRepositoryImpl());
//        this.mockMvc = MockMvcBuilders
//                .standaloneSetup(designTacoController)
//                .build();
//    }
//
//    @Test
//    public void testDesignTacoController() throws Exception {
//        mockMvc.perform(get("/design"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("design"));
//    }
//
//    @Test
//    public void testProcessDesign() throws Exception {
//        MvcResult result = mockMvc.perform(post("/design")
//                .content(objectMapper.writeValueAsString(new Taco())))
//                .andExpect(view().name("redirect:/orders/current"))
//                .andReturn();
//
//        //https://www.jianshu.com/p/91045b0415f0
//        ResultMatcher.matchAll(redirectedUrl(result.getResponse().getRedirectedUrl()));
//    }
//}
