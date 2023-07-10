package com.tuoniao.vo;

import com.tuoniao.po.OutstandingBillPO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OutstandingBillVO {

    private String header;

    private List<OutstandingBillPO> children;
}
