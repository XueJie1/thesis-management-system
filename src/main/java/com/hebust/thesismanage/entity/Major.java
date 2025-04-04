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

/**
 * 专业实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("major")
public class Major {

    @TableId(value = "major_id", type = IdType.AUTO)
    private Integer majorId;

    @TableField("name")
    private String name;

    @TableField("code")
    private String code;

    @TableField("department_id")
    private Integer departmentId; // 外键关联 department 表，存储 department_id
}
