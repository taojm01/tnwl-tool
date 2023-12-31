package com.tuoniao.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tuoniao.dao.SearchMapper;
import com.tuoniao.dto.AbnormalTrackDTO;
import com.tuoniao.entity.OutstandingBill;
import com.tuoniao.po.OutstandingBillPO;
import com.tuoniao.vo.AbnormalTrackVO;
import com.tuoniao.vo.OutstandingBillVO;
import com.tuoniao.vo.CompanyAccountVO;
import com.tuoniao.service.SearchService;
import com.tuoniao.util.AESUtil;
import com.tuoniao.vo.TransactionRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;

    @Override
    public List<OutstandingBillVO> outstandingBill() {
        List<OutstandingBillVO> list = new ArrayList<>();
        searchMapper.outstandingBill().stream().collect(Collectors.groupingBy(OutstandingBill::getCompany))
                .forEach((k, v) -> {
                    var header = String.format("%s 余额: %s", k, v.get(0).getRechargeBalance());
                    var children = v.stream()
                            .map(r -> OutstandingBillPO.builder()
                                    .date(r.getDate())
                                    .repayment(r.getRepayment())
                                    .amount(r.getAmount())
                                    .freightPaid(r.getFreightPaid())
                                    .unpaidFreight(r.getUnpaidFreight())
                                    .payProgress(r.getPayProgress())
                                    .canPayPercent(r.getCanPayPercent().compareTo(BigDecimal.ONE) > 0 ? BigDecimal.ONE : r.getCanPayPercent())
                                    .build()).toList();
                    list.add(OutstandingBillVO.builder()
                            .header(header)
                            .children(children)
                            .build());
                });
        return list;
    }

    @Override
    public List<CompanyAccountVO> companyAccount(String companyNameLike) {
        var list = searchMapper.companyAccount(companyNameLike.trim());
        return list.stream().peek(r -> {
//            var password = ObjectUtil.defaultIfNull(AESUtil.Decrypt(r.getPassword()), r.getPassword());
//            r.setPassword(password);
            r.setPassword(decryptPassword(r.getPassword()));
            r.setStatus(Objects.equals("1", r.getStatus()) ? "禁用" : "激活");
            switch (r.getLevel()){
                case "0" -> r.setLevel("超级管理员");
                case "1" -> r.setLevel("信息管理员");
                case "2" -> r.setLevel("普通员工");
                case "3" -> r.setLevel("调度主管");
                case "4" -> r.setLevel("财务主管");
                case "5" -> r.setLevel("仓库操作");
                case "6" -> r.setLevel("日志审计员");
                default -> r.setLevel("未知");
            }
        }).toList();
    }

    private String decryptPassword(String text){
        var password = AESUtil.Decrypt(text);
        if(Objects.isNull(password) && text.length() == 32){
            var resp = HttpUtil.get("http://www.ttmd5.com/do.php?c=Decode&m=getMD5&md5=".concat(text));
            log.info("MD5请求:{}, 返回：{}", text, resp);
            var json = JSONUtil.parseObj(resp);
            password = json.getStr("plain");
        }
        return password;
    }

    @Override
    public List<TransactionRecordVO> transactionRecordByOrderNo(String orderNo) {
        return searchMapper.transactionRecordByOrderNo(orderNo.trim());
    }

    @Override
    public List<AbnormalTrackVO> abnormalTrack(AbnormalTrackDTO dto) {
        return searchMapper.abnormalTrack(dto);
    }
}
