package com.hebust.thesismanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hebust.thesismanage.entity.User;

public interface UserService extends IService<User> {
    public User getByUsername(String username);
}
