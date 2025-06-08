package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.domain.eNum.Position;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.response.PlayerDetailResponse;
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
        List<Position> positions = playerService.getAllPos();
        model.addAttribute("players", players);
        model.addAttribute("maxPage", 5);
        model.addAttribute("positions", positions);
        model.addAttribute("playerDTO", new PlayerDto(0L, "", null, 0));

        return "page/players";
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<PlayerDto> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/playerDetail/{playerId}")
    @ResponseBody
    public PlayerDetailResponse getPlayerDetail(@PathVariable("playerId")Long playerId) {
        return playerService.getPlayerDetail(playerId);
    }

    @PostMapping("/newPlayer")
    public String newPlayer(PlayerDto playerDto) {
        playerService.savePlayer(playerDto);
        return "redirect:/players";
    }

    @PostMapping("/modifyPlayer")
    public ResponseEntity<HttpStatus> modifyPlayer(PlayerDto playerDto) {
        playerService.modifyPlayer(playerDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/deletePlayer/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable("playerId") Long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok("ok");
    }
}
