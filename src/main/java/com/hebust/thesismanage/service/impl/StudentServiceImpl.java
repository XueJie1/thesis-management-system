package com.hebust.thesismanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hebust.thesismanage.dto.StudentDto;
import com.hebust.thesismanage.entity.Department;
import com.hebust.thesismanage.entity.Major;
import com.hebust.thesismanage.entity.Student;
import com.hebust.thesismanage.entity.User;
import com.hebust.thesismanage.mapper.StudentMapper;
import com.hebust.thesismanage.service.DepartmentService;
import com.hebust.thesismanage.service.MajorService;
import com.hebust.thesismanage.service.StudentService;
import com.hebust.thesismanage.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MajorService majorService;

    @Override
    public Student getByStudentNo(String stuNo) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(stuNo), Student::getStudentNo, stuNo);
        return this.getOne(queryWrapper);
    }

    @Override
    public Page<StudentDto> page(Integer page, Integer pageSize, String searchName) {
        Page<Student> studentPage = new Page<>(page, pageSize);
        Page<StudentDto> studentDtoPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(searchName) && searchName != null, Student::getName, searchName);
        this.page(studentPage, queryWrapper);
        BeanUtils.copyProperties(studentPage, studentDtoPage, "records");
        List<Student> records = studentPage.getRecords();
        List<StudentDto> collect = records.stream().map((item) -> {
            StudentDto studentDto = new StudentDto();
            BeanUtils.copyProperties(item, studentDto);

            studentDto.setMajorName(
                    Optional.ofNullable(majorService.getById(studentDto.getMajorId()))
                            .map(Major::getName)
                            .orElse(null)
            );
            studentDto.setDepartmentName(
                    Optional.ofNullable(departmentService.getById(studentDto.getDepartmentId()))
                            .map(Department::getName)
                            .orElse(null)
            );
            studentDto.setCreatedAt(
                    Optional.ofNullable(userService.getById(studentDto.getStudentId()))
                            .map(User::getCreatedAt)
                            .orElse(null)
            );
            studentDto.setLastLogin(
                    Optional.ofNullable(userService.getById(studentDto.getStudentId()))
                            .map(User::getLastLogin)
                            .orElse(null)
            );

            return studentDto;
        }).collect(Collectors.toList());
        studentDtoPage.setRecords(collect);
        return studentDtoPage;
    }

    public List<StudentDto> getAll() {
        List<Student> students = this.list();
        List<StudentDto> studentDtos = new ArrayList<>();
        // 从 students 复制到 studentDtos
        for (Student student : students) {
            StudentDto studentDto = new StudentDto();
            BeanUtils.copyProperties(student, studentDto);
            studentDtos.add(studentDto);
        }
        List<StudentDto> collect = studentDtos.stream().map(item -> {
            StudentDto studentDto = new StudentDto();
            BeanUtils.copyProperties(item, studentDto);

            studentDto.setMajorName(
                    Optional.ofNullable(majorService.getById(studentDto.getMajorId()))
                            .map(Major::getName)
                            .orElse(null)
            );
            studentDto.setDepartmentName(
                    Optional.ofNullable(departmentService.getById(studentDto.getDepartmentId()))
                            .map(Department::getName)
                            .orElse(null)
            );
            return studentDto;
        }).collect(Collectors.toList());
        return collect;
    }
}
