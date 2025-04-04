package com.hebust.thesismanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 公告实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("announcement")
public class Announcement {

    @TableId(value = "announcement_id", type = IdType.AUTO)
    private Long announcementId;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("publisher_id")
    private Long publisherId; // 外键关联 user 表，存储 user_id (发布者)

    @TableField("created_at")
    private LocalDateTime createdAt;
}
