package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.portjs.base.model.TRoleMenu")
@Data
@TableName(value = "t_role_menu")
public class TRoleMenu {
    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value="null")
    private String id;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value="角色ID")
    private String roleId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    @ApiModelProperty(value="菜单ID")
    private String menuId;

    /**
     * 保留字段1
     */
    @TableField(value = "reserved1")
    @ApiModelProperty(value="保留字段1")
    private String reserved1;

    /**
     * 保留字段2
     */
    @TableField(value = "reserved2")
    @ApiModelProperty(value="保留字段2")
    private String reserved2;

    /**
     * 保留字段3
     */
    @TableField(value = "reserved3")
    @ApiModelProperty(value="保留字段3")
    private String reserved3;
}