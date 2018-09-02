package com.daishuai.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/31 23:00
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class MyUserDetails implements UserDetails {


    private User user;


    public MyUserDetails(User user){
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.user == null ? "" : this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user == null ? "" : this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
