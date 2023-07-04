package com.tuoniao.service;

import com.tuoniao.dto.VerificationTrackDTO;
import com.tuoniao.dto.WriteCaptchaCodeDTO;

public interface OperationalService {

    String writeCaptchaCode(WriteCaptchaCodeDTO dto);

    void abnormalTrackRerun(int id);

    String verificationTrack(VerificationTrackDTO json);
}
