package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public String getMomRanking(Model model,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<RankingResponse> momRanking = momService.getMomRanking(page, size);
        model.addAttribute("rankList", momRanking);
        model.addAttribute("maxPage", 5);

        return "page/mom";

    }

    @PostMapping("/modify")
    public String modifyMom(MomRequest momRequest){
        momService.modify(momRequest);
        return "redirect:/quarter/detail/" + momRequest.gameId();
    }

//    @GetMapping("/getMomRanking")
//    public String getMomRanking(Model model, Pageable pageable) {
//        Page<RankingResponse> momRanking = momService.getMomRanking(pageable);
//        model.addAttribute("rankList", momRanking);
//        return "page/mom :: gameTableFragment";
//    }


}
