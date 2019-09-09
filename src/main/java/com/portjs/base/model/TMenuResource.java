package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.portjs.base.model.TMenuResource")
@Data
@TableName(value = "t_menu_resource")
public class TMenuResource {
    /**
     * 资源菜单表,每两位表示一个层级
     */
    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value="资源菜单表,每两位表示一个层级")
    private String id;

    /**
     * 父权限
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value="父权限")
    private String parentId;

    /**
     * 菜单或资源的名字
     */
    @TableField(value = "name")
    @ApiModelProperty(value="菜单或资源的名字")
    private String name;

    /**
     * 地址
     */
    @TableField(value = "path")
    @ApiModelProperty(value="地址")
    private String path;

    /**
     * 菜单等级
     */
    @TableField(value = "grade")
    @ApiModelProperty(value="菜单等级")
    private String grade;

    /**
     * 图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value="图标")
    private String icon;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="排序")
    private Integer sort;

    @TableField(value = "reserved1")
    @ApiModelProperty(value="null")
    private String reserved1;

    @TableField(value = "reserved2")
    @ApiModelProperty(value="null")
    private String reserved2;

    @TableField(value = "reserved3")
    @ApiModelProperty(value="null")
    private String reserved3;

    @TableField(value = "reserved4")
    @ApiModelProperty(value="null")
    private String reserved4;
}