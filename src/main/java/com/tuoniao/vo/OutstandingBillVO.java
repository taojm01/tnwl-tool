package com.tuoniao.vo;

import com.tuoniao.entity.OutstandingBill;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OutstandingBillVO {

    private String header;

    private List<OutstandingBill> children;
}
