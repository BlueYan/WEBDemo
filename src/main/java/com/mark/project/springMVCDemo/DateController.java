package com.mark.project.springMVCDemo;

import com.mark.project.hibernateDemo.domain.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mark on 17/8/19
 * 前台注入时间和后台
 */
@Controller
public class DateController {

    /**
     * 接受一个User对象 里面有时间属性
     * 返回一个User对象转成json。
     * @param u
     * @return
     * @throws Exception
     */
    @RequestMapping("/date")
    @ResponseBody
    public User dateMethod(User u) throws Exception {
        System.out.println(u);

        User nUser = new User();
        nUser.setId(1L);
        nUser.setName("Mark");
        nUser.setHiredate(new Date());
        return nUser;
    }

    /**
     * 该方法每次都会在执行其他方法前调用。
     * @param binder
     */
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        System.out.println("initBinder");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        format.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
//    }

}
