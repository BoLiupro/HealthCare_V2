package com.healthcare.mapper;

import com.healthcare.entity.Essays;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EssaysMapper extends BaseMapper<Essays> {
    List<Essays> selectByKey(String searchKey);
    List<Essays> selectByType(String type);
}
