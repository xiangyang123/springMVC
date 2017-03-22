package com.zs.controller;

import com.zs.pojo.User;
import com.zs.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by zouxiang on 2017/3/2.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private Jedis jedis;

    @RequestMapping(value = "/showUser",method = RequestMethod.GET)
    public String toIndex(HttpServletRequest request, ModelMap model){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user",user);
        logger.info("user:"+user);
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

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(HttpServletRequest request){
        String userName  = request.getParameter("userName");
        String password = request.getParameter("password");
        String ageString = request.getParameter("age");

        int age = 0;
        if(StringUtils.isNotBlank(ageString)){
            age = Integer.parseInt(ageString);
        }
        User user = new User(userName,password,age);
        int result = userService.saveUser(user);
        System.out.println(result);
        return "add";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "main";
    }


    @RequestMapping(value = "saveJedis",method = RequestMethod.GET)
    public void saveJedis(){
        //jedis.set("springmvc","springmvc");

        Map<String,String> map = jedis.hgetAll("javaHashMap");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":"+entry.getValue());
        }

//        jedis.lpushx("arrayList","1","2","3","4");
        System.out.println(jedis.exists("arrayList"));

        List<String> list = jedis.lrange("arrayList",0,10);
        for (String s : list) {
            System.out.println(s);
        }
    }




}
