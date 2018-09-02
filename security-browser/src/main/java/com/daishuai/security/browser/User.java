package com.daishuai.security.browser;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: 用户
 * @Author: daishuai
 * @CreateDate: 2018/8/31 22:52
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
@Table(name = "user")
@Entity
public class User {

    public interface UserSimpleView{};

    public interface UserDetailView extends UserSimpleView{};

    @Id
    @JsonView(UserSimpleView.class)
    private Integer userId;

    @NotBlank(message = "用户不能为空")
    @JsonView(UserSimpleView.class)
    private String username;

    @JsonView(UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    @JsonView(UserSimpleView.class)
    private Integer roleId;
}
