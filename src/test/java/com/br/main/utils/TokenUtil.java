package com.br.main.utils;

import com.br.main.controllers.dtos.AuthDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class TokenUtil {

    @Autowired
    private ObjectMapper objectMapper;

    public String obtainAccessToken(MockMvc mockMvc, String username, String password) throws Exception {

        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(username);
        authDTO.setPassword(password);

        String jsonBody = objectMapper.writeValueAsString(authDTO);

        ResultActions result = mockMvc
                .perform(post("/api/authenticate")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        return result.andReturn().getResponse().getContentAsString();
    }
}
