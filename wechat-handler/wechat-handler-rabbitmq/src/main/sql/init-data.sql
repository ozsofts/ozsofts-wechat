insert into t_wx_accounts 
(system_id, name, app_id, secret, token, encoding_aes_key, encrypt_type, account_type, is_auth, is_init, is_enabled)
values
('nyymh',    '南粤牡丹爱影会', 'wx0395a0935a06dd22', '4d998637034377aee375731b2dce2fac', 'nyymh',       'yC7ud7ATy8AyxUr1cQdFawKsICcffic3AFCL3F4Swnj', 'aes', 'DY', 1, 0, 0),
('ikdy',     '迈络爱影网',    'wx0855361da61e2483', 'abbb4165a583f4682d9868497e0188db', 'mobiritikdy', 'R8QRwuFqQ3AKaIHDwSwukIR8ZlDL6kFWckDWJSzHPP8', 'raw', 'DY', 0, 0, 0),
('ikdyserv', '我的爱影网',    'wx17b5c19e50d8519b', 'ae0f1795e2224e8f49b2189f4ebe8af1', 'serviceikdy', '9cHqvBXVPZXg0JfWMZNQjIztSu0j0jWHVQMNp7Jq3kw', 'aes', 'FW', 1, 0, 1);



insert into t_wx_resp_messages
(id, msg_type, content, account_id)
values
(1, 'text', '感谢您关注我的爱影网，关于购票中出现的问题都可以在此得到帮助。', 3);


insert into t_wx_rules
(id, rule_name, rule_type, rule_value, match_type, resp_messages_id, ref_type, ref_id, account_id)
values
(1, '我的爱影网关注回复', 'default', '', '', 1, '', null, 3),
(2, '微信测试活动签到回复', 'default', '', '', 1, 'ACTEVENT', 1, 3);


insert into t_wx_actevents 
(name, code, start_date, end_date, status, account_id)
values
('测试微信活动', '签到', '20151008', '20151231', 0, 3);
