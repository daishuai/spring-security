package com.daishuai.security.browser;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/31 22:55
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
