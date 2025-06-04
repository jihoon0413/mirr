package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public String newAttend(MomRequest attendRequest) {
        attendService.newAttends(attendRequest);
        return "redirect:/quarter/detail/" + attendRequest.gameId();
    }
    //TODO: 참석자 체크박스로 한번에 추가하기
    //TODO: 득점, 어시스트 기록후 게임디테일 화면으로 올 수 있는 버튼 추가

//    @GetMapping("/getAttendRanking")
//    public String getAttendRanking(Model model, Pageable pageable) {
//        Page<RankingResponse> attendRanking = attendService.getAttendRanking(pageable);
//        model.addAttribute("rankList", attendRanking);
//        return "page/attend :: gameTableFragment";
//    }


    @PostMapping("/delete/{attendId}")
    @ResponseBody
    public void deleteAttend(@PathVariable("attendId") Long attendId) {
        attendService.deleteAttend(attendId);
    }

}
