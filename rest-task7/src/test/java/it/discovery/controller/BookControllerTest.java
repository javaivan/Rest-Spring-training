package it.discovery.controller;

import it.discovery.model.Book;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



import com.fasterxml.jackson.databind.ObjectMapper;

import it.discovery.bootstrap.RestApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=RestApplication.class)
public class BookControllerTest {
	@Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(
        		applicationContext).build();
    }

    @Test
    public void findBooks_StorageIsEmpty_NoBooksAreReturn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/"))
                .andExpect(status().isOk())
                .andExpect(content()
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", Matchers.hasSize(0)));

    }

    @Test
    public void saveBook_validBook_BookIsReturned() throws Exception{
        Book book = new Book();
        book.setName("Java 8");

        mockMvc.perform(post("/book/").contentType(
                MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(MAPPER.writeValueAsBytes(book)))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.name",Matchers.equalTo("Java 8")));
    }
}

