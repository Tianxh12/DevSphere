package com.devsphere.dto;

import com.devsphere.entity.DishFlavor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO implements Serializable {
    private Long id;
    //名称
    private String name;
    //分类id
    private Long categoryId;
    //价格
    private BigDecimal price;
    //图片
    private String image;
    //描述信息
    private String description;
    //0 停售 1 起售
    private Integer status;

    private List<DishFlavor> flavors = new ArrayList<>();
}
