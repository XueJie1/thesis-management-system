create database if not exists `thesismanage` character set utf8mb4;
use `thesismanage`;

-- 用户表
CREATE TABLE if not exists `user` (
                        `user_id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户唯一标识',
                        `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名（学号/工号）',
                        `password` VARCHAR(100) NOT NULL COMMENT '加密后的密码',
                        `role` INT NOT NULL COMMENT '角色：ADMIN/教师/学生',
                        `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号（扩展用）',
                        `otp_secret` VARCHAR(16) DEFAULT NULL COMMENT '2FA密钥（扩展用）',
                        `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `last_login` TIMESTAMP NULL DEFAULT NULL COMMENT '最后登录时间',
                        INDEX `idx_role` (`role`)  -- 角色过滤索引 [[1]]
) ENGINE=InnoDB COMMENT '用户基表';

-- 角色表
CREATE TABLE if not exists `role` (
                        `role_id` INT PRIMARY KEY,
                        `role_name` VARCHAR(50) NOT NULL
);
ALTER TABLE `user` MODIFY `role` INT, ADD FOREIGN KEY (`role`) REFERENCES `role`(`role_id`);

-- 院系表
CREATE TABLE if not exists `department` (
                              `department_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '院系ID',
                              `name` VARCHAR(100) NOT NULL UNIQUE COMMENT '院系名称',
                              `code` VARCHAR(20) NOT NULL UNIQUE COMMENT '院系编号'
) ENGINE=InnoDB COMMENT '院系信息';

-- 专业表
CREATE TABLE if not exists `major` (
                         `major_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '专业ID',
                         `name` VARCHAR(100) NOT NULL COMMENT '专业名称',
                         `code` VARCHAR(20) NOT NULL COMMENT '专业编号',
                         `department_id` INT NOT NULL COMMENT '所属院系ID',
                         FOREIGN KEY (`department_id`) REFERENCES `department`(`department_id`),
                         UNIQUE KEY `uk_department_major` (`department_id`, `code`)  -- 防止同院系重复专业 [[3]]
) ENGINE=InnoDB COMMENT '专业信息';

-- 学生表
CREATE TABLE if not exists `student` (
                           `student_id` BIGINT PRIMARY KEY COMMENT '继承自user.user_id',
                           `student_no` VARCHAR(20) NOT NULL UNIQUE COMMENT '学号',
                           `name` VARCHAR(50) NOT NULL COMMENT '学生姓名',
                           `department_id` INT NOT NULL COMMENT '所属院系ID',
                           `major_id` INT NOT NULL COMMENT '所属专业ID',
                           FOREIGN KEY (`student_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
                           FOREIGN KEY (`department_id`) REFERENCES `department`(`department_id`),
                           FOREIGN KEY (`major_id`) REFERENCES `major`(`major_id`)
) ENGINE=InnoDB COMMENT '学生扩展信息';

-- 教师表
CREATE TABLE if not exists `teacher` (
                           `teacher_id` BIGINT PRIMARY KEY COMMENT '继承自user.user_id',
                           `teacher_no` VARCHAR(20) NOT NULL UNIQUE COMMENT '工号',
                           `name` VARCHAR(50) NOT NULL COMMENT '教师姓名',
                           `department_id` INT NOT NULL COMMENT '所属院系ID',
#                            `major_id` INT NOT NULL COMMENT '所属专业ID',
                           FOREIGN KEY (`teacher_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
                           FOREIGN KEY (`department_id`) REFERENCES `department`(`department_id`)
#                            FOREIGN KEY (`major_id`) REFERENCES `major`(`major_id`)
) ENGINE=InnoDB COMMENT '教师扩展信息';


-- 论文表
CREATE TABLE if not exists `thesis` (
                          `thesis_id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '论文ID',
                          `title` VARCHAR(200) NOT NULL COMMENT '论文标题',
                          `keywords` VARCHAR(200) NOT NULL COMMENT '关键词（逗号分隔）',
                          `abstract` TEXT NOT NULL COMMENT '摘要',
                          `file_path` VARCHAR(255) NOT NULL COMMENT '文件存储路径（对象存储URL）',
                          `completion_time` DATE NOT NULL COMMENT '完成时间',
                          `status` ENUM('EXCELLENT', 'PASS', 'FAIL') DEFAULT NULL COMMENT '论文状态',
                          `download_count` INT DEFAULT 0 COMMENT '下载次数',
                          `student_id` BIGINT NOT NULL COMMENT '作者学生ID',
                          `teacher_id` BIGINT NOT NULL COMMENT '指导教师ID',
                          `department_id` INT NOT NULL COMMENT '冗余院系ID（优化统计）',
                          `major_id` INT NOT NULL COMMENT '冗余专业ID（优化统计）',
                          FOREIGN KEY (`student_id`) REFERENCES `student`(`student_id`),
                          FOREIGN KEY (`teacher_id`) REFERENCES `teacher`(`teacher_id`),
                          INDEX `idx_search` (`title`, `keywords`, `student_id`, `teacher_id`, `completion_time`),
                          INDEX `idx_status` (`status`)
) ENGINE=InnoDB COMMENT '论文信息';
-- 评论表
CREATE TABLE if not exists `comment` (
                           `comment_id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '评论ID',
                           `thesis_id` BIGINT NOT NULL COMMENT '关联论文ID',
                           `user_id` BIGINT NOT NULL COMMENT '评论者ID',
                           `content` TEXT NOT NULL COMMENT '评论内容',
                           `reply_user_id` BIGINT DEFAULT NULL COMMENT '回复者ID',
                           `reply_content` TEXT DEFAULT NULL COMMENT '管理员/教师回复内容',
                           `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
                           `replied_at` TIMESTAMP NULL DEFAULT NULL COMMENT '回复时间',
                           FOREIGN KEY (`thesis_id`) REFERENCES `thesis`(`thesis_id`),
                           FOREIGN KEY (`reply_user_id`) REFERENCES `user`(`user_id`),
                           FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
                           INDEX `idx_thesis_user` (`thesis_id`, `user_id`)  -- 联合索引加速查询 [[4]]
) ENGINE=InnoDB COMMENT '论文评论';

-- 公告表
CREATE TABLE if not exists `announcement` (
                                `announcement_id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '公告ID',
                                `title` VARCHAR(200) NOT NULL COMMENT '公告标题',
                                `content` TEXT NOT NULL COMMENT '公告内容',
                                `publisher_id` BIGINT NOT NULL COMMENT '发布者ID（管理员）',
                                `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
                                FOREIGN KEY (`publisher_id`) REFERENCES `user`(`user_id`),
                                INDEX `idx_time` (`created_at`)  -- 按时间排序索引 [[6]]
) ENGINE=InnoDB COMMENT '系统公告';