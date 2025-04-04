package com.hebust.thesismanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hebust.thesismanage.common.Result;
import com.hebust.thesismanage.dto.StudentDto;
import com.hebust.thesismanage.entity.Student;
import com.hebust.thesismanage.service.StudentService;
import com.hebust.thesismanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/page")
    public Result<Page<StudentDto>> page(Integer page, Integer pageSize, String searchName) {
        Page<StudentDto> studentPage = studentService.page(page, pageSize, searchName);
        return Result.success(studentPage);
    }
    @GetMapping("/all")
    public Result<List<StudentDto>> getAll() {
        List<StudentDto> all = studentService.getAll();
        return Result.success(all);
    }
}
