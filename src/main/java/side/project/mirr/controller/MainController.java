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
}
