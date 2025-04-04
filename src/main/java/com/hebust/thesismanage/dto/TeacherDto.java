package com.hebust.thesismanage.dto;

import com.hebust.thesismanage.entity.Teacher;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TeacherDto extends Teacher {
    private String departmentName;
    private String phone;
    private String otpSecret;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
}
