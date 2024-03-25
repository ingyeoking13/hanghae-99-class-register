package classregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatusController {
    @GetMapping("status")
    public String getStauts(
            @RequestParam("class_id") String classId,
            @RequestParam("user_id") String userId) {
        return "";
    }
}
