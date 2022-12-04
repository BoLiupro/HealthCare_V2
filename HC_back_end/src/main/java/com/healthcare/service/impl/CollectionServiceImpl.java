package com.healthcare.service.impl;


import com.healthcare.entity.Collections;
import com.healthcare.mapper.CollectionMapper;
import com.healthcare.service.dao.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liubo
 * @since 2022-05-09
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collections> implements CollectionService {

}
