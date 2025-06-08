package side.project.mirr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.domain.eNum.PointType;
import side.project.mirr.dto.PointDto;
import side.project.mirr.dto.request.PointRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.PointService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;


    @GetMapping("/detail")
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

    @PostMapping("/newPoint")
    public ResponseEntity<String> saveNewPoint(PointRequest pointRequest) {
        pointService.saveNewPoint(pointRequest);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/delete/{pointId}")
    @ResponseBody
    public void deleteById(@PathVariable("pointId") Long pointId) {
        pointService.deleteById(pointId);
    }

}
