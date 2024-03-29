package classregister.controller;

import classregister.dto.BaseResponse;
import classregister.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassController {

    @Autowired private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping("class")
    public BaseResponse<String> registerClass(@RequestHeader("Authorization") Long user,
                                 @RequestParam("id") Long id) {
        try{
            classService.registerClass(user, id);
        }
        catch(NullPointerException e){
            return new BaseResponse<>(
                    403,
                    "인원수 제한 입니다.",
                    "FAIL"
            );
        }

        return new BaseResponse<>(
                200,
                "",
                "OK"
        );
    }

    @GetMapping("status")
    public BaseResponse<String> getStauts(
            @RequestParam("lecture_id") Long lectureId,
            @RequestParam("member_id") Long memberId) {
        boolean result = classService.getMemberStatus(lectureId, memberId);
        return new BaseResponse<>(
                200,
                "",
                result?"OK":"FAIL"
        );
    }

}
