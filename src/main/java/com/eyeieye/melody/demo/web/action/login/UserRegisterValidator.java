package com.eyeieye.melody.demo.web.action.login;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * 登陆校验，实现Spring的Validator接口，采用编码的形式验证。
 * 
 * @author zhengdd
 * @version $Id: UserRegisterValidator.java,v 1.3 2012/02/20 15:13:46 fish Exp $
 */
public class UserRegisterValidator implements Validator {

    public boolean supports(Class<?> clz) {
        return User.class.equals(clz);
    }

    /**
     * 验证处理
     * @param obj
     * @param err
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object obj, Errors err) {
        Assert.notNull(obj);
        Assert.isInstanceOf(User.class, obj);
        User user = (User) obj;
        String realName = user.getRealName();
        if (StringUtils.isBlank(realName)) {
            // 4个参数分别为：验证对象的属性名、错误信息代码、错误信息参数、默认错误信息。
            // 错误信息可以通过配置MessageResource，指定错误信息代码，而无需在代码里写死错误信息。
            err.rejectValue("realName", "user.register.realName.empty", null, "请填写用户名");
        } else {
            if (realName.length() > 16) {
                err.rejectValue("realName", "user.register.realName.long", new Integer[]{16}, "用户名不能超过{0}个字符");
            }
        }
        if(StringUtils.isBlank(user.getPassword())){
        	 err.rejectValue("password", null, null, "请输入口令");
        }
        if(user.getPassword().length() < 6 || user.getPassword().length() > 20 ){
        	 err.rejectValue("password", null, null, "口令长度在6~20之间");
        }
        Date date = new Date();
        if(user.getBirthday().getTime() > date.getTime()){
            err.rejectValue("birthday", null, null, "出生日期不能在今天之后");
        }
        if(user.getAge() == null){
            err.rejectValue("age", null, null, "请填写年龄");
        }else{
            if(user.getAge() < 2 || user.getAge() >120){
                err.rejectValue("age", null, null, "年龄在2~120之间");
            }
        }
    }

}
