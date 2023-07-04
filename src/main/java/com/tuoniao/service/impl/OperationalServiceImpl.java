package com.tuoniao.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tuoniao.controller.BadRequestException;
import com.tuoniao.dto.VerificationTrackDTO;
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

    @Override
    public void abnormalTrackRerun(int id) {
        if(id > 0){
            var resp = HttpUtil.get("https://tms2.gangkou56.com/api/tms/pool/history/gpsClient/" + id);
            log.info("异常轨迹重跑--请求参数：{}, 返回：{}", id, resp);
            if(ObjectUtil.isNotEmpty(resp)){
                throw new BadRequestException("系统错误");
            }
        } else {
            throw new BadRequestException("非法的请求标识");
        }

    }

    @Override
    public String verificationTrack(VerificationTrackDTO dto) {
        var resp = HttpUtil.post("https://tms2.gangkou56.com/api/truck-track/match-check-points", dto.getJson());
        log.info("轨迹校验--请求参数：{}, 返回：{}", dto.getJson(), resp);
        return resp;
    }
}
