package com.quan.client.Mapper;

import com.quan.client.Entity.OauthToken;
import com.quan.client.Entity.TestUser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface OauthTokenMapper extends Mapper<OauthToken> {

    @Select("SELECT * FROM oauth_token ORDER BY create_datetime DESC LIMIT 0,1")
    OauthToken findRecentEntity();
}
