package com.eyeieye.melody.demo.web.action.login;

import com.eyeieye.melody.demo.cache.CacheManager;
import com.eyeieye.melody.web.adapter.AnnotationMethodHandlerInterceptorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import java.lang.reflect.Method;

@Component
public class ExtendedUserInterceptor extends AnnotationMethodHandlerInterceptorAdapter {
    @Autowired
    private CacheManager<ExtendedUserCacheEntry> cacheManager;

    /**
     * »º´æÃû³Æ
     */
    private  String cacheName = ExtendedUserCacheEntry.class.getName();

    @Override
    public void preInvoke(Method handlerMethod, Object handler,
                          ServletWebRequest webRequest){
        Extended extended = AnnotationUtils.getAnnotation(
                handlerMethod, Extended.class);
        if(extended == null){
            extended = handler.getClass().getAnnotation(Extended.class);
        }

        if(extended == null){
            return;
        }

        User user = (User)webRequest.getAttribute(User.NAME, RequestAttributes.SCOPE_SESSION);
        if(user == null) return;

        ExtendedUserCacheEntry extendedUserCacheEntry = cacheManager.getData(cacheName,user.getUuid());

        if(extendedUserCacheEntry == null) {
            extendedUserCacheEntry = new ExtendedUserCacheEntry();
        }
        ExtendedUser exUser = extendedUserCacheEntry.getExtendedUser();
        if(exUser == null){
            exUser = new ExtendedUser();
        }
        exUser.setUser(user);

        webRequest.setAttribute(ExtendedUser.NAME, exUser,RequestAttributes.SCOPE_REQUEST);


    }



}
