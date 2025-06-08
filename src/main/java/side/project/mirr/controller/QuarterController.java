package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.dto.AttendDto;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.QuarterDto;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.request.QuarterRequest;
import side.project.mirr.service.AttendService;
import side.project.mirr.service.MomService;
import side.project.mirr.service.QuarterService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/quarter")
@RequiredArgsConstructor
public class QuarterController {

    private final QuarterService quarterService;
    private final MomService momService;
    private final AttendService attendService;

    @GetMapping("/detail/{gameId}")
    public String detail(@PathVariable("gameId") Long gameId,
                         Model model) {
        List<QuarterDto> quarterDtos = quarterService.findQuarter(gameId);
        List<PlayerDto> moms = momService.findMomsByGameId(gameId);
        List<AttendDto> attends = attendService.findPlayerByGameId(gameId);
        model.addAttribute("quarterDTO", new QuarterDto(0L,0,null,0,0));
        model.addAttribute("quarterRequest", new QuarterRequest(0L, 0, "", 0, 0));
        model.addAttribute("momRequest", new MomRequest(0L, null));
        model.addAttribute("gameId", gameId);
        model.addAttribute("gameDetailList", quarterDtos);
        model.addAttribute("moms", moms);
        model.addAttribute("attends", attends);
        return "page/quarter";
    }

    @PostMapping("/newQuarter")
    public String newQuarter(QuarterRequest quarterRequest) {
        quarterService.saveQuarter(quarterRequest);
        return "redirect:/quarter/detail/" + quarterRequest.gameId();
    }

    @GetMapping("/findById/{quarterId}")
    @ResponseBody
    public QuarterDto findById(@PathVariable("quarterId") Long quarterId){
        return quarterService.findQuarterById(quarterId);
    }

    @PostMapping("/update")
    public String updateQuarter(QuarterDto quarterDto) {
        Long gameId = quarterService.updateQuarter(quarterDto);
        return "redirect:/quarter/detail/" + gameId;
    }


    @PostMapping("/delete/{quarterId}")
    @ResponseBody
    public void deleteQuarter(@PathVariable("quarterId") Long quarterId) {
        quarterService.deleteQuarter(quarterId);
    }

}
