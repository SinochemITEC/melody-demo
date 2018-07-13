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
    @Qualifier("aes")
    Crypto aesCrypto;

    @Autowired
    @Qualifier("rsa")
    Crypto rsaCrypto;

    @Autowired
    @Qualifier("blowfish")
    Crypto blowfishCrypto;

    @RequestMapping("encrypt")
    public String encrypt(String text, ModelMap modelMap) {
        if (StringUtil.isEmpty(text) == false) {
            modelMap.put("aesEncrypt", aesCrypto.encrypt(text));
            modelMap.put("rsaEncrypt", rsaCrypto.encrypt(text));
            //modelMap.put("bfEncrypt",blowfishCrypto.encrypt(text));

            modelMap.put("text", text);
        }
        return "/crypto/demo";
    }

    @RequestMapping("decrypt")
    public String decrypt(String aesStr, String rsaStr, String bfStr, ModelMap modelMap) {
        String aesDecrypt = null;
        String rsaDecrypt = null;
        String bfDecrypt = null;
        if (StringUtil.isEmpty(aesStr) == false) {
            aesDecrypt = aesCrypto.dectypt(aesStr);
        }
        if (StringUtil.isEmpty(rsaStr) == false) {
            rsaDecrypt = rsaCrypto.dectypt(rsaStr);
        }
        /*if(StringUtil.isEmpty(bfStr) == false){
           bfDecrypt = blowfishCrypto.dectypt(bfStr);
        }*/
        modelMap.put("aesDecrypt", aesDecrypt);
        modelMap.put("rsaDecrypt", rsaDecrypt);

        modelMap.put("aesStr", aesStr);
        modelMap.put("rsaStr", rsaStr);
        //modelMap.put("bfStr",bfStr);

        return "/crypto/demo";
    }
}
