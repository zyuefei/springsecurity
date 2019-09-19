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
 * @date 2019/9/18 2:38 下午
 */
@ApiModel(value = "com.portjs.base.model.TUserRole")
@Data
@TableName(value = "t_user_role")
public class TUserRole {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "null")
    private String id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色ID")
    private String roleId;
}