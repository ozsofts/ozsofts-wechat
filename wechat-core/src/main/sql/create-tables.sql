
drop table if exists tbl_wx_accounts;
create table tbl_wx_accounts (
  `id` bigint not null auto_increment, 
  `system_id` varchar(64) not null comment '在系统中为每个公众号分配的标识', 
  `name` varchar(64) not null comment '微信公众号名称',
  `app_id` varchar(128) not null comment '微信公众平台分配ID', 
  `secret` varchar(128) comment '应用密钥', 
  `token` varchar(128) comment '当前可用的访问令牌', 
  `encoding_aes_key` varchar(128) comment '消息加解密密钥', 
  `encrypt_type` varchar(16) comment '消息加解密类型', 
  `account_type` varchar(16) comment '公众号类型，分为订阅号和服务号', 
  
  `is_auth` bit default false comment '公众号是否通过认证',
  `is_init` bit default false comment '公众号是否已经初始化完成，在系统中初始化完成才可以进行正常的操作',
  `is_enabled` bit default false comment '公众号是否启用',

  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
	primary key (id)
) ENGINE=InnoDB comment '微信公众号信息表';


drop table if exists tbl_wx_groups;
create table tbl_wx_groups (
  `id` bigint not null auto_increment, 
  `group_id` int not null comment '微信为群组分配的标识', 
  `name` varchar(64) not null comment '群组名称', 
  `user_count` int default 0 comment '群组中用户的数量', 
	
  `system_id` varchar(64) not null comment '公众号系统标识', 

  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  primary key (id)
) ENGINE=InnoDB comment '微信公众号群组表';


drop table if exists tbl_wx_users;
create table tbl_wx_users (
  `id` bigint not null auto_increment, 
  `open_id` varchar(64) not null comment '用户的标识，对当前公众号唯一', 
  `union_id` varchar(64) default '' comment '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。', 
  `nick_name` varchar(256) default '' comment '用户的昵称', 
  `sex` varchar(1) default '0' comment '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知', 
  `language` varchar(16) not null comment '用户的语言，简体中文为zh_CN',
  `city` varchar(64) default '' comment '用户所在城市', 
  `province` varchar(64) default '' comment '用户所在省份', 
  `country` varchar(64) default '' comment '用户所在国家',
  `head_img_url` varchar(256) comment '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。', 
	
  `is_subscribe` bit default 0 comment '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。',
  `user_type` tinyint NOT NULL default 1 comment '人员类型，1：普通用户；2：内部人员；3：代理商',
  `is_staff` bit default 0 comment '是否内部人员',
  `is_vendor` bit default 0 comment '是否代理商',
	
  `sub_date` varchar(10) comment '用户关注日期，YYYY-MM-DD。如果用户曾多次关注，则取最后关注时间', 
  `sub_time` varchar(8) comment '用户关注时间，HH:MM:SS。如果用户曾多次关注，则取最后关注时间', 
  `unsub_date` varchar(10) comment '用户取消关注日期，YYYY-MM-DD。', 
  `unsub_time` varchar(8) comment '用户取消关注时间，HH:MM:SS。', 
  `remark` varchar(128) comment '用户备注',
  `mobile` varchar(32) comment '用户手机号码，在用户参与活动中获取，一般是没有的',

  `system_id` varchar(64) not null comment '公众号系统标识', 

  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  primary key (id)
) ENGINE=InnoDB comment '微信公众号用户表';
--ALTER TABLE tbl_wx_users CHANGE nick_name nick_name VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


drop table if exists tbl_wx_xmlmessages;
create table tbl_wx_xmlmessages (
  `id` bigint not null auto_increment,
	
  `xml` varchar(8192) default '' comment '上行消息的XML格式数据',

  `system_id` varchar(64) not null comment '公众号系统标识', 

  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  primary key (id)
) ENGINE=InnoDB comment '微信公众号原始信息记录表';


drop table if exists tbl_wx_messages;
create table tbl_wx_messages (
  `id` bigint not null auto_increment,
	
  `to_user` varchar(64) not null comment '发送消息的用户(openId)',
  `from_user` varchar(64) not null comment '接收消息的用户(openId)',

  `msg_id` varchar(64) comment '消息标识号，64位整型', 
  `msg_date` varchar(10) not null comment '消息日期，YYYYMMDD', 
  `msg_time` varchar(8) not null comment '消息时间，HHMMSS', 
  `msg_type` varchar(32) comment '消息类型', 
  `direction` varchar(16) default 'RECV' comment '消息的传送方向，RECV表示公众号接收到的消息，RPLY表示公众号回复的消息',

  `content` varchar(1024) default '' comment '针对文本信息,文本消息内容',

  `pic_url` varchar(255) default '' comment '针对图片信息,图片的链接地址',
	
  `format` varchar(32) default '' comment '针对语音信息,语音格式',
  `media_id` varchar(64) default '' comment '针对图片、视频以及小视频消息,图片、语音消息媒体id',
  `thumb_media_id` varchar(64) default '' comment '针对视频和小视频消息,视频消息缩略图的媒体id',

  `music_url` varchar(255) default '' comment '音乐地址信息',
  `hq_music_url` varchar(255) default '' comment '高品质音乐地址信息',
	
  `location_x` varchar(64) default '' comment '针对地理位置消息,地理位置纬度',
  `location_y` varchar(64) default '' comment '针对地理位置消息,地理位置经度',
  `scale` varchar(64) default '' comment '针对地理位置消息,地图缩放大小',
  `label` varchar(64) default '' comment '针对地理位置消息,地理位置信息',

  `title` varchar(512) default '' comment '针对链接消息,消息标题',
  `description` varchar(64) default '' comment '针对链接消息,消息描述',
  `url` varchar(64) default '' comment '针对链接消息,消息链接',
	
  `system_id` varchar(64) not null comment '公众号系统标识', 

  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  primary key (id)
) ENGINE=InnoDB comment '微信公众号信息记录表';


# 这个表中更多的是信息的配置，具体下行消息还是记录在tbl_wx_messages中
drop table if exists tbl_wx_resp_messages;
create table tbl_wx_resp_messages (
  `id` bigint not null auto_increment,
  
  `msg_type` varchar(32) comment '消息类型', 

  `content` varchar(1024) default '' comment '针对文本信息,文本消息内容',

  `media_id` varchar(64) default '' comment '针对图片、视频以及语音消息媒体id',
  
  `title` varchar(512) default '' comment '针对音乐和视频消息,消息标题',
  `description` varchar(255) default '' comment '针对音乐和视频消息,消息描述',
  `thumb_media_id` varchar(64) default '' comment '针对音乐和视频消息,图片、语音消息媒体id',

  `music_url` varchar(255) default '' comment '针对音乐消息,消息链接',
  `hq_music_url` varchar(255) default '' comment '针对音乐消息,消息链接',
	
  `system_id` varchar(64) not null comment '公众号系统标识', 

  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  primary key (id)
) ENGINE=InnoDB comment '微信公众号回复信息表';
