package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import side.project.mirr.service.PlayerService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final PlayerService playerService;

    @GetMapping("/")
    public String home() {
        playerService.savePlayer();
        return "index";
    }

}
