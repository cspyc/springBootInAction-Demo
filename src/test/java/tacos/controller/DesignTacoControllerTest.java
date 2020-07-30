package tacos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import tacos.model.Taco;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testDesignTacoController() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"));
    }

    @Test
    public void testProcessDesign() throws Exception {
        MvcResult result = mockMvc.perform(post("/design")
                .content(objectMapper.writeValueAsString(new Taco())))
                .andExpect(view().name("redirect:/orders/current"))
                .andReturn();

        //https://www.jianshu.com/p/91045b0415f0
       ResultMatcher.matchAll(redirectedUrl(result.getResponse().getRedirectedUrl()));
    }
}
