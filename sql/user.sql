show databases;

create database db_admin_user;

use db_admin_user;

-- 用户表
CREATE TABLE `d_users`
(
    `user_id`       varchar(36)  NOT NULL COMMENT '用户UUID',
    `username`      varchar(50)  NOT NULL COMMENT '登录用户名',
    `password_hash` varchar(255) NOT NULL COMMENT '加密后的密码',
    `email`         varchar(100) NOT NULL COMMENT '邮箱',
    `phone`         varchar(20)           DEFAULT NULL COMMENT '手机号',
    `real_name`     varchar(50)           DEFAULT NULL COMMENT '真实姓名',
    `institution`   varchar(100)          DEFAULT NULL COMMENT '所属机构',
    `title`         varchar(50)           DEFAULT NULL COMMENT '职称',
    `avatar`        varchar(255)          DEFAULT NULL COMMENT '头像URL',
    `status`        tinyint(1)   NOT NULL DEFAULT '1' COMMENT '状态(0-禁用,1-正常)',
    `last_login`    datetime              DEFAULT NULL COMMENT '最后登录时间',
    `create_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `idx_username` (`username`),
    UNIQUE KEY `idx_email` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

-- 角色表
CREATE TABLE `d_roles`
(
    `role_id`     int(11)     NOT NULL AUTO_INCREMENT,
    `role_name`   varchar(50) NOT NULL COMMENT '角色名称',
    `role_code`   varchar(50) NOT NULL COMMENT '角色编码',
    `description` varchar(255)         DEFAULT NULL COMMENT '描述',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `idx_role_code` (`role_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

-- 权限表
CREATE TABLE `d_permissions`
(
    `permission_id`   int(11)     NOT NULL AUTO_INCREMENT,
    `permission_name` varchar(50) NOT NULL,
    `permission_code` varchar(50) NOT NULL,
    `description`     varchar(255)         DEFAULT NULL,
    `create_time`     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`permission_id`),
    UNIQUE KEY `idx_permission_code` (`permission_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';

-- 角色-权限关联表
CREATE TABLE `d_role_permissions`
(
    `id`            int(11)  NOT NULL AUTO_INCREMENT,
    `role_id`       int(11)  NOT NULL,
    `permission_id` int(11)  NOT NULL,
    `create_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_role_permission` (`role_id`, `permission_id`)
#     FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
#     FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`permission_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色权限关联表';

-- 用户-角色关联表
CREATE TABLE `d_user_roles`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT,
    `user_id`     varchar(36) NOT NULL,
    `role_id`     int(11)     NOT NULL,
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_user_role` (`user_id`, `role_id`)
#     FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
#     FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色关联表';

-- 登录日志表
CREATE TABLE `d_login_logs`
(
    `log_id`      bigint(20)  NOT NULL AUTO_INCREMENT,
    `user_id`     varchar(36) NOT NULL,
    `ip_address`  varchar(50)          DEFAULT NULL,
    `device`      varchar(255)         DEFAULT NULL COMMENT '登录设备信息',
    `location`    varchar(100)         DEFAULT NULL COMMENT '登录地点',
    `status`      tinyint(1)  NOT NULL COMMENT '0-失败,1-成功',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`log_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='登录日志表';

show tables;

-- 插入用户表模拟数据
INSERT INTO `d_users` (`user_id`, `username`, `password_hash`, `email`, `phone`, `real_name`, `institution`, `title`,
                       `avatar`, `status`, `last_login`, `create_time`, `update_time`)
VALUES ('1', 'user1', 'password1', 'user1@example.com', '1234567890', '张三', '机构A', '教授',
        'http://example.com/avatar1.jpg', 1, NOW(), NOW(), NOW()),
       ('2', 'user2', 'password2', 'user2@example.com', '0987654321', '李四', '机构B', '讲师',
        'http://example.com/avatar2.jpg', 1, NOW(), NOW(), NOW());

-- 插入角色表模拟数据
INSERT INTO `d_roles` (`role_id`, `role_name`, `role_code`, `description`, `create_time`)
VALUES (1, '管理员', 'role_admin', '系统管理员', NOW()),
       (2, '普通用户', 'role_user', '普通注册用户', NOW());

update `d_roles` set `role_code` = 'role_admin' where `role_id` = 1;
update `d_roles` set `role_code` = 'role_user' where `role_id` = 2;

-- 插入权限表模拟数据
INSERT INTO `d_permissions` (`permission_id`, `permission_name`, `permission_code`, `description`, `create_time`)
VALUES (1, '查看用户', 'view_users', '查看用户列表', NOW()),
       (2, '编辑用户', 'edit_users', '编辑用户信息', NOW());

-- 插入角色-权限关联表模拟数据
INSERT INTO `d_role_permissions` (`id`, `role_id`, `permission_id`, `create_time`)
VALUES (1, 1, 1, NOW()),
       (2, 1, 2, NOW()),
       (3, 2, 1, NOW());

-- 插入用户-角色关联表模拟数据
INSERT INTO `d_user_roles` (`id`, `user_id`, `role_id`, `create_time`)
VALUES (1, '1', 1, NOW()),
       (2, '2', 2, NOW());

-- 插入登录日志表模拟数据
INSERT INTO `d_login_logs` (`log_id`, `user_id`, `ip_address`, `device`, `location`, `status`, `create_time`)
VALUES (1, '1', '192.168.1.1', 'PC', '地点A', 1, NOW()),
       (2, '2', '192.168.1.2', 'Mobile', '地点B', 1, NOW());
