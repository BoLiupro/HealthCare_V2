package com.healthcare.controller;

import com.healthcare.entity.Collections;
import com.healthcare.entity.Essays;
import com.healthcare.mapper.CollectionMapper;
import com.healthcare.mapper.EssaysMapper;
import com.healthcare.mapper.TUserMapper;
import com.healthcare.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liubo
 * @since 2022-05-09
 */
@RestController
@RequestMapping("/essays")
public class EssaysController {

    @Autowired
    EssaysMapper essaysMapper;
    @Autowired
    TUserMapper tUserMapper;
    @Autowired
    CollectionMapper collectionMapper;

    @PostMapping("/getEssayByKey")
    public Result getEssayByKey(@RequestParam("searchKey") String searchKey){
        List<Essays> essays=essaysMapper.selectByKey("%"+searchKey+"%");
        Result result=Result.ok().message("success");
        result.data("essay",essays);
        return result;
    }

    @PostMapping("/getEssayById")
    public Result getEssayById(@RequestParam("essay_id") String essay_id){
        Essays essays=essaysMapper.selectById(essay_id);
        Result result=Result.ok().message("success");
        result.data("essay",essays);
        return result;
    }

    @PostMapping("/getEssayByType")
    public Result getEssayBytype(@RequestParam("type") String type){
        List<Essays> essays=essaysMapper.selectByType(type);
        Result result=Result.ok().message("success");
        result.data("essay",essays);
        return result;
    }

    @PostMapping("/recommend")
    public Result getEssayBytype(@RequestParam("user_id") Integer user_id){
        List<Collections> collections=collectionMapper.selectCollections(user_id);
        Collections collection=collections.get(collections.size()-1);
        Essays recommend_essay=essaysMapper.selectById(collection.getEssayId());
        List<Essays> recommend_essays=essaysMapper.selectByType(recommend_essay.getClassification());
        Result result=Result.ok().message("success");
        result.data("essay",recommend_essays);
        return result;
    }

}

