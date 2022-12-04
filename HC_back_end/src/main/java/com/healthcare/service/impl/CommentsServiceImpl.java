package com.healthcare.service.impl;

import com.healthcare.entity.Comments;
import com.healthcare.mapper.CommentsMapper;
import com.healthcare.service.dao.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liubo
 * @since 2022-05-10
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

}
