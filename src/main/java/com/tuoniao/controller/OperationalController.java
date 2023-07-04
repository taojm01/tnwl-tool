package com.tuoniao.controller;

import com.tuoniao.dto.VerificationTrackDTO;
import com.tuoniao.dto.WriteCaptchaCodeDTO;
import com.tuoniao.service.OperationalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/tool/operate")
@Tag(name = "操作", description = "Operate")
public class OperationalController {
    @Autowired
    private OperationalService operationalService;

    @PostMapping("/write-captcha-code")
    @Operation(summary = "1.0司机宝验证码")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public String writeCaptchaCode(@RequestBody WriteCaptchaCodeDTO dto){
        return operationalService.writeCaptchaCode(dto);
    }

    @GetMapping("/abnormal-trajectory/rerun")
    @Operation(summary = "异常轨迹重新跑")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    public void abnormalTrackRerun(@RequestParam("id") int id) {
        operationalService.abnormalTrackRerun(id);
    }

    @PostMapping("/abnormal-trajectory/verification")
    @Operation(summary = "校验轨迹")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public String verificationTrack(@RequestBody VerificationTrackDTO dto) {
        return operationalService.verificationTrack(dto);
    }

}
