package com.eyeieye.melody.demo.web.action;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eyeieye.melody.util.crypto.RsaCrypto;

/**
 * 
 * @author fish
 * 
 */

@Controller
public class RsaAction {
	private static final String charSet = "UTF-8";

	private RsaCrypto rsaCrypto;
	private String jsPublicKey = null;
	private String jsExponent = null;

	@Autowired
	public void setRsaCrypto(RsaCrypto rsaCrypto) {
		this.rsaCrypto = rsaCrypto;
		// this.jsPublicKey =
		// rsaCrypto.getPublicKey().getModulus().toString(16);
		// 16进制都很长,用base64短些
		this.jsPublicKey = toBase64(rsaCrypto.getPublicKey().getModulus());
		this.jsExponent = rsaCrypto.getPublicKey().getPublicExponent()
				.toString(16);
	}

	/**
	 * 解密js加密后的值
	 */
	private String decodeJsValue(String jsValue) {
		// melody 可以直接提交16进制的密文,但是太长
		// byte[] input = Hex.decode(jsValue);
		// 用base64提交就短多了
		byte[] input = fromBase64(jsValue);
		byte[] raw = this.rsaCrypto.dectypt(input);
		// 标志位为0之后的是输入的有效字节
		int i = raw.length - 1;
		while (i > 0 && raw[i] != 0) {
			i--;
		}
		i++;
		byte[] data = new byte[raw.length - i];
		for (int j = i; j < raw.length; j++) {
			data[j - i] = raw[j];
		}
		try {
			return new String(data, charSet);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private static final String toBase64(BigInteger bi) {
		byte[] bs = bi.toByteArray();
		bs = Base64.encodeBase64(bs, false, false);
		try {
			return new String(bs, charSet);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	private static final byte[] fromBase64(String s) {
		try {
			byte[] bs = s.getBytes(charSet);
			return Base64.decodeBase64(bs);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	@RequestMapping("/rsa.htm")
	public void rsa(
			@RequestParam(value = "itext", required = false) String itext,
			@RequestParam(value = "entime", required = false) String entime,
			ModelMap model) throws Exception {
		if (StringUtils.isBlank(itext)) {
			return;
		}
		long before = System.currentTimeMillis();
		String plaintext = decodeJsValue(itext);
		long cost = System.currentTimeMillis() - before;
		if (StringUtils.isNotBlank(plaintext)) {
			model.addAttribute("itext", itext);
			model.addAttribute("plaintext", plaintext);
			model.addAttribute("entime", entime);
			model.addAttribute("detime", cost);
		}
	}

	@ModelAttribute("pkey")
	public String getPublicKey() {
		return jsPublicKey;
	}

	@ModelAttribute("pxo")
	public String getExponent() {
		return jsExponent;
	}

}
