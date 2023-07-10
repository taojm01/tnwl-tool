package com.tuoniao.po;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OutstandingBillPO {
    private String date;

    private String repayment;

    private BigDecimal amount;

    private BigDecimal freightPaid;

    private BigDecimal unpaidFreight;

    private BigDecimal payProgress;

    private BigDecimal canPayPercent;
}
