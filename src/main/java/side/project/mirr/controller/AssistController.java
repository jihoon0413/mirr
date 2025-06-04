package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.PointService;

@Slf4j
@Controller
@RequestMapping("/assist")
@RequiredArgsConstructor
public class AssistController {

    private final PointService pointService;

    @GetMapping
    public String getAssistRanking(Model model,
                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<RankingResponse> assistRanking = pointService.getAssistRanking(page, size);
        if (assistRanking == null) {
            // 예외 처리 또는 기본값 설정
            assistRanking = Page.empty();
        }
        model.addAttribute("rankList", assistRanking);
        model.addAttribute("maxPage", 5);
        return "page/assist";
    }


}
