package com.healthcare.controller;


import com.healthcare.entity.Collections;
import com.healthcare.mapper.CollectionMapper;
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
 * @since 2022-05-05
 */
@RestController
@RequestMapping("/users")
public class CollectionController {

    @Autowired
    CollectionMapper collectionMapper;

    @PostMapping("/setCollection")
    public Result setCollection(@RequestParam("user_id") Integer user_id,
                                @RequestParam("essay_id") Integer essay_id){
        List<Collections> collectionList=collectionMapper.selectCollections(user_id);
        boolean check=false;
        Result result =Result.ok();
        for(Collections collections:collectionList){
            if(collections.getEssayId()==essay_id) check=true;
        }
        if(!check){
            Collections collection =new Collections(user_id,essay_id);
            collectionMapper.insert(collection);
            result.message("success");
        }else{
            result.message("already collected");
        }
        return result;
    }

    @PostMapping("/checkCollection")
    public Result checkCollection(@RequestParam("user_id") Integer user_id,
                                  @RequestParam("essay_id") Integer essay_id){
        List<Collections> collectionList=collectionMapper.selectCollections(user_id);
        boolean check=false;
        Result result =Result.ok();
        for(Collections collections:collectionList){
            if(collections.getEssayId()==essay_id) check=true;
        }
        result.message(String.valueOf(check));
        return result;
    }

    @PostMapping("/deleteCollection")
    public Result deleteCollection(@RequestParam("user_id") Integer user_id,
                                @RequestParam("essay_id") Integer essay_id){
        collectionMapper.deleteCollection(user_id,essay_id);
        Result result=Result.ok().message("success");
        return result;
    }

    @PostMapping("/getCollection")
    public Result getCollection(@RequestParam("user_id") Integer user_id){
        List<Collections> collectionList=collectionMapper.selectCollections(user_id);
        Result result=Result.ok().message("success");
        result.data("collections",collectionList);
        return result;
    }

}

