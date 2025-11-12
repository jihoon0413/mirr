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
    @PostMapping("/newQuarter")
    public ResponseEntity<HttpStatus> newQuarter(QuarterRequest quarterRequest) {
        quarterService.saveQuarter(quarterRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "쿼터 정보 조회", description = "쿼터 정보를 수정할 때 사용")
    @GetMapping("/findById/{quarterId}")
    @ResponseBody
    public QuarterDto findById(@PathVariable("quarterId") Long quarterId){
        return quarterService.findQuarterById(quarterId);
    }

    @Operation(summary = "쿼터 정보 업데이트")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateQuarter(QuarterDto quarterDto) {
        Long gameId = quarterService.updateQuarter(quarterDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/delete/{quarterId}")
    @ResponseBody
    public void deleteQuarter(@PathVariable("quarterId") Long quarterId) {
        quarterService.deleteQuarter(quarterId);
    }

}
