package classregister.controller;

import classregister.service.ClassService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassController.class)
class ClassControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ClassService classService;

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
        Mockito.doThrow(
                new NullPointerException("")

        ).when(classService).registerClass(1L, 1L);
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

    @Test
    void test_getStauts() {
    }
}