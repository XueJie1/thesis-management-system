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
 * 评论实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comment")
public class Comment {

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    @TableField("thesis_id")
    private Long thesisId; // 外键关联 thesis 表，存储 thesis_id

    @TableField("user_id")
    private Long userId; // 外键关联 user 表，存储 user_id (评论者)

    @TableField("content")
    private String content;

    @TableField("reply_user_id")
    private Long replyUserId; // 外键关联 user 表，存储 user_id (回复者)

    @TableField("reply_content")
    private String replyContent;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("replied_at")
    private LocalDateTime repliedAt;
}
