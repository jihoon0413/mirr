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
import side.project.mirr.dto.response.IdResponse;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.dto.response.Response;
import side.project.mirr.service.PointService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @PostMapping
    public Response<Void> saveNewPoint(@RequestBody PointRequest pointRequest) {
        pointService.saveNewPoint(pointRequest);
        return Response.success();
    }

    @DeleteMapping("/{pointId}")
    @ResponseBody
    public Response<Void> deleteById(@PathVariable("pointId") Long pointId) {
        pointService.deleteById(pointId);
        return Response.success();
    }

}
