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
@ApiModel(value="Essays对象", description="")
public class Essays implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "essay_id", type = IdType.AUTO)
    private Integer essayId;

    private String title;

    private String text;

    private String writer;

    private String classification;

    private String picture;


}
