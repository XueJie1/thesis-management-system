package com.hebust.thesismanage.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hebust.thesismanage.entity.Student;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StudentDto extends Student {
    private String departmentName;
    private String majorName;
    private String phone;
    private String otpSecret;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
}
