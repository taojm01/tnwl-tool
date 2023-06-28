package com.tuoniao.dto;

import lombok.Data;

@Data
public class WriteCaptchaCodeDTO {

    private String phone;

    private String code;
}
