package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.service.AttendService;

@Slf4j
@Controller
@RequestMapping("/attend")
@RequiredArgsConstructor
public class AttendController {

    private final AttendService attendService;

    @PostMapping("/newAttend")
    public String newAttend(MomRequest attendRequest) {
        attendService.newAttends(attendRequest);
        return "redirect:/quarter/detail/" + attendRequest.gameId();
    }


    @PostMapping("/delete/{attendId}")
    @ResponseBody
    public void deleteAttend(@PathVariable("attendId") Long attendId) {
        attendService.deleteAttend(attendId);
    }

}
