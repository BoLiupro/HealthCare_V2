package com.healthcare.service.impl;

import com.healthcare.entity.TUser;
import com.healthcare.mapper.TUserMapper;
import com.healthcare.service.dao.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liubo
 * @since 2022-05-05
 */
@Service
public class UsersServiceImpl extends ServiceImpl<TUserMapper, TUser> implements UsersService {

}
