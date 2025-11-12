package side.project.mirr.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import side.project.mirr.domain.eNum.PointType;
import side.project.mirr.domain.eNum.Position;
import side.project.mirr.dto.AttendDto;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.PointDto;
import side.project.mirr.dto.QuarterDto;
import side.project.mirr.dto.request.GameRequest;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.request.PointRequest;
import side.project.mirr.dto.request.QuarterRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.AttendService;
import side.project.mirr.service.MomService;
import side.project.mirr.service.PlayerService;
import side.project.mirr.service.PointService;
import side.project.mirr.service.QuarterService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final PointService pointService;
    private final AttendService attendService;
    private final MomService momService;
    private final PlayerService playerService;
    private final QuarterService quarterService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("gameDTO", new GameRequest("",""));
        return "page/index";
    }

    @Operation(summary = "게임 정보 조회",  description = "조회한 데이터를 표시할 뷰")
    @GetMapping("/quarter/detail/{gameId}")
    public String detail(@PathVariable("gameId") Long gameId,
                         Model model) {
        List<QuarterDto> quarterDtos = quarterService.findQuarter(gameId);
        List<PlayerDto> moms = momService.findMomsByGameId(gameId);
        List<AttendDto> attends = attendService.findPlayerByGameId(gameId);
        model.addAttribute("quarterDTO", new QuarterDto(0L,0,null,0,0));
        model.addAttribute("quarterRequest", new QuarterRequest(0L, 0, "", 0, 0));
        model.addAttribute("momRequest", new MomRequest(0L, null));
        model.addAttribute("gameId", gameId);
        model.addAttribute("gameDetailList", quarterDtos);
        model.addAttribute("moms", moms);
        model.addAttribute("attends", attends);
        return "page/quarter";
    }

    @GetMapping("/point/detail")
    public String getPointDetail(@RequestParam("quarterId") Long quarterId,
                                 @RequestParam("gameId") Long gameId,
                                 Model model) {
        List<PointDto> pointDtos = pointService.getPointByQuarter(quarterId);
        model.addAttribute("pointDetailList", pointDtos);
        model.addAttribute("gameId", gameId);
        model.addAttribute("quarterId", quarterId);
        model.addAttribute("pointRequest", new PointRequest(0L, null, PointType.GOAL));
        return "page/point";
    }

    @Operation(summary = "득점 기록 뷰 조회")
    @GetMapping("/score")
    public String getScoreRanking(Model model,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<RankingResponse> scoreRanking = pointService.getGoalRanking(page, size);
        model.addAttribute("rankList", scoreRanking);
        model.addAttribute("maxPage", 5);

        return "page/score";
    }

    @Operation(summary = "어시스트 기록 뷰 조회")
    @GetMapping("/assist")
    public String getAssistRanking(Model model,
                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<RankingResponse> assistRanking = pointService.getAssistRanking(page, size);
        if (assistRanking == null) {
            // 예외 처리 또는 기본값 설정
            assistRanking = Page.empty();
        }
        model.addAttribute("rankList", assistRanking);
        model.addAttribute("maxPage", 5);
        return "page/assist";
    }

    @Operation(summary = "참석자 기록 뷰 조회")
    @GetMapping("/attend")
    public String getAttendRanking(Model model,
                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size){
        Page<RankingResponse> attendRanking = attendService.getAttendRanking(page, size);
        model.addAttribute("rankList", attendRanking);
        model.addAttribute("maxPage", 5);
        return "page/attend";
    }


    @Operation(summary = "M.O.M 기록 뷰 조회")
    @GetMapping("/mom")
    public String getMomRanking(Model model,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<RankingResponse> momRanking = momService.getMomRanking(page, size);
        model.addAttribute("rankList", momRanking);
        model.addAttribute("maxPage", 5);

        return "page/mom";
    }

    @GetMapping("/players")
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
}
