package com.healthcare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liubo
 * @since 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Comments对象", description="")
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer essayId;

    private String comment;

    private Integer state;

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    private Integer userId;


    public Comments(Integer user_id,Integer essay_id, String comment) {
        this.userId=user_id;
        this.essayId=essay_id;
        this.comment=comment;
        this.state=0;
    }
}
