package app.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WordChainsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPaths() throws Exception {
        mockMvc
                .perform(get("/?initialWord=cat&lastWord=dog"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[cat, cot, cog, dog]")));

        mockMvc
                .perform(get("/?initialWord=lead&lastWord=gold"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[lead, load, goad, gold]")));

        mockMvc
                .perform(get("/?initialWord=ruby&lastWord=code"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[ruby, rube, robe, rode, code]")));

        mockMvc
                .perform(get("/?initialWord=&lastWord=code"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Invalid data! initialWord:  lastWord: code")));

        mockMvc
                .perform(get("/?initialWord=&lastWord="))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Invalid data! initialWord:  lastWord: ")));

        mockMvc
                .perform(get("/?initialWord=www&lastWord=zzzzzzzz"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Invalid data! Words length should be equal. initialWord: www lastWord: zzzzzzzz")));

        mockMvc
                .perform(get("/?initialWord=cot&lastWord=lead"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Invalid data! Words length should be equal. initialWord: cot lastWord: lead")));

        mockMvc
                .perform(get("/?initialWord=xyzxyz&lastWord=abcabc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Path not found. initialWord: xyzxyz lastWord: abcabc")));

        mockMvc
                .perform(get("/"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc
                .perform(get("/?lastWord=abcabc"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc
                .perform(get("/?initialWord="))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}