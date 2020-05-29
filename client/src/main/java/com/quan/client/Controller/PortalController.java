package com.quan.client.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.quan.client.Entity.OauthToken;
import com.quan.client.Mapper.OauthTokenMapper;
import com.quan.client.util.IdWorker;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

@Controller
@RequestMapping("/portal")
public class PortalController {

    @Autowired
    private OauthTokenMapper oauthTokenMapper;

    @RequestMapping("/login")
    public String login(){
        return "/portal/login";
    }

    @RequestMapping("/index")
    public String index(){
        return "/portal/index";
    }

    @RequestMapping("/order")
    public String order(){
        return "/portal/order";
    }

    @RequestMapping("/auth")
    public String auth(){
        return "/portal/auth";
    }

    @RequestMapping("receive")
    @ResponseBody
    public String receive(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders() {{
//            String auth = username + ":" + password;
            String auth = "clientapp" + ":" + "112233";
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("code", code);
        paramMap.add("grant_type", "authorization_code");
        paramMap.add("redirect_uri", "http://localhost:8085/portal/receive");
        paramMap.add("scope", "server");

        ResponseEntity<String> responseEntity = restTemplate.exchange
                ("http://localhost:8080/oauth/token", HttpMethod.POST, new HttpEntity<MultiValueMap<String, Object>>(paramMap, httpHeaders), String.class);
        String body = responseEntity.getBody();
        System.err.println("1"+body);
        JSONObject jsonObject = JSON.parseObject(body);
        String accessToken = jsonObject.getString("access_token");
        String tokenType = jsonObject.getString("token_type");
        String refreshToken = jsonObject.getString("refresh_token");
        String expiresIn = jsonObject.getString("expires_in");
        String scope = jsonObject.getString("scope");

        OauthToken oauthToken = new OauthToken();
        IdWorker iw = new IdWorker();
        String id = String.valueOf(iw.nextId());
        oauthToken.setId(id);
        oauthToken.setAccessToken(accessToken);
        oauthToken.setTokenType(tokenType);
        oauthToken.setRefreshToken(refreshToken);
        oauthToken.setExpiresIn(expiresIn);
        oauthToken.setScope(scope);
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        oauthToken.setCreateDatetime(df.format(day));
        oauthToken.setDelFlag("0");
        oauthTokenMapper.insert(oauthToken);
        return "授权成功！";
    }

    @RequestMapping("getOrder")
    @ResponseBody
    private String getOrder(String data){
        OauthToken oauthToken = oauthTokenMapper.findRecentEntity();
        String accessToken = oauthToken.getAccessToken();
        String tokenType = oauthToken.getTokenType();
        String res = tokenType+" "+accessToken;
        HttpHeaders httpHeaders = new HttpHeaders() {{
            set("Authorization", res);
        }};
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("data", data);
        ResponseEntity<String> responseEntity = restTemplate.exchange
                ("http://localhost:8081/resource/order/test", HttpMethod.POST, new HttpEntity<MultiValueMap<String, Object>>(paramMap,httpHeaders), String.class);
        String r = responseEntity.getBody();
        System.err.println(r);
        return r;
    }

    @RequestMapping("/out/login")
    @ResponseBody
    private String outLogin(String data){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =  request.getSession();
        String username = (String)session.getAttribute("username");
        String password = (String)session.getAttribute("password");
        OauthToken oauthToken = oauthTokenMapper.findRecentEntity();
        String accessToken = oauthToken.getAccessToken();
        String tokenType = oauthToken.getTokenType();
        String res = tokenType+" "+accessToken;
        System.err.println(res);
        HttpHeaders httpHeaders = new HttpHeaders() {{
            set("Authorization", res);
        }};
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("username", username);
        paramMap.add("password", password);
        ResponseEntity<String> responseEntity = restTemplate.exchange
                ("http://localhost:8081/resource/out/login", HttpMethod.POST, new HttpEntity<MultiValueMap<String, Object>>(paramMap,httpHeaders), String.class);
        String r = responseEntity.getBody();
        System.err.println(r);
        return r;
    }
}
