package com.hebust.thesismanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hebust.thesismanage.entity.Major;
import com.hebust.thesismanage.mapper.MajorMapper;
import com.hebust.thesismanage.service.MajorService;
import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {
}
