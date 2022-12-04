package com.healthcare.controller;

import com.healthcare.entity.Comments;
import com.healthcare.mapper.CommentsMapper;
import com.healthcare.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/comments")
public class CommentController {

    /*
    0未审核，1审核通过，-1审核不通过
     */
    @Autowired
    CommentsMapper commentsMapper;

    @PostMapping("/putComments")
    public Result putComments(@RequestParam("user_id") Integer user_id,
                               @RequestParam("essay_id") Integer essay_id,
                               @RequestParam("comment") String comment){
        Comments comments=new Comments(user_id,essay_id,comment);
        commentsMapper.insert(comments);
        Result result=Result.ok().message("successfully put, wait for check");
        result.data("comment",comments);
        return result;
    }

    @PreAuthorize("hasRole('admin')")//只有admin才可以审核
    @PostMapping("/setComments")
    public Result setComments(@RequestParam("comment_id") Integer comment_id,@RequestParam("state") Integer state){
        commentsMapper.updateState(comment_id,state);
        Result result=Result.ok().message("successfully checked");
        return result;
    }

    @PreAuthorize("hasRole('admin')")//只有admin才可见
    @PostMapping("/getAllComments")
    public Result getAllComments(@RequestParam("essay_id") Integer essay_id){
        List<Comments> commentsList=commentsMapper.selectAll(essay_id);
        Result result=Result.ok().message("success");
        result.data("AllComments",commentsList);
        return result;
    }

    @PostMapping("/getCheckedComments")
    public Result getCheckedComments(@RequestParam("essay_id") Integer essay_id){
        List<Comments> commentsList=commentsMapper.selectChecked(essay_id,1);
        Result result=Result.ok().message("success");
        result.data("CheckedComments",commentsList);
        return result;
    }
}

