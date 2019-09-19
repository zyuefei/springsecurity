package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/11 10:34 上午
 */
@ApiModel(value = "com.portjs.base.model.TRole")
@Data
@TableName(value = "t_role")
public class TRole extends BaseEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value = "角色名")
    private String roleName;

    /**
     * 角色描述
     */
    @TableField(value = "role_desc")
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    /**
     * 禁用状态0不可用 1可用
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "禁用状态0不可用 1可用")
    private Integer status;

    /**
     * 排序字段
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序字段")
    private Integer sort;


}