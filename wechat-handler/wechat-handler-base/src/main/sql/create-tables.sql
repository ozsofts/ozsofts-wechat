
drop table if exists t_wx_accounts;
create table t_wx_accounts (
	id bigint not null auto_increment, 
	system_id varchar(64) not null comment '在系统中为每个公众号分配的标识', 
	name varchar(64) not null comment '微信公众号名称',
	app_id varchar(128) not null comment '微信公众平台分配ID', 
	secret varchar(128) comment '应用密钥', 
	token varchar(128) comment '当前可用的访问令牌', 
	encoding_aes_key varchar(128) comment '消息加解密密钥', 
	encrypt_type varchar(16) comment '消息加解密类型', 
	account_type varchar(16) comment '公众号类型，分为订阅号和服务号', 
	
	is_auth bit default false comment '公众号是否通过认证',
	is_init bit default false comment '公众号是否已经初始化完成，在系统中初始化完成才可以进行正常的操作',
	is_enabled bit default false comment '公众号是否启用',
	
	primary key (id)
) ENGINE=InnoDB comment '微信公众号信息表';


drop table if exists t_wx_groups;
create table t_wx_groups (
	id bigint not null auto_increment, 
	group_id integer not null comment '微信为群组分配的标识', 
	name varchar(64) not null comment '群组名称', 
	user_count integer default 0 comment '群组中用户的数量', 
	
	account_id bigint not null comment '群组所在的公众号标识', 
	primary key (id)
) ENGINE=InnoDB comment '微信公众号群组表';


drop table if exists t_wx_users;
create table t_wx_users (
	id bigint not null auto_increment, 
	open_id varchar(64) not null comment '用户的标识，对当前公众号唯一', 
	union_id varchar(64) default '' comment '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。', 
	nick_name varchar(255) default '' comment '用户的昵称', 
	sex varchar(1) default '0' comment '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知', 
	language varchar(16) not null comment '用户的语言，简体中文为zh_CN',
	city varchar(64) default '' comment '用户所在城市', 
	province varchar(64) default '' comment '用户所在省份', 
	country varchar(64) default '' comment '用户所在国家',
	head_img_url varchar(255) comment '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。', 
	
	is_subscribe bit default 0 comment '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。',
	
	sub_date varchar(10) comment '用户关注日期，YYYYMMDD。如果用户曾多次关注，则取最后关注时间', 
	sub_time varchar(8) comment '用户关注时间，HHMMSS。如果用户曾多次关注，则取最后关注时间', 
	unsub_date varchar(10) comment '用户取消关注日期，YYYYMMDD。', 
	unsub_time varchar(8) comment '用户取消关注时间，HHMMSS。', 
	remark varchar(128) comment '用户备注',
	mobile varchar(64) comment '用户手机号码，在用户参与活动中获取，一般是没有的',

	group_id bigint not null comment '用户所属群组标识', 
	account_id bigint not null comment '群组所在的公众号标识', 
	primary key (id)
) ENGINE=InnoDB comment '微信公众号用户表';


drop table if exists t_wx_messages;
create table t_wx_messages (
	id bigint not null auto_increment,
	
	to_user varchar(64) not null comment '发送消息的用户(openId)',
	from_user varchar(64) not null comment '接收消息的用户(openId)',

	msg_id varchar(64) comment '消息标识号，64位整型', 
	msg_date varchar(10) not null comment '消息日期，YYYYMMDD', 
	msg_time varchar(8) not null comment '消息时间，HHMMSS', 
	msg_type varchar(32) comment '消息类型', 
	direction varchar(16) default 'RECV' comment '消息的传送方向，RECV表示公众号接收到的消息，RPLY表示公众号回复的消息',

	content varchar(1024) default '' comment '针对文本信息,文本消息内容',

	pic_url varchar(255) default '' comment '针对图片信息,图片的链接地址',
	
	format varchar(32) default '' comment '针对语音信息,语音格式',
	media_id varchar(64) default '' comment '针对图片、视频以及小视频消息,图片、语音消息媒体id',
	thumb_media_id varchar(64) default '' comment '针对视频和小视频消息,视频消息缩略图的媒体id',

	music_url varchar(255) default '' comment '音乐地址信息',
	hq_music_url varchar(255) default '' comment '高品质音乐地址信息',
	
	location_x varchar(64) default '' comment '针对地理位置消息,地理位置纬度',
	location_y varchar(64) default '' comment '针对地理位置消息,地理位置经度',
	scale varchar(64) default '' comment '针对地理位置消息,地图缩放大小',
	label varchar(64) default '' comment '针对地理位置消息,地理位置信息',

	title varchar(512) default '' comment '针对链接消息,消息标题',
	description varchar(64) default '' comment '针对链接消息,消息描述',
	url varchar(64) default '' comment '针对链接消息,消息链接',
	
	account_id bigint not null comment '群组所在的公众号标识', 
	primary key (id)
) ENGINE=InnoDB comment '微信公众号信息记录表';


