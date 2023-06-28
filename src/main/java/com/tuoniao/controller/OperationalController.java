package com.tuoniao.controller;

import com.tuoniao.dto.WriteCaptchaCodeDTO;
import com.tuoniao.service.OperationalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
