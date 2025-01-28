package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.PointService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping("/getGoalRanking")
    public String getGoalRanking(Model model) {
        List<RankingResponse> goalRanking = pointService.getGoalRanking();
        model.addAttribute("rankList", goalRanking);
        return "page/score :: gameTableFragment";
    }

    @GetMapping("/getAssistRanking")
    public String getAssistRanking(Model model) {
        List<RankingResponse> assistRanking = pointService.getAssistRanking();
        model.addAttribute("rankList", assistRanking);
        return "page/assist :: gameTableFragment";
    }

}
