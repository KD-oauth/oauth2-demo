package com.quan.client.Service;

import com.quan.client.Entity.TestUser;
import com.quan.client.Mapper.TestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service("databaseUserDetailService")
@Transactional
public class DataBaseUserDetailService implements UserDetailsService {


    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TestUser testUser = testUserMapper.findAllByUserName(userName);
        if(testUser==null){
            throw new UsernameNotFoundException("user + " + userName + "not found.");
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =  request.getSession();
        session.setAttribute("username",testUser.getUserName());
        session.setAttribute("password",testUser.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(testUser.getUserDesc()));
        UserDetails userDetails = new User(testUser.getUserName(),testUser.getPassword(),authorities);
        return userDetails;
    }
}
