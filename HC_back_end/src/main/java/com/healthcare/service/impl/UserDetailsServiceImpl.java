package com.healthcare.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.healthcare.entity.*;
import com.healthcare.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private RolesMapper RoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //判断非空
        if(StrUtil.isEmpty(username)){
            throw new RuntimeException("用户名不能为空");
        }
        //如果不为空,则从数据库中通过用户名获取对应的用户信息
        LambdaQueryWrapper<TUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TUser::getUsername,username);
        TUser sysUser = tUserMapper.selectOne(wrapper);
        if(sysUser==null){
            throw new UsernameNotFoundException(String.format("%s这个用户不存在",username));
        }
        //如果用户存在则要查询用户的角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> codeList= RoleMapper.getRoleCodeByUserName(username);
        codeList.forEach(code->{
            //如果数据库中没有添加ROLE_则这里需要添加一个
            //SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+code);
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(code);
            authorities.add(authority);
        });
        return new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
    }
}