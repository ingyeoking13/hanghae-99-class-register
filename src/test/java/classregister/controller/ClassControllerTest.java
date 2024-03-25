package classregister.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassController.class)
class ClassControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    void test_클래스_등록_성공() throws Exception {
        mockMvc.perform(post("/class?id=1").header(
                "Authorization", "1"
                ))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{" +
                                "\"statusCode\": 200, " +
                                "\"result\": \"OK\", " +
                                "\"message\": \"\"" +
                                "}")
                );
    }

    @Test
    void test_클래스_등록_실패() throws Exception {
        mockMvc.perform(post("/class?id=1").header(
                        "Authorization", "1"
                ))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{" +
                                "\"statusCode\": 403, " +
                                "\"result\": \"FAIL\", " +
                                "\"message\": \"인원수 제한 입니다.\"" +
                                "}")
                );
    }
}