# 这个表中更多的是信息的配置，具体下行消息还是记录在t_wx_messages中
drop table if exists t_wx_resp_messages;
create table t_wx_resp_messages (
	id bigint not null auto_increment,
	
	msg_type varchar(32) comment '消息类型', 

	content varchar(1024) default '' comment '针对文本信息,文本消息内容',

	media_id varchar(64) default '' comment '针对图片、视频以及语音消息媒体id',
	
	title varchar(512) default '' comment '针对音乐和视频消息,消息标题',
	description varchar(255) default '' comment '针对音乐和视频消息,消息描述',
	thumb_media_id varchar(64) default '' comment '针对音乐和视频消息,图片、语音消息媒体id',

	music_url varchar(255) default '' comment '针对音乐消息,消息链接',
	hq_music_url varchar(255) default '' comment '针对音乐消息,消息链接',
	
	account_id bigint not null comment '回复消息所属公众号标识', 
	primary key (id)
) ENGINE=InnoDB comment '微信公众号回复信息表';


drop table if exists t_wx_rules;
CREATE TABLE t_wx_rules (
	id bigint not null auto_increment,

	rule_name varchar(64) not null comment '规则名称，便于管理人员识别',
	
	rule_type varchar(32) not null comment '规则类型，default: 关注时回复；auto: 自动回复；keyword: 关键字回复; 其它类型可以由具体的引用而定', 
	rule_value varchar(128) default '' comment '活动规则定义，关键字',
	match_type varchar(32) not null comment '匹配类型，regex: 正则；startsWith: 以关键字开头；endsWidth: 以关键字结束；contains: 包含关键字',

	resp_message_id bigint not null comment '具体的消息定义在回复消息表中',
	
	ref_type varchar(32) default '' comment '消息的关联引用，视后续扩展而定，系统级的保持空即可',
	ref_id bigint comment '当ref_type有数据时，这里表示实际引用的对象',

	account_id bigint not null comment '消息规则所属公众号标识', 
  	primary key (id)
) ENGINE=InnoDB comment '消息规则表，根据上行消息进行匹配然后形成返回的消息';


# 这里的微信活动是对上行的消息进行互动处理的
drop table if exists t_wx_actevents;
create table t_wx_actevents (
	id bigint not null auto_increment, 
	name varchar(128) not null comment '微信活动名称',
	
	code varchar(32) not null comment '默认的签到命令为[code 手机号码]',
	start_date varchar(20) not null comment '活动开始时间，yyyyMMddHHmmss',
	end_date varchar(20) not null comment '活动结束时间，yyyyMMddHHmmss',
	
	status integer not null default -1 comment '活动状态',
	
	user_count integer default 0 comment '活动参与人数，即上行keyword的用户数',
	message_count integer default 0 comment '活动上行的消息数',

	account_id bigint not null comment '微信活动的公众号标识', 
	primary key (id)
)  ENGINE=InnoDB comment '微信活动表';


# 活动参与的用户信息，在用户签到时记录信息
drop table if exists t_wx_actusers;
create table t_wx_actusers (
	id bigint not null auto_increment, 
		
	# 用户信息
	open_id varchar(64) not null comment '参与活动的用户标识号', 
	nick_name varchar(255) default '' comment '用户的昵称', 
	sex varchar(1) default '0' comment '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知', 
	head_img_url varchar(255) comment '用户头像', 
	msg_id varchar(64) comment '签到消息标识', 

	login_date varchar(10) not null comment '参与日期，yyyyMMdd',
	login_time varchar(8) not null comment '参与时间，HHmmss',
	
	message_count integer default 0 comment '活动上行的消息数',

	actevent_id bigint not null comment '微信活动标识', 
	primary key (id)
)  ENGINE=InnoDB comment '微信活动用户表';


drop table if exists t_wx_actmessages;
create table t_wx_actmessages (
	id bigint not null auto_increment, 
		
	open_id varchar(64) not null comment '参与活动的用户标识号', 
	msg_id varchar(64) comment '签到消息标识',
	
	actevent_id bigint not null comment '微信活动标识', 
	primary key (id)
)  ENGINE=InnoDB comment '微信活动消息表';
