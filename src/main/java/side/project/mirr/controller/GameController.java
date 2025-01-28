package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/newGame")
    public String saveGame(GameRequest gameDto) {

        gameService.saveGame(GameDto.of(gameDto.stadium(), gameDto.matchDay()));
        return "redirect:/";
    }

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


    @PostMapping("delete/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.ok("/");
    }

    @GetMapping("/detail/{gameId}")
    public String detail(@PathVariable("gameId") Long gameId,
                         Model model) {
        List<QuarterDto> quarterDtos = gameService.findQuarter(gameId);
        model.addAttribute("gameId", gameId);
        model.addAttribute("gameDetailList", quarterDtos);
        return "page/detail";
    }

}
