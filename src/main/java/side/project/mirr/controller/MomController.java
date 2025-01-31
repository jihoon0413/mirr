package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.service.MomService;

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
}
