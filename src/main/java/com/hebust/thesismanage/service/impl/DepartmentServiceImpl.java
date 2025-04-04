package com.hebust.thesismanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hebust.thesismanage.entity.Department;
import com.hebust.thesismanage.mapper.DepartmentMapper;
import com.hebust.thesismanage.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
}
