package side.project.mirr.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.response.Response;
import side.project.mirr.service.AttendService;

@Slf4j
@RestController
@RequestMapping("/attend")
@RequiredArgsConstructor
public class AttendController {

    private final AttendService attendService;

    @Operation(summary = "참석자 추가")
    @PostMapping
    public Response<Void> newAttend(@RequestBody MomRequest attendRequest) {
        attendService.newAttends(attendRequest);
        return Response.success();
    }
    //TODO: 같은 경기에 참석자 중복 저장되지 않도록 하기
    //TODO: 참석자 체크박스로 한번에 추가하기

    @Operation(summary = "참석자 삭제")
    @DeleteMapping("/{attendId}")
    @ResponseBody
    public Response<Void> deleteAttend(@PathVariable("attendId") Long attendId) {
        attendService.deleteAttend(attendId);
        return Response.success();
    }

}
