package com.healthcare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liubo
 * @since 2022-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Collection对象", description="")
public class Collections implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer essayId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    public Collections(Integer user_id, Integer essay_id) {
        this.essayId=essay_id;
        this.userId=user_id;
    }
}
