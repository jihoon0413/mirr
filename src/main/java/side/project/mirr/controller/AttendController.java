package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.AttendService;

@Slf4j
@Controller
@RequestMapping("/attend")
@RequiredArgsConstructor
public class AttendController {

    private final AttendService attendService;

    @GetMapping
    public String getAttendRanking(Model model,
                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size){
        Page<RankingResponse> attendRanking = attendService.getAttendRanking(page, size);
        model.addAttribute("rankList", attendRanking);
        model.addAttribute("maxPage", 5);
        return "page/attend";
    }

    @PostMapping("/newAttend")
    public ResponseEntity<HttpStatus> newAttend(MomRequest attendRequest) {
        attendService.newAttends(attendRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    //TODO: 같은 경기에 참석자 중복 저장되지 않도록 하기
    //TODO: 참석자 체크박스로 한번에 추가하기

    @PostMapping("/delete/{attendId}")
    @ResponseBody
    public void deleteAttend(@PathVariable("attendId") Long attendId) {
        attendService.deleteAttend(attendId);
    }

}
