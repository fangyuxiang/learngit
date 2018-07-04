package com.example.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: 事件监听demo
 * @author: yuxiang.fang
 * @create: 2018-06-30 11:28
 **/
public class SpringEventListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyApplicationListener.class);
        context.publishEvent(new MyApplicationEvent("hello world0 \n"));
        context.publishEvent(new MyApplicationEvent("hello world1 \n"));
        context.publishEvent(new MyApplicationEvent("hello world2 \n"));
        context.publishEvent(new MyApplicationEvent("hello world3 \n"));
    }

    @Component
    private static class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
        @Override
        public void onApplicationEvent(MyApplicationEvent event) {
            System.out.println("Application event source: %s" + event.getSource());
        }
    }

    private static class MyApplicationEvent extends ApplicationEvent {
        public MyApplicationEvent(Object source) {
            super(source);
        }
    }
}
