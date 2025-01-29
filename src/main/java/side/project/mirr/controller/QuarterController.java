package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.dto.QuarterDto;
import side.project.mirr.dto.request.QuarterRequest;
import side.project.mirr.service.QuarterService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/quarter")
@RequiredArgsConstructor
public class QuarterController {

    private final QuarterService quarterService;

    @GetMapping("/detail/{gameId}")
    public String detail(@PathVariable("gameId") Long gameId,
                         Model model) {
        List<QuarterDto> quarterDtos = quarterService.findQuarter(gameId);
        model.addAttribute("quarterDTO", new QuarterDto(0L,0,null,0,0));
        model.addAttribute("gameId", gameId);
        model.addAttribute("quarterRequest", new QuarterRequest(0L, 0, "", 0, 0));
        model.addAttribute("gameDetailList", quarterDtos);
        return "page/detail";
    }

    @PostMapping("/newQuarter")
    public String newQuarter(QuarterRequest quarterDto) {

        quarterService.saveQuarter(quarterDto);
        return "redirect:/quarter/detail/" + quarterDto.gameId();
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
