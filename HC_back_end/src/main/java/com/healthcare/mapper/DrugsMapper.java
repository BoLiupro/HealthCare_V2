package com.healthcare.mapper;

import com.healthcare.entity.Drugs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liubo
 * @since 2022-05-10
 */
@Mapper
public interface DrugsMapper extends BaseMapper<Drugs> {

    List<Drugs> selectByType(String searchKey);

    List<Drugs> selectByName(String name);

    List<Drugs> selectByProducer(String s);
}
