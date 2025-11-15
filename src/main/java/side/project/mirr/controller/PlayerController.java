package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.response.PlayerDetailResponse;
import side.project.mirr.dto.response.Response;
import side.project.mirr.dto.response.IdResponse;
import side.project.mirr.service.PlayerService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public Response<IdResponse> newPlayer(@RequestBody PlayerDto playerDto) {
        IdResponse result = IdResponse.of(playerService.savePlayer(playerDto));
        return Response.success(result);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Response<List<PlayerDto>> findAll() {
        return Response.success(playerService.findAll());
    }

    @GetMapping("/{playerId}")
    @ResponseBody
    public Response<PlayerDetailResponse> getPlayerDetail(@PathVariable("playerId")Long playerId) {
        return Response.success(playerService.getPlayerDetail(playerId));
    }

    @PutMapping
    public Response<IdResponse> modifyPlayer(@RequestBody PlayerDto playerDto) {
        IdResponse result = IdResponse.of(playerService.modifyPlayer(playerDto));
        return Response.success(result);
    }

    @DeleteMapping("/{playerId}")
    public Response<Void> deletePlayer(@PathVariable("playerId") Long playerId) {
        playerService.deletePlayer(playerId);
        return Response.success();
    }
}
