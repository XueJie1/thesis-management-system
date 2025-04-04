package com.hebust.thesismanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hebust.thesismanage.dto.ThesisDto;
import com.hebust.thesismanage.entity.Thesis;
import com.hebust.thesismanage.mapper.ThesisMapper;
import com.hebust.thesismanage.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Lazy
@Service
public class ThesisServiceImpl extends ServiceImpl<ThesisMapper, Thesis> implements ThesisService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MajorService majorService;
    /**
     * 根据 id 获取单个 ThesisDto
     *
     * @param id 论文id
     * @return ThesisDto
     */
    @Override
    public ThesisDto getThesisDto(Long id) {
        ThesisDto result = new ThesisDto();
        // 1. 根据id查到Thesis对象
        Thesis thesis = this.getById(id);
        BeanUtils.copyProperties(thesis, result);
        // 2. 根据Thesis对象里的学生、教师等的id去对应的getById()方法查，赋值到对应的 result 属性值
        result.setStudentName(studentService.getById(thesis.getStudentId()).getName());
        result.setMajorName(majorService.getById(thesis.getMajorId()).getName());
        result.setDepartmentName(departmentService.getById(thesis.getDepartmentId()).getName());
        result.setTeacherName(teacherService.getById(thesis.getTeacherId()).getName());
        return result;
    }

    /**
     * 获取所有论文数据
     *
     * @return
     */
    @Override
    public List<ThesisDto> getAll() {
        // 1. 从 ThesisService 提供的 list() 方法查到所有 thesis 对象
        List<Thesis> thesisList = this.list();
        // 2. 转换为 ThesisDto 列表
        List<ThesisDto> thesisDtoList = thesisList.stream().map(thesis -> {
            ThesisDto result = new ThesisDto();
            BeanUtils.copyProperties(thesis, result);
            result.setStudentName(studentService.getById(thesis.getStudentId()).getName());
            result.setMajorName(majorService.getById(thesis.getMajorId()).getName());
            result.setDepartmentName(departmentService.getById(thesis.getDepartmentId()).getName());
            result.setTeacherName(teacherService.getById(thesis.getTeacherId()).getName());
            return result;
        }).collect(Collectors.toList());

        // 3. 返回 ThesisDto 列表
        return thesisDtoList;
    }

    @Override
    public Page<ThesisDto> page(int page, int pageSize, String title) {
        Page<Thesis> thesisPage = new Page<>(page, pageSize);
        Page<ThesisDto> thesisDtoPage = new Page<>(page, pageSize);

        // 条件构造器
        LambdaQueryWrapper<Thesis> queryWrapper = new LambdaQueryWrapper<>();
        // 构造查询条件：查询论文标题含有 title 的数据
        queryWrapper.like(StringUtils.isNotEmpty(title) && title != null, Thesis::getTitle, title);
        // 构造排序条件：按照完成时间排序
        queryWrapper.orderByDesc(Thesis::getCompletionTime);
        // 查询并分页，此操作由 Mybaits Plus 完成，我们只需要调用方法即可。
        this.page(thesisPage, queryWrapper);
        // 把查到的 thesisPage 里的内容复制到 thesisDtoPage 里，由于二者的内容(records)的类型不同，所以无法直接复制
        BeanUtils.copyProperties(thesisPage, thesisDtoPage, "records");
        // 处理 thesisPage.records 的复制
        List<Thesis> thesisList = thesisPage.getRecords();
        List<ThesisDto> thesisDtoList = thesisList.stream().map((item) -> {
            ThesisDto thesisDto = new ThesisDto();
            BeanUtils.copyProperties(item, thesisDto);

            // 查询学生、教师姓名等 dto 中独有的内容，并赋值给 thesisDto
            thesisDto.setStudentName(studentService.getById(item.getStudentId()).getName());
            thesisDto.setTeacherName(teacherService.getById(item.getTeacherId()).getName());
            thesisDto.setDepartmentName(departmentService.getById(item.getDepartmentId()).getName());
            thesisDto.setMajorName(majorService.getById(item.getMajorId()).getName());

            return thesisDto;

        }).collect(Collectors.toList());
        thesisDtoPage.setRecords(thesisDtoList);
        return thesisDtoPage;
    }

}


