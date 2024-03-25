package classregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassController {
    @PostMapping("class")
    public String getClass(@RequestHeader("Authorization") String user,
                           @RequestParam("id") String id ) {
        return "";
    }

}
