package side.project.mirr.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.service.MomService;

@Slf4j
@Controller
@RequestMapping("/mom")
@RequiredArgsConstructor
public class MomController {

    private final MomService momService;

    @Operation(summary = "M.O.M 수정")
    @PostMapping("/modify")
    public ResponseEntity<HttpStatus> modifyMom(MomRequest momRequest){
        momService.modify(momRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
