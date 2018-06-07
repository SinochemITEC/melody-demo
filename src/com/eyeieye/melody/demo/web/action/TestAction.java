package com.eyeieye.melody.demo.web.action;

import com.eyeieye.melody.web.url.URLBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/test")
public class TestAction {
    @Autowired
    private URLBroker mailServer;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        System.out.println(mailServer.get("123.htm").toString());
        return "/dir_url/introduce";
    }
}
