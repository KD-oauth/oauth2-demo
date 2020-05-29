package com.quan.oauth.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(String data){
        return "order1"+data;
    }

    @RequestMapping("callBack")
    @ResponseBody
    public String callBack(){
        return "callBack";
    }

    @RequestMapping("/out/login")
    public String outLogin(Model model,String username, String password) throws IOException, ServletException {
        System.err.println("aaa"+username);
        System.err.println("aaa"+password);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "/portal/wait";
    }


}
