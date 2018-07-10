package com.eyeieye.melody.demo.web.action.utils;

import com.eyeieye.melody.demo.web.action.login.User;
import com.eyeieye.melody.util.DateUtil;
import com.eyeieye.melody.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("utils")
public class UtilsAction {

    @RequestMapping("demo/DateUtil")
    public void dateUtilPage(Integer afterDays, Integer beforeDays, ModelMap modelMap) {
        //获取当前日期时间
        String nowDateTime = DateUtil.getDateTime(DateUtil.getDatePattern(), new Date());
        //获取今天是星期几：
        int weeks = DateUtil.getDay(new Date());
        //获取几天后的日期
        Date nextDate = null;
        if ((afterDays == null || afterDays == 0) == false) {
            nextDate = DateUtil.getRelativeDate(new Date(), afterDays);
            modelMap.put("nextWeeks", getWeeks(DateUtil.getDay(nextDate)));
            modelMap.put("afterDays",afterDays);
        }
        //获取几天前的日期
        Date lastDate = null;
        if (!(beforeDays == null || beforeDays == 0)) {
            lastDate = DateUtil.getRelativeDate(new Date(), beforeDays * -1);
            modelMap.put("lastWeeks", getWeeks(DateUtil.getDay(lastDate)));
            modelMap.put("beforeDays",beforeDays);
        }

        modelMap.put("dateNow", nowDateTime);
        modelMap.put("weeks", getWeeks(weeks));


    }

    @RequestMapping("demo/StringUtil")
    public void stringUtilPage(String str1, String str2, ModelMap modelMap){
        modelMap.put("str1",str1);
        modelMap.put("str2",str2);
        modelMap.put("isEmpty",StringUtil.isEmpty(str1));
        modelMap.put("default",StringUtil.defaultIfBlank(str1,"_") );
        modelMap.put("trim",StringUtil.trim(str1));
        modelMap.put("isNumber",StringUtil.isNumber(str1));
        modelMap.put("toUpper",StringUtil.toUpperCase(str1));
        modelMap.put("toLower",StringUtil.toLowerCase(str1));
        modelMap.put("subString",StringUtil.substring(str1,0,2));
        modelMap.put("alignLeft",StringUtil.alignLeft(str1,10,"abc"));
        modelMap.put("camelCase",StringUtil.toCamelCase(str1));
        modelMap.put("equal",StringUtil.equals(str1,str2));
        String[] array = {str1, str2};
        modelMap.put("join",StringUtil.join(array));
        modelMap.put("indexOf",StringUtil.indexOf(str1,str2));
        modelMap.put("replace",StringUtil.replace(str1,str2,"jojo"));
        modelMap.put("difference",StringUtil.difference(str1,str2));
    }


    public static void main(String[] args) throws ParseException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(DateUtil.getCurrentDay().getTime()));

        Date date = new Date();
        System.out.println(DateUtil.getTimeNow(date));

        System.out.println(DateUtil.getDatePattern());

    }

    private String getWeeks(int weeks) {
        switch (weeks) {
            case 0:
                return "星期日";
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            default:
                return "未知";
        }
    }
}
