package com.eyeieye.melody.demo.util;


import com.eyeieye.melody.util.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Encode {
    private static final Logger logger = LoggerFactory.getLogger(MD5Encode.class);

    /**
     * 生成指定字符串的MD5值
     *
     * @param str
     * @return
     */
    public static String encode(String str) throws Exception {
        String result = null;
        result = DigestUtils.md5Hex(str);
        if(StringUtil.isEmpty(result)) throw new Exception("MD5加密失败");
        return result;
    }


    public static String encodeWithSalt(String str, String salt) throws Exception {
        String result_1 = null;
        String result_2 = null;
        result_1 = encode(str);
        result_2 = DigestUtils.md5Hex(result_1+salt);
        if(StringUtil.isEmpty(result_2)) throw new Exception("MD5加盐失败");
        return result_2;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(encodeWithSalt("TestAdmin123","SinoB2B"));
    }


}
