package com.quan.oauth.service;

import com.quan.oauth.entity.TestResourceUser;
import com.quan.oauth.mapper.TestResourceUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("databaseUserDetailService")
@Transactional
public class DataBaseUserDetailService implements UserDetailsService {


    @Autowired
    private TestResourceUserMapper testResourceUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

       TestResourceUser testUser = testResourceUserMapper.findAllByUserName(userName);
        if(testUser==null){
            throw new UsernameNotFoundException("user + " + userName + "not found.");
        }
        System.err.println(testUser);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(testUser.getUserDesc()));
        UserDetails userDetails = new User(testUser.getUserName(),testUser.getPassword(),authorities);
        return userDetails;
    }
}
