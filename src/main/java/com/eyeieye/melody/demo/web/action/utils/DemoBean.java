package com.eyeieye.melody.demo.web.action.utils;

import com.eyeieye.melody.web.util.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class DemoBean {

    @Autowired
    private ObjectFactory objectFactory;

    private Integer randomTag = new Random().nextInt(1234);

    public Integer getRandomTag(){
        return randomTag;
    }
    public void ObjectFactoryDemo(){
        DemoBean bean1 = objectFactory.createBean(DemoBean.class,false);
        DemoBean bean2 = new DemoBean();
        objectFactory.autowireBeanProperties(bean2);
        DemoBean bean3 = objectFactory.getBean(DemoBean.class);
        String beanName = objectFactory.getBeanName(DemoBean.class);
        DemoBean bean4 = (DemoBean)objectFactory.getBean(beanName);
        Map<String,DemoBean> map = objectFactory.getBeansOfType4Map(DemoBean.class);
        DemoBean bean5 = map.get(beanName);
        List<DemoBean> list = objectFactory.getBeansOfType4List(DemoBean.class);
        DemoBean bean6 = list.get(0);
        DemoBean[] array = objectFactory.getBeansOfType4Array(DemoBean.class);
        DemoBean bean7 = array[0];

        System.out.println("本bean中的randomTag为："+randomTag);
        System.out.println("bean1中的randomTag为："+ bean1.getRandomTag());
        System.out.println("bean2中的randomTag为："+ bean2.getRandomTag());
        System.out.println("bean3中的randomTag为："+ bean3.getRandomTag());
        System.out.println("bean4中的randomTag为："+ bean4.getRandomTag());
        System.out.println("bean5中的randomTag为："+ bean5.getRandomTag());
        System.out.println("bean6中的randomTag为："+ bean6.getRandomTag());
        System.out.println("bean7中的randomTag为："+ bean7.getRandomTag());
    }
}
