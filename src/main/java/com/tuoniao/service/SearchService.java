package com.tuoniao.service;

import com.tuoniao.dto.AbnormalTrackDTO;
import com.tuoniao.vo.AbnormalTrackVO;
import com.tuoniao.vo.OutstandingBillVO;
import com.tuoniao.vo.CompanyAccountVO;
import com.tuoniao.vo.TransactionRecordVO;

import java.util.List;

public interface SearchService {

    List<OutstandingBillVO> outstandingBill();

    List<CompanyAccountVO> companyAccount(String companyNameLike);

    List<TransactionRecordVO> transactionRecordByOrderNo(String orderNo);

    List<AbnormalTrackVO> abnormalTrack(AbnormalTrackDTO dto);
}
