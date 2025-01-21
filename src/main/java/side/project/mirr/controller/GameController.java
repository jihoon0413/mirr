package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import side.project.mirr.dto.GameDto;
import side.project.mirr.service.GameService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/findMatchByMonth")
    public String findMatchByMonth(Model model) {
        List<GameDto> gameDtos = gameService.findMatchByMonth();
        model.addAttribute("gameList", gameDtos);
        return "page/score :: gameTableFragment";

    }

}
