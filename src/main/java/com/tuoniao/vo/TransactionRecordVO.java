package com.tuoniao.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRecordVO {

    private Integer id;

    private String company;

    private String state;

    private String transactionTime;

    private String fee;

    private BigDecimal amount;

    private String type;

    private String balance;

    private String channel;
}
