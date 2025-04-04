package com.hebust.thesismanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hebust.thesismanage.dto.StudentDto;
import com.hebust.thesismanage.entity.Student;

import java.util.List;

public interface StudentService extends IService<Student> {
    public Student getByStudentNo(String stuNo);
    public Page<StudentDto> page(Integer page, Integer pageSize, String searchName);
    public List<StudentDto> getAll();
}
