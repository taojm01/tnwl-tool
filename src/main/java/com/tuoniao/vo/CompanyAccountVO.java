package com.tuoniao.vo;

import lombok.Data;

@Data
public class CompanyAccountVO {
    private Integer id;

    private String companyName;

    private String nameCn;

    private String username;

    private String password;

    private String status;

    private String level;

    private String secretKey;
}
