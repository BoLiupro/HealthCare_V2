package com.healthcare.mapper;

import com.healthcare.entity.Comments;
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
public interface CommentsMapper extends BaseMapper<Comments> {
    void updateState(Integer comment_id,Integer state);
    List<Comments> selectAll(Integer essay_id);
    List<Comments> selectChecked(Integer essay_id,Integer state);
}
