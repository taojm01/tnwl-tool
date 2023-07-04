package com.tuoniao.controller;

import com.tuoniao.dto.AbnormalTrackDTO;
import com.tuoniao.vo.AbnormalTrackVO;
import com.tuoniao.vo.OutstandingBillVO;
import com.tuoniao.vo.CompanyAccountVO;
import com.tuoniao.service.SearchService;
import com.tuoniao.vo.TransactionRecordVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tool/search")
@Tag(name = "查询", description = "Search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/outstanding-bill")
    @Operation(summary = "公司未还清账单")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OutstandingBillVO.class))))
    })
    public List<OutstandingBillVO> outstandingBill() {
        return searchService.outstandingBill();
    }

    @GetMapping("/company-account")
    @Operation(summary = "公司登录账号")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CompanyAccountVO.class))))
    })
    public List<CompanyAccountVO> companyAccount(@RequestParam("companyNameLike") String companyNameLike) {
        return searchService.companyAccount(companyNameLike);
    }

    @GetMapping("/transaction-record")
    @Operation(summary = "查询订单流水")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransactionRecordVO.class))))
    })
    public List<TransactionRecordVO> transactionRecordByOrderNo(@RequestParam("orderNo") String orderNo) {
        return searchService.transactionRecordByOrderNo(orderNo);
    }

    @GetMapping("/abnormal-trajectory")
    @Operation(summary = "异常轨迹")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AbnormalTrackVO.class))))
    })
    public List<AbnormalTrackVO> abnormalTrack(@ModelAttribute AbnormalTrackDTO dto) {
        return searchService.abnormalTrack(dto);
    }
}
