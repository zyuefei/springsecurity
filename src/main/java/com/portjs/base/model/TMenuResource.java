package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.beans.Transient;
import java.util.List;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/11 10:24 上午
 */
@ApiModel(value = "com.portjs.base.model.TMenuResource")
@Data
@TableName(value = "t_menu_resource")
public class TMenuResource {
    /**
     * 资源菜单表
     */
    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value = "资源菜单表")
    private String id;

    /**
     * 父节点id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父节点id")
    private String parentId;

    /**
     * 菜单或资源的名字
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "菜单或资源的名字")
    private String name;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 地址
     */
    @TableField(value = "path")
    @ApiModelProperty(value = "地址")
    private String path;

    /**
     * 菜单等级
     */
    @TableField(value = "grade")
    @ApiModelProperty(value = "菜单等级")
    private String grade;

    /**
     * 图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 0是菜单  1是资源
     */
    @TableField(value = "resource_menu")
    @ApiModelProperty(value = "0是菜单  1是资源")
    private Integer resourceMenu;



    @TableField(exist = false)
    private List<TMenuResource> children;
}