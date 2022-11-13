package org.example.spring_app.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.spring_app.User.Username;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Username.class)
public class AuthenticationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturnUser() throws Exception {
        mockMvc.perform(get("/api/username").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(content().string("admin"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldReturnError() throws Exception {
        mockMvc.perform(get("/api/username").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isForbidden());
    }
}
