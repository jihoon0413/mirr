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
@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<String> newPlayer(PlayerDto playerDto) {
        playerService.savePlayer(playerDto);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<PlayerDto> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/{playerId}")
    @ResponseBody
    public PlayerDetailResponse getPlayerDetail(@PathVariable("playerId")Long playerId) {
        return playerService.getPlayerDetail(playerId);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> modifyPlayer(PlayerDto playerDto) {
        playerService.modifyPlayer(playerDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable("playerId") Long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok("ok");
    }
}
