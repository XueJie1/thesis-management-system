package com.hebust.thesismanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hebust.thesismanage.dto.ThesisDto;
import com.hebust.thesismanage.entity.Thesis;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Lazy
@Transactional
public interface ThesisService extends IService<Thesis> {
    public ThesisDto getThesisDto(Long id);
    public List<ThesisDto> getAll();
    public Page<ThesisDto> page(int page, int pageSize, String name);
//    public boolean saveDto(ThesisDto thesisDto);
}
