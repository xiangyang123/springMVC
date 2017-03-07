package com.zs.controller;

import com.zs.pojo.User;
import com.zs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * Created by zouxiang on 2017/3/2.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/showUser",method = RequestMethod.POST)
    public String toIndex(HttpServletRequest request, ModelMap model){
        ModelAndView mv = new ModelAndView();
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user",user);
        System.out.println(123);
        return "showUser";
    }

    public String test(HttpServletRequest request){
        return request.getParameter("id");
    }

    @RequestMapping(value = "/loginTest", method = RequestMethod.POST)
    public String loginTest(HttpServletRequest request,HttpServletResponse response){
        String account = request.getParameter("userName");
        String password = request.getParameter("password");
        if (account.equals("admin")&&password.equals("1")){
            return "index";
        }else{
            return "login";
        }
    }



}
