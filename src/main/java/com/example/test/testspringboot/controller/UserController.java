package com.example.test.testspringboot.controller;

import com.example.test.testspringboot.entity.User;
import com.example.test.testspringboot.taskservice.TaskService;
import com.example.test.testspringboot.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 */
@Controller
public class UserController {

    @Autowired
    private TaskService taskService;


    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(User user){
        /**
         * 这里因为不是在一个线程里面设置的值，所在在taskService取值的时候会
         * 为null
         */
        TestUtils.set("测试");
        System.out.println(user);
        return "/";
    }


    /**
     * 这里异步调用任务的时候，必须返回一个值或者视图路径
     * 否则会出现javax.servlet.ServletException:
     * Circular view path [login]: would dispatch back to the current handler URL
     * 死循环调用
     * 造成的原因：1.缺省转发
     *            2.view和path同名
     * 解决办法：1.消除缺省转发
     *          2.修改view和path，让他们不同名
     * @return
     */
    @GetMapping(value = "/asyncTest")
    public String test1(){
        for (int i = 0; i <10 ; i++) {
            taskService.task1(i);
            taskService.task2(i);
        }
        return "success";
    }

    /**
     * javax.servlet.ServletException: Circular view path [test2]:
     * would dispatch back to the current handler URL [/test2] again.
     * Check your ViewResolver setup!
     * 出现此问题的原因和是不是异步没有关系
     *
     * 主要是因为
     * ，如果你的view name和你的path是相同的字符串，根据Spring的转发规则，
     * 就等于让自己转发给自己，会陷入死循环。所以Spring会检查到这种情况，
     * 于是抛出Circular view path异常
     *
     *死循环调用
     *造成的原因：1.缺省转发
     *           2.view和path同名
     * 解决办法：1.消除缺省转发
     *          2.修改view和path，让他们不同名
     * 解决办法是必须返回一个view视图路径
     */
    @GetMapping(value = "/test2")
    public String test2(){
        System.out.println("test2");
        return  "failed";
    }

    @GetMapping(value = "/")
    public ModelAndView test3(ModelAndView mv){

        mv.setViewName("/success");
        mv.addObject("title","欢迎使用Thymeleaf!");
        return mv;
    }

    /**
     * 这里整合thymeleaf的时候
     * 由于Spring-boot默认使用的thymeleaf的版本是2.0x的
     * spring-boot-starter-thymeleaf中包含的是thymeleaf2.X
     * 所以在Spring-boot 1.5.x版本必须自己手动重新配置thymeleaf的版本信息为3.0x
     * <thymeleaf.version>3.0.2.RELEASE</thymeleaf.version>
     * <thymeleaf-layout-dialect.version>2.0.4</thymeleaf-layout-dialect.version>
     * @return
     */
    @GetMapping(value = "/test4")
    public String test4(){

        return "/success";
    }
}
