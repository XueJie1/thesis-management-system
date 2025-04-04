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

import java.time.LocalDate;

/**
 * 论文实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("thesis")
public class Thesis {

    @TableId(value = "thesis_id", type = IdType.AUTO)
    private Long thesisId;

    @TableField("title")
    private String title;

    @TableField("keywords")
    private String keywords;

    @TableField("abstract")
    private String abstractText; // 注意避免使用 SQL 关键字 'abstract' 作为字段名，这里用 abstractText

    @TableField("file_path")
    private String filePath;

    @TableField("completion_time")
    private LocalDate completionTime;

    @TableField("status")
    private String status; // ENUM 类型，对应字符串 'EXCELLENT', 'PASS', 'FAIL'

    @TableField("download_count")
    private Integer downloadCount;

    @TableField("student_id")
    private Long studentId; // 外键关联 student 表，存储 student_id

    @TableField("teacher_id")
    private Long teacherId; // 外键关联 teacher 表，存储 teacher_id

    @TableField("department_id")
    private Integer departmentId; // 冗余字段，存储 department_id

    @TableField("major_id")
    private Integer majorId; // 冗余字段，存储 major_id
}
