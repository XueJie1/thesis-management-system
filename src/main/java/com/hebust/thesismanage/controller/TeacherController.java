package com.hebust.thesismanage.controller;

import com.hebust.thesismanage.common.Result;
import com.hebust.thesismanage.dto.TeacherDto;
import com.hebust.thesismanage.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/all")
    public Result<List<TeacherDto>> getAll() {
        List<TeacherDto> all = teacherService.getAll();
        return Result.success(all);
    }
}
