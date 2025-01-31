package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.service.PlayerService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/findAll")
    @ResponseBody
    public List<PlayerDto> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/search")
    @ResponseBody
    public List<PlayerDto> searchPlayers(@RequestParam String q) {
        return playerService.searchPlayers(q);
    }

}
