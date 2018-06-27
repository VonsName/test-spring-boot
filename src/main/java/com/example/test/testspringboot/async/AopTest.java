package com.example.test.testspringboot.async;

import com.example.test.testspringboot.taskservice.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Administrator
 */
public class AopTest {

    //static TaskService taskService=new TaskService();
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        TaskService bean = applicationContext.getBean(TaskService.class);
        for (int i = 0; i <10 ; i++) {
            bean.task1(i);
            bean.task2(i);
        }

    }
}
