package com.hebust.thesismanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hebust.thesismanage.dto.TeacherDto;
import com.hebust.thesismanage.entity.Teacher;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
    public Teacher getByTeacherNo(String teacherNo);
    public List<TeacherDto> getAll();
}
