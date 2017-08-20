package com.mark.project.springMVCDemo;

import com.mark.project.hibernateDemo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mark on 17/8/19.
 *
 * 返回json格式
 *
 */
@Controller
public class JSONController {


    @RequestMapping("/jsonMethod")
    @ResponseBody
    public User jsonMethod() {
        return new User(1L, "Mark");
    }


}
