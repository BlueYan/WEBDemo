package com.mark.project.strut2.action;

/**
 * Created by Mark on 17/7/2.
 * 定义一个Action类
 * 提供一个无参数的execute方法
 */
public class HelloAction {

    public String execute() {
        System.out.println("HelloAction execute");
        return "hello";
    }

}
