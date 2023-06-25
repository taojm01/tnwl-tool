package com.tuoniao.dao;


import com.tuoniao.entity.OutstandingBill;
import com.tuoniao.vo.CompanyAccountVO;
import com.tuoniao.vo.TransactionRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchMapper {


    List<OutstandingBill> outstandingBill();

    List<CompanyAccountVO> companyAccount(@Param("companyNameLike") String companyNameLike);

    List<TransactionRecordVO> transactionRecordByOrderNo(@Param("orderNo") String orderNo);

}
