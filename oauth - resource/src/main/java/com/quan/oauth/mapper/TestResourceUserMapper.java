package com.quan.oauth.mapper;

import com.quan.oauth.entity.TestResourceUser;
import org.apache.ibatis.annotations.Select;

public interface TestResourceUserMapper {

    @Select("select * from test_user where user_name = #{userName}")
    TestResourceUser findAllByUserName(String userName);

}
