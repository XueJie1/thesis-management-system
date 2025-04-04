package com.hebust.thesismanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hebust.thesismanage.dto.TeacherDto;
import com.hebust.thesismanage.dto.TeacherDto;
import com.hebust.thesismanage.entity.Department;
import com.hebust.thesismanage.entity.Major;
import com.hebust.thesismanage.entity.Teacher;
import com.hebust.thesismanage.mapper.TeacherMapper;
import com.hebust.thesismanage.service.DepartmentService;
import com.hebust.thesismanage.service.MajorService;
import com.hebust.thesismanage.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MajorService majorService;
    @Override
    public Teacher getByTeacherNo(String teacherNo) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(teacherNo), Teacher::getTeacherNo, teacherNo);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<TeacherDto> getAll() {
        List<Teacher> teachers = this.list();
        List<TeacherDto> teacherDtos = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherDto teacherDto = new TeacherDto();
            BeanUtils.copyProperties(teacher, teacherDto);
            teacherDtos.add(teacherDto);
        }
        teacherDtos = teacherDtos.stream().map(item -> {
            TeacherDto teacherDto = new TeacherDto();
            BeanUtils.copyProperties(item, teacherDto);
            teacherDto.setDepartmentName(
                    Optional.ofNullable(departmentService.getById(teacherDto.getDepartmentId()))
                            .map(Department::getName)
                            .orElse(null)
            );
            return teacherDto;
        }).collect(Collectors.toList());
        return teacherDtos;
    }
}
