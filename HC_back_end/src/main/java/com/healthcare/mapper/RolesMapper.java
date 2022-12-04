package com.healthcare.mapper;

import com.healthcare.entity.Roles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface RolesMapper extends BaseMapper<Roles> {

    List<String> getRoleCodeByUserName(String username);
}
