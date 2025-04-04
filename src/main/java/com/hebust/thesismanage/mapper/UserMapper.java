package com.hebust.thesismanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hebust.thesismanage.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
