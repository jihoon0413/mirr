package side.project.mirr.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import side.project.mirr.dto.GameDto;
import side.project.mirr.dto.request.GameRequest;
import side.project.mirr.dto.response.Response;
import side.project.mirr.dto.response.IdResponse;
import side.project.mirr.service.GameService;

@Slf4j
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @Operation(summary = "새로운 게임 추가")
    @PostMapping
    public Response<IdResponse> saveGame(@RequestBody GameRequest gameDto) {
        IdResponse result = IdResponse.of(gameService.saveGame(GameDto.of(gameDto.stadium(), gameDto.matchDay())));
        return Response.success(result);
    }

    //TODO: 현재 달만 조회하도록 개선

    @GetMapping("/findAll")
    @ResponseBody
    public Response<List<GameDto>> findOne(Model model) {
        return Response.success(gameService.findMatchByMonth());
    }

    @Operation(summary = "게임 정보 삭제")
    @DeleteMapping("/{gameId}")
    public Response<Void> deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
        return Response.success();
    }

}
