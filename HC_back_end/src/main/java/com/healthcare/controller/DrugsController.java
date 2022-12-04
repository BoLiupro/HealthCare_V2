package com.healthcare.controller;

import com.healthcare.entity.Drugs;
import com.healthcare.mapper.DrugsMapper;
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
 * @since 2022-05-10
 */
@RestController
@RequestMapping("/drugs")
public class DrugsController {

    @Autowired
    DrugsMapper drugsMapper;

    @PostMapping("/selectByName")
    public Result selectByName(@RequestParam("name") String name ){
        List<Drugs> drugsList=drugsMapper.selectByName("%"+name+"%");
        Result result=Result.ok().message("success");
        result.data("drugs",drugsList);
        return result;
    }

    @PostMapping("/selectByType")
    public Result selectByType(@RequestParam("type") String type ){
        List<Drugs> drugsList=drugsMapper.selectByType("%"+type+"%");
        Result result=Result.ok().message("success");
        result.data("drugs",drugsList);
        return result;
    }

    @PostMapping("/selectByProducer")
    public Result selectByProducer(@RequestParam("producer") String producer ){
        List<Drugs> drugsList=drugsMapper.selectByProducer("%"+producer+"%");
        Result result=Result.ok().message("success");
        result.data("drugs",drugsList);
        return result;
    }
}

