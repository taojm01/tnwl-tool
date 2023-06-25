package com.tuoniao.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OutstandingBill {

    private Integer id;

    private String company;

    private BigDecimal rechargeBalance;

    private String date;

    private String repayment;

    private BigDecimal amount;

    private BigDecimal freightPaid;

    private BigDecimal unpaidFreight;
}
