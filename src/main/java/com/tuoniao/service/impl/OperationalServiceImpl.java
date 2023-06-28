package com.tuoniao.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tuoniao.dto.WriteCaptchaCodeDTO;
import com.tuoniao.service.OperationalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OperationalServiceImpl implements OperationalService {

    @Override
    public String writeCaptchaCode(WriteCaptchaCodeDTO dto) {
        if (ObjectUtil.isEmpty(dto.getPhone()) || ObjectUtil.isEmpty(dto.getCode())){
            return "{\"code\":500,\"message\":\"参数不正确\"}";
        } else if (dto.getPhone().length() != 11){
            return "{\"code\":500,\"message\":\"手机号格式不正确\"}";
        } else if (dto.getCode().length() != 4){
            return "{\"code\":500,\"message\":\"验证码必须是4位数\"}";
        } else {
            var urlTarget = "http://192.168.1.124:9009/writeCaptcha?phone=" + dto.getPhone() + "&code=" + dto.getCode();
            var resp = HttpUtil.post(urlTarget, "");
            log.info("1.0司机宝验证码--请求参数：{}, 返回：{}", JSONUtil.toJsonStr(dto), resp);
            return resp;
        }
    }
}
