package com.hebust.thesismanage.dto;

import com.hebust.thesismanage.entity.User;
import lombok.Data;

@Data
public class LoginResponse extends User {
    private String jwtToken;
    // 学生/教师姓名
    private String name;
    private String departmentName;
    private String majorName;
}
