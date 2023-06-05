package com.github.cloudgyb.heartchat.modules.common.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.cloudgyb.heartchat.domain.UserEntity;
import com.github.cloudgyb.heartchat.mapper.UserEntityMapper;
import org.springframework.stereotype.Service;

/**
 * @author geng
 * @since 2023/03/01 21:12:14
 */
@Service
public class UserService extends ServiceImpl<UserEntityMapper, UserEntity> {
    private final UserEntityMapper userEntityMapper;

    public UserService(UserEntityMapper userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
    }

    public UserEntity getByUsername(String username) {
        return userEntityMapper.selectOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getUsername, username)
                        .last("limit 1")
        );
    }
}
