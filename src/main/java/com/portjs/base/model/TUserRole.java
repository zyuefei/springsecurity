package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.portjs.base.model.TUserRole")
@Data
@TableName(value = "t_user_role")
public class TUserRole {
    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value="null")
    private String id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户ID")
    private String userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value="角色ID")
    private String roleId;

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