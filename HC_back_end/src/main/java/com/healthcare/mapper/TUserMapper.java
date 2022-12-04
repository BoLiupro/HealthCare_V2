package com.healthcare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healthcare.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liubo
 * @since 2022-05-05
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
    TUser getOneUser(String username);
    List<TUser> getAllUser();
}
