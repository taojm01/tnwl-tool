package com.tuoniao.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class AESUtil {

	public static final String AES_KEYS = "CHENGJI_AES_KEYS";

	/**
	 * 解密
	 */
	public static String Decrypt(String sSrc){
		try {
			// 判断Key是否正确
			if (StringUtils.isBlank(AES_KEYS)) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			byte[] raw = AES_KEYS.getBytes(StandardCharsets.UTF_8);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			Base64.Decoder decoder = Base64.getDecoder();
			// 先用base64解密
			byte[] encrypted1 = decoder.decode(sSrc);

			try {
				byte[] original = cipher.doFinal(encrypted1);
				return new String(original, StandardCharsets.UTF_8);
			} catch (Exception e) {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}

}
