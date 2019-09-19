package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/18 2:37 下午
 */
@ApiModel(value = "com.portjs.base.model.TRoleMenu")
@Data
@TableName(value = "t_role_menu")
public class TRoleMenu {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "null")
    private String id;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色ID")
    private String roleId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    @ApiModelProperty(value = "菜单ID")
    private String menuId;
}