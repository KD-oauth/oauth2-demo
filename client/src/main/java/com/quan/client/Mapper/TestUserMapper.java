package com.quan.client.Mapper;

import com.quan.client.Entity.TestUser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface TestUserMapper extends Mapper<TestUser> {

    @Select("select * from test_user where user_name = #{userName}")
    TestUser findAllByUserName(String userName);

}
