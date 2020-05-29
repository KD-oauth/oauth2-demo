package com.quan.oauth.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portal")
public class PortalController {

    @RequestMapping("/login")
    public String login(){
        return "/portal/login";
    }
}
