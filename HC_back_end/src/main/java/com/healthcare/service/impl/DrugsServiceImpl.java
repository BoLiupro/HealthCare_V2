package com.healthcare.service.impl;

import com.healthcare.entity.Drugs;
import com.healthcare.mapper.DrugsMapper;
import com.healthcare.service.dao.DrugsService;
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
public class DrugsServiceImpl extends ServiceImpl<DrugsMapper, Drugs> implements DrugsService {

}
