package com.tuoniao.controller;

import com.tuoniao.constant.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class BadRequestException extends AbstractThrowableProblem {

    public BadRequestException(String title) {
        super(ErrorConstants.BAD_REQUEST_TYPE, title, Status.BAD_REQUEST);
    }

}