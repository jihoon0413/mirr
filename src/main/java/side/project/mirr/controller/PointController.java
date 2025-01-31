package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.domain.eNum.PointType;
import side.project.mirr.dto.PointDto;
import side.project.mirr.dto.request.PointRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.PointService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping("/detail/{quarterId}")
    public String getPointDetail(@PathVariable("quarterId") Long quarterId,
                                 Model model) {
        List<PointDto> pointDtos = pointService.getPointByQuarter(quarterId);
        model.addAttribute("pointDetailList", pointDtos);
        model.addAttribute("quarterId", quarterId);
        model.addAttribute("pointRequest", new PointRequest(0L, 0L, PointType.GOAL));
        return "page/point";
    }

    @PostMapping("/newPoint")
    public String saveNewPoint(PointRequest pointRequest) {
        pointService.saveNewPoint(pointRequest);
        return "redirect:/point/detail/" + pointRequest.quarterId();
    }


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

    @PostMapping("/delete/{pointId}")
    @ResponseBody
    public void deleteById(@PathVariable("pointId") Long pointId) {
        pointService.deleteById(pointId);
    }

}
