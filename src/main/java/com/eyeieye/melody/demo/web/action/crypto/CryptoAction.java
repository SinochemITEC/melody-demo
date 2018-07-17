package com.eyeieye.melody.demo.web.action.crypto;

import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.util.crypto.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("crypto")
public class CryptoAction {

    @Autowired
    @Qualifier("aesCrypto")
    Crypto aesCrypto;

    @Autowired
    @Qualifier("rsaCrypto")
    Crypto rsaCrypto;


    @RequestMapping("encrypt")
    public String encrypt(String text, ModelMap modelMap) {
        if (StringUtil.isEmpty(text) == false) {
            modelMap.put("aesEncrypt", aesCrypto.encrypt(text));
            modelMap.put("rsaEncrypt", rsaCrypto.encrypt(text));

            modelMap.put("text", text);
        }
        return "/crypto/demo";
    }

    @RequestMapping("decrypt")
    public String decrypt(String aesStr, String rsaStr, ModelMap modelMap) {
        String aesDecrypt = null;
        String rsaDecrypt = null;
        if (StringUtil.isEmpty(aesStr) == false) {
            aesDecrypt = aesCrypto.dectypt(aesStr);
        }
        if (StringUtil.isEmpty(rsaStr) == false) {
            rsaDecrypt = rsaCrypto.dectypt(rsaStr);
        }
        modelMap.put("aesDecrypt", aesDecrypt);
        modelMap.put("rsaDecrypt", rsaDecrypt);

        modelMap.put("aesStr", aesStr);
        modelMap.put("rsaStr", rsaStr);

        return "/crypto/demo";
    }
}
