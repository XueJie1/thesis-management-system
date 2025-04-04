package com.hebust.thesismanage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 教师实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("teacher")
public class Teacher {

    @TableId("teacher_id") // 主键继承自 user 表
    private Long teacherId;

    @TableField("teacher_no")
    private String teacherNo;

    @TableField("name")
    private String name;

    @TableField("department_id")
    private Integer departmentId; // 外键关联 department 表，存储 department_id

//    @TableField("major_id")
//    private Integer majorId; // 外键关联 major 表，存储 major_id
}
