CREATE TABLE `sys_code` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT 'code',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `type` varchar(20) NOT NULL COMMENT '类型',
  `des` varchar(100) NOT NULL COMMENT '描述',
  `status` int NOT NULL COMMENT '状态;启用1;停用0',
  `version` int NOT NULL COMMENT '版本乐观锁',
  `create_time` datetime COMMENT '创建日期',
  `update_time` datetime COMMENT '更新日期',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统码表';