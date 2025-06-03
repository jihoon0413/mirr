package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.project.mirr.dto.request.GameRequest;
import side.project.mirr.service.GameService;
import side.project.mirr.service.PlayerService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final PlayerService playerService;
    private final GameService gameService;

    //TODO: 현재 달의 매치만 가져오기
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("gameDTO", new GameRequest("",""));
        return "page/index";
    }

    @GetMapping("/score")
    public String score() {
        return "page/score";
    }

    @GetMapping("/assist")
    public String assist() {
        return "page/assist";
    }

    @GetMapping("/mom")
    public String mom() {
        return "page/mom";
    }

    @GetMapping("/attend")
    public String attend() {
        return "page/attend";
    }

    @GetMapping("/players")
    public String player() {return "page/players";}

}
