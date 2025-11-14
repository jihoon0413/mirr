package side.project.mirr.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.dto.GameDto;
import side.project.mirr.dto.request.GameRequest;
import side.project.mirr.service.GameService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @Operation(summary = "새로운 게임 추가")
    @PostMapping
    public ResponseEntity<String> saveGame(@RequestBody GameRequest gameDto) {

        gameService.saveGame(GameDto.of(gameDto.stadium(), gameDto.matchDay()));
        return ResponseEntity.ok("ok");
    }

    //TODO: 현재 달만 조회하도록 개선

    @GetMapping("/findAll")
    @ResponseBody
    public List<GameDto> findOne(Model model) {
        return gameService.findMatchByMonth();
    }

    @Operation(summary = "게임 정보 삭제")
    @DeleteMapping("/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.ok("/");
    }

}
