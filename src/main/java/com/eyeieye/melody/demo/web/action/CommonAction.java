package com.eyeieye.melody.demo.web.action;

import com.eyeieye.melody.demo.web.action.login.User;
import com.eyeieye.melody.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonAction {

    @RequestMapping(value = "config/introduce",method = RequestMethod.GET)
    public void configIntro(ModelMap modelMap){
        modelMap.put("selected","config");
    }
    @RequestMapping(value = "dir_url/introduce",method = RequestMethod.GET)
    public void dirUrlIntro(ModelMap modelMap){
        modelMap.put("selected","dir_url");
    }
    @RequestMapping(value = "webpages/introduce",method = RequestMethod.GET)
    public void webpagesIntro(ModelMap modelMap){
        modelMap.put("selected","webpages");
    }
    @RequestMapping(value = "annotation/introduce",method = RequestMethod.GET)
    public void annotationIntro(ModelMap modelMap){
        modelMap.put("selected","annotation");
    }
    @RequestMapping(value = "nosession/introduce",method = RequestMethod.GET)
    public void nosessionIntro(ModelMap modelMap){
        modelMap.put("selected","nosession");
    }
    @RequestMapping(value = "login/introduce",method = RequestMethod.GET)
    public void loginIntro(ModelMap modelMap){
        modelMap.put("selected","login");
    }
    @RequestMapping(value = "others/introduce",method = RequestMethod.GET)
    public void othersIntro(ModelMap modelMap){
        modelMap.put("selected","others");
    }

    @RequestMapping("contain/header")
    public void header(User user, ModelMap modelMap){
        if(StringUtil.isEmpty(user.getUuid()) == false){
            modelMap.put("logined",true);
        }else{
            modelMap.put("logined",false);
        }
    }

}
