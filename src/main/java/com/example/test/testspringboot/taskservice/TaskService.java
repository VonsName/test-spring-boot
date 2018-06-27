package com.example.test.testspringboot.taskservice;


import com.example.test.testspringboot.utils.TestUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class TaskService {

    @Async
    public void task1(int i){
        System.out.println("task1>"+i);
        String s = TestUtils.get();
        System.out.println(s);
    }

    @Async
    public void task2(int i){
        System.out.println("task2>"+i);
        String s = TestUtils.get();
        System.out.println(s);
    }
}
