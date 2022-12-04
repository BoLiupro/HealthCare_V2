package com.healthcare.entity;

import java.math.BigDecimal;
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
@ApiModel(value="Drugs对象", description="")
public class Drugs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "drug_id", type = IdType.AUTO)
    private Integer drugId;

    private String name;

    private String type;

    private String quantity;

    private BigDecimal price;

    private String producer;


}
