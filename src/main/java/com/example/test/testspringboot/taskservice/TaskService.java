package com.example.test.testspringboot.taskservice;


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
    }

    @Async
    public void task2(int i){
        System.out.println("task2>"+i);
    }
}
