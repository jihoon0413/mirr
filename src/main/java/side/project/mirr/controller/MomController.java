package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.MomService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mom")
@RequiredArgsConstructor
public class MomController {

    private final MomService momService;

    @PostMapping("/modify")
    public String modifyMom(MomRequest momRequest){
        momService.modify(momRequest);
        return "redirect:/quarter/detail/" + momRequest.gameId();
    }

    @GetMapping("/getMomRanking")
    public String getMomRanking(Model model) {
        List<RankingResponse> momRanking = momService.getMomRanking();
        model.addAttribute("rankList", momRanking);
        return "page/mom :: gameTableFragment";
    }


}
