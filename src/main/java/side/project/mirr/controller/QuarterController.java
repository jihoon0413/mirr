package side.project.mirr.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import side.project.mirr.dto.AttendDto;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.QuarterDto;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.request.QuarterRequest;
import side.project.mirr.dto.response.IdResponse;
import side.project.mirr.dto.response.Response;
import side.project.mirr.service.AttendService;
import side.project.mirr.service.MomService;
import side.project.mirr.service.QuarterService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/quarter")
@RequiredArgsConstructor
public class QuarterController {

    private final QuarterService quarterService;

    @Operation(summary = "새로운 쿼터 추가")
    @PostMapping
    public Response<IdResponse> newQuarter(@RequestBody QuarterRequest quarterRequest) {
        IdResponse result = IdResponse.of(quarterService.saveQuarter(quarterRequest));
        return Response.success(result);
    }

    @Operation(summary = "쿼터 정보 조회", description = "쿼터 정보를 수정할 때 사용")
    @GetMapping("/{quarterId}")
    @ResponseBody
    public Response<QuarterDto> findById(@PathVariable("quarterId") Long quarterId){
        return Response.success(quarterService.findQuarterById(quarterId));
    }

    @Operation(summary = "쿼터 정보 업데이트")
    @PutMapping
    public Response<IdResponse> updateQuarter(@RequestBody QuarterDto quarterDto) {
        IdResponse result = IdResponse.of(quarterService.updateQuarter(quarterDto));
        return Response.success(result);
    }

    @DeleteMapping("/{quarterId}")
    @ResponseBody
    public Response<Void> deleteQuarter(@PathVariable("quarterId") Long quarterId) {
        quarterService.deleteQuarter(quarterId);
        return Response.success();
    }

}
