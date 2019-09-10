package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@ApiModel(value="com.portjs.base.model.TRole")
@Data
@TableName(value = "t_role")
public class TRole implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value="主键")
    private String id;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value="角色名")
    private String roleName;

    /**
     * 角色描述
     */
    @TableField(value = "role_desc")
    @ApiModelProperty(value="角色描述")
    private String roleDesc;

    /**
     * 禁用状态0不可用 1可用
     */
    @TableField(value = "status")
    @ApiModelProperty(value="禁用状态0不可用 1可用")
    private Integer status;

    /**
     * 排序字段
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="排序字段")
    private Integer sort;

    /**
     * 部门
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(value="部门")
    private String deptId;

    /**
     * 创建人
     */
    @TableField(value = "create_id")
    @ApiModelProperty(value="创建人")
    private String createId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新者id
     */
    @TableField(value = "update_id")
    @ApiModelProperty(value="更新者id")
    private String updateId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
     * 备用字段1
     */
    @TableField(value = "reserved1")
    @ApiModelProperty(value="备用字段1")
    private String reserved1;

    /**
     * 备用字段2
     */
    @TableField(value = "reserved2")
    @ApiModelProperty(value="备用字段2")
    private String reserved2;

    /**
     * 备用字段3
     */
    @TableField(value = "reserved3")
    @ApiModelProperty(value="备用字段3")
    private String reserved3;

    /**
     * 备用字段4
     */
    @TableField(value = "reserved4")
    @ApiModelProperty(value="备用字段4")
    private String reserved4;

    /**
     * 备用字段5
     */
    @TableField(value = "reserved5")
    @ApiModelProperty(value="备用字段5")
    private String reserved5;

    /**
     * 备用字段6
     */
    @TableField(value = "reserved6")
    @ApiModelProperty(value="备用字段6")
    private String reserved6;

    /**
     * 备用字段7
     */
    @TableField(value = "reserved7")
    @ApiModelProperty(value="备用字段7")
    private String reserved7;

    /**
     * 备用字段8
     */
    @TableField(value = "reserved8")
    @ApiModelProperty(value="备用字段8")
    private String reserved8;
}