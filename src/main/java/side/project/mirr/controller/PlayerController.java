package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.PlayerService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public String getAllPlayers(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
                                Model model) {
        Page<PlayerDto> players = playerService.findAll(pageable);
        model.addAttribute("players", players);
        model.addAttribute("maxPage", 5);

        return "page/players";
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<PlayerDto> findAll() {
        return playerService.findAll();
    }

//    @GetMapping("/getAllPlayers")
//    public String findAll(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable, Model model) {
//        Page<PlayerDto> players = playerService.findAll(pageable);
//        model.addAttribute("players", players);
//        return "page/players :: gameTableFragment";
//    }

}
