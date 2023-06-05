package com.github.cloudgyb.heartchat.mapper;

import com.github.cloudgyb.heartchat.domain.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author geng
 * @since 2023/02/26 15:12:47
 */
@SpringBootTest
public class UserEntityTest {
    @Autowired
    UserEntityMapper userEntityMapper;

    @Test
    public void testSelect() {
        List<UserEntity> userEntities = userEntityMapper.selectList(null);
        Assertions.assertTrue(userEntities.size() > 0);
    }
}
