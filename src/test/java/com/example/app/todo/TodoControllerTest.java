package com.example.app.todo;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository repository;

    @BeforeEach
    void clearDatabase() {
        repository.deleteAll();
    }

    private Long createTodo(String title) {
        Todo saved = repository.save(new Todo(title));
        return saved.getId();
    }

    @Test
    void createsTodo() throws Exception {
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Buy milk\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("Buy milk"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void rejectsBlankTitle() throws Exception {
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"  \"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listsTodos() throws Exception {
        createTodo("First");
        createTodo("Second");

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("First"));
    }

    @Test
    void marksTodoDone() throws Exception {
        Long id = createTodo("Walk the dog");

        mockMvc.perform(patch("/todos/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"done\": true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.done").value(true))
                .andExpect(jsonPath("$.title").value("Walk the dog"));

        mockMvc.perform(get("/todos/" + id))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void deletesTodo() throws Exception {
        Long id = createTodo("Temporary");

        mockMvc.perform(delete("/todos/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/todos/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void returnsNotFoundForMissingTodo() throws Exception {
        mockMvc.perform(get("/todos/9999"))
                .andExpect(status().isNotFound());
    }
}
