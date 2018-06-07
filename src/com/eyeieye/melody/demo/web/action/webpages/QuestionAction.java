package com.eyeieye.melody.demo.web.action.webpages;


import com.eyeieye.melody.demo.domain.DemoHero;
import com.eyeieye.melody.demo.service.impl.SimpleCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/webpages/demo")
public class QuestionAction {

    SimpleCacheManager cacheManager = new SimpleCacheManager();

    @RequestMapping(value = "quetionStart.htm")
    public String questionStart(Integer levelA, Integer levelB, ModelMap modelMap) {
        if (levelA == null) {
            modelMap.put("A", null);
        } else {
            DemoHero A = generateNewHero(levelA);
            cacheManager.put(String.valueOf(A.getId()), A);
            modelMap.put("A", A);
        }
        if (levelB == null) {
            modelMap.put("B", null);
        } else {
            DemoHero B = generateNewHero(levelB);
            cacheManager.put(String.valueOf(B.getId()), B);
            modelMap.put("B", B);
        }

        return "/webpages/demo/questionStart";
    }

    @RequestMapping(value = "questionExecute.htm")
    public void execute(Long id,ModelMap modelMap){
        Object o = cacheManager.get(String.valueOf(id));
        if(o != null) {
            modelMap.put("hero", (DemoHero) o);
        }else{
            modelMap.put("hero", null);
        }
    }



    @RequestMapping(value = "questionResult.htm")
    public void result(Long id, ModelMap modelMap){
        Object o = cacheManager.get(String.valueOf(id));
        if(o != null) {
            DemoHero hero = (DemoHero)o;
            Random r = new Random();
            Integer hit = r.nextInt(100)+100;
            Integer heart = (hit - hero.getLevel())>0?(hit - hero.getLevel()):0;
            hero.setHealthPoint(hero.getHealthPoint() - heart);
            hero.setFloor(hero.getFloor()+1);
            modelMap.put("hero",hero);
        }else{
            modelMap.put("hero", null);
        }
    }


    synchronized private DemoHero generateNewHero(Integer level) {
        DemoHero demoHero = new DemoHero();
        demoHero.setLevel(level);
        demoHero.setId(DemoHero.getNextId());
        return demoHero;
    }
}
