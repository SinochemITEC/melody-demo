package com.eyeieye.melody.demo.web.action.access;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eyeieye.melody.demo.access.AdminAccess;
import com.eyeieye.melody.demo.domain.AdministerAgent;
import com.eyeieye.melody.demo.enums.FunctionsEnum;
import com.eyeieye.melody.demo.web.validator.AdministerLoginvalidator;

/**
 * ϵͳ����Ա��¼�˳�action
 *
 * @author fish
 */
@Controller
@RequestMapping("/access")
public class AccessAction {

    /**
     * ��ʾ���ֱ����action��ע�������ļ������õ�ֵ
     */
    private @Value("${system.devMode}")
    boolean devMode;

    private Validator loginValidator = new AdministerLoginvalidator();

    private Random random = new Random();

    /**
     * û��@AdminAccess��ǩ,���ʾ���������
     */
    @RequestMapping("/testPage.htm")
    public void testPage(AdministerAgent agent, ModelMap model) {
        if (agent == null || agent.getLoginId() == null) {
            model.put("logined", false);
            model.put("auths", null);
            return;
        }

        List<String> auths = new ArrayList<String>();
        if (agent.haveFunction(FunctionsEnum.Fun1)) auths.add("Fun1");
        if (agent.haveFunction(FunctionsEnum.Fun2)) auths.add("Fun2");
        if (agent.haveFunction(FunctionsEnum.Fun3)) auths.add("Fun3");
        if (agent.haveFunction(FunctionsEnum.Fun4)) auths.add("Fun4");
        if (agent.haveFunction(FunctionsEnum.Fun5)) auths.add("Fun5");
        model.put("logined", true);
        model.put("auths", auths);

    }

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public String login(HttpSession session) {
        AdministerAgent agent = new AdministerAgent();
        agent.setLoginId("" + new Random().nextInt(10000));
        agent.setFunctions(4);
        session.setAttribute(AdministerAgent.AdministerTag, agent);


        return "redirect:/access/testPage.htm";

    }

    @RequestMapping(value = "logout.htm", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(AdministerAgent.AdministerTag);
        session.invalidate();
        return "redirect:/access/testPage.htm";

    }


    @RequestMapping(value = "/addFun1.htm", method = RequestMethod.GET)
    public String addFun1(AdministerAgent agent, HttpSession session) {
        if (agent == null || agent.getLoginId() == null) {
            return "redirect:/access/testPage.htm";
        }
        agent.setFunctions(0);
        session.setAttribute(AdministerAgent.AdministerTag, agent);
        return "redirect:/access/testPage.htm";
    }

    @RequestMapping(value = "/addFun2.htm", method = RequestMethod.GET)
    public String addFun2(AdministerAgent agent, HttpSession session) {
        if (agent == null || agent.getLoginId() == null) {
            return "redirect:/access/testPage.htm";
        }
        agent.setFunctions(1);
        session.setAttribute(AdministerAgent.AdministerTag, agent);
        return "redirect:/access/testPage.htm";
    }

    @RequestMapping(value = "/clearFun.htm", method = RequestMethod.GET)
    public String clearFun(AdministerAgent agent, HttpSession session) {
        agent.clearFunctions();
        session.setAttribute(AdministerAgent.AdministerTag, agent);
        return "redirect:/access/testPage.htm";
    }

    /**
     * @AdminAccess() ��ʾ��¼�û��Ϳɷ���
     */
    @AdminAccess()
    @RequestMapping(value = "/admin.htm")
    public void main(AdministerAgent agent, ModelMap model) {
        model.addAttribute("admin", agent);
    }

    /**
     * @AdminAccess( { FunctionsEnum.Fun1, FunctionsEnum.Fun3 })
     * ��ʾ��fun1����fun3��admin���ܷ���
     */
    @AdminAccess({FunctionsEnum.Fun1, FunctionsEnum.Fun3})
    @RequestMapping(value = "/fun1orfun3.htm")
    public void fun1orfun3() {
    }


}
