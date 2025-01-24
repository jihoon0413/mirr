package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.dto.GameDto;
import side.project.mirr.dto.QuarterDto;
import side.project.mirr.dto.request.GameRequest;
import side.project.mirr.service.GameService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/findGame")
    @ResponseBody
    public List<GameDto> findOne(Model model) {
        return gameService.findMatchByMonth();
    }


    @GetMapping("/findMatchByMonth")
    public String findMatchByMonth(Model model) {
        List<GameDto> gameDtos = gameService.findMatchByMonth();
        model.addAttribute("gameList", gameDtos);
        return "page/score :: gameTableFragment";
    }

    @PostMapping("/newGame")
    public String saveGame(GameRequest gameDto) {
        log.info("통신 성공");

        gameService.saveGame(GameDto.of(gameDto.stadium(), gameDto.matchDay()));
        return "redirect:/";
    }

    @GetMapping("/detail/{gameId}")
    public String detail(@PathVariable("gameId") Long gameId,
                         Model model) {
        List<QuarterDto> quarterDtos = gameService.findQuarter(gameId);
        model.addAttribute("gameDetailList", quarterDtos);
        return "page/detail";
    }

}
