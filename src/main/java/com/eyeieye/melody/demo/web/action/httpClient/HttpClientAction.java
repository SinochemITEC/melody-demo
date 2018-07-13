package com.eyeieye.melody.demo.web.action.httpClient;

import com.eyeieye.melody.util.httpclient.ConnectionManager;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("httpClient")
@Controller
public class HttpClientAction {

    @Autowired
    private ConnectionManager connectionManager;

    @RequestMapping("doGet")
    public void doGet(String url, String s, HttpServletRequest request){
        HttpGet get=  (HttpGet)connectionManager.doRequest("", null);
        //connectionManager.getHttpClient().execute(get);

    }


}
