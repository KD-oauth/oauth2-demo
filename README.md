# oauth2-demo
SpringSecurity+oauth2实现授权认证管理系统
CSDN：https://blog.csdn.net/a624193873/article/details/106417218


数据库表：



  client项目：
  
  登录表：
  
  CREATE TABLE `test_user` (
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



token令牌表：

CREATE TABLE `oauth_token` (
  `id` varchar(64) CHARACTER SET utf8 NOT NULL,
  `access_token` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `token_type` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `refresh_token` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `expires_in` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `scope` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `create_datetime` varchar(64) DEFAULT NULL,
  `del_flag` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

  oauth-resource项目：
  
  登录表：
  
  CREATE TABLE `test_resource_user` (
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

  oauth-autho项目：
  
  client信息表：
  
  CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
