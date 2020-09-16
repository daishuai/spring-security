package com.daishuai.security;

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

    /**
     * 通过手机号查询用户
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);
}
