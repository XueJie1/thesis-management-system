package com.hebust.thesismanage.controller;

import com.hebust.thesismanage.common.CustomException;
import com.hebust.thesismanage.common.Result;
import com.hebust.thesismanage.config.JwtUtil;
import com.hebust.thesismanage.dto.LoginResponse;
import com.hebust.thesismanage.dto.LoginRequest;
import com.hebust.thesismanage.entity.Student;
import com.hebust.thesismanage.entity.Teacher;
import com.hebust.thesismanage.entity.User;
import com.hebust.thesismanage.service.StudentService;
import com.hebust.thesismanage.service.TeacherService;
import com.hebust.thesismanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping()
//    public Result<?> login(@RequestBody LoginRequest loginRequest) {
            public Result<?> login(String username, String password) {
//        String username = loginRequest.getUsername();
//        String password = loginRequest.getPassword();
        // 1. 从用户表中查询 userId
        User user = userService.getByUsername(username);
        // 2. 如果用户存在且密码与数据库相同，那么就允许登录并返回结果和 JWT Token
        if (user != null && password.equals(user.getPassword())) {
            // 生成 JWT Token
            String token = jwtUtil.generateToken(username, user.getRole());
            LoginResponse response = new LoginResponse();
            response.setJwtToken(token);
            BeanUtils.copyProperties(user, response);
            // 插入学生/教师姓名
            if (user.getRole().equals(2)) { // STUDENT
                Student byStudentNo = studentService.getByStudentNo(response.getUsername());
                response.setName(byStudentNo.getName());
            } else if (user.getRole().equals(3)) {  // TEACHER
                Teacher byTeacherNo = teacherService.getByTeacherNo(response.getUsername());
                response.setName(byTeacherNo.getName());
                response.setMajorName("");
            } else if (user.getRole().equals(1)) {  // ADMIN
                log.info("用户名直接为admin");
                response.setName("admin");
            } else {
                throw new CustomException("用户角色不存在");
            }
            response.setPassword("");
            log.info("用户{}登录成功！ID:{}", user.getUsername(), user.getUserId());
            return Result.success(response);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
