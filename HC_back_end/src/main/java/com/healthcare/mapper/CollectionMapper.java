package com.healthcare.mapper;

import com.healthcare.entity.Collections;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liubo
 * @since 2022-05-09
 */
@Mapper
public interface CollectionMapper extends BaseMapper<Collections> {
    void deleteCollection(Integer user_id,Integer essay_id);
    List<Collections> selectCollections(Integer user_id);
}
