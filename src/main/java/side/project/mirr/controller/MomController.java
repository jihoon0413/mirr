package side.project.mirr.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.response.Response;
import side.project.mirr.service.MomService;

@Slf4j
@RestController
@RequestMapping("/mom")
@RequiredArgsConstructor
public class MomController {

    private final MomService momService;

    @Operation(summary = "M.O.M 수정")
    @PostMapping
    public Response<Void> modifyMom(@RequestBody MomRequest momRequest){
        momService.modify(momRequest);
        return Response.success();
    }

    //TODO :  m.o.m 비워두는 기능 추가
}
