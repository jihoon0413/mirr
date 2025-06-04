package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.PointService;

@Slf4j
@Controller
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {

    private final PointService pointService;

    @GetMapping
    public String getScoreRanking(Model model,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<RankingResponse> scoreRanking = pointService.getGoalRanking(page, size);
        model.addAttribute("rankList", scoreRanking);
        model.addAttribute("maxPage", 5);

        return "page/score";

    }

}
