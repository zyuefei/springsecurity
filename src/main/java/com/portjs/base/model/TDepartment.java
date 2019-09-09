package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value="com.portjs.base.model.TDepartment")
@Data
@TableName(value = "t_department")
public class TDepartment {
    /**
     * 当前部门ID
     */
    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value="当前部门ID")
    private String id;

    /**
     * 父部门ID,根节点为00
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value="父部门ID,根节点为00")
    private String parentId;

    /**
     * 部门code,两位数字一个部门层级
     */
    @TableField(value = "code")
    @ApiModelProperty(value="部门code,两位数字一个部门层级")
    private String code;

    /**
     * 部门名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="部门名称")
    private String name;

    /**
     * 部门名称首字母，用于快速搜索
     */
    @TableField(value = "pingyin")
    @ApiModelProperty(value="部门名称首字母，用于快速搜索")
    private String pingyin;

    /**
     * 部门类型，1表示正常部门，0表示公司
     */
    @TableField(value = "type")
    @ApiModelProperty(value="部门类型，1表示正常部门，0表示公司")
    private Integer type;

    /**
     * 所属公司id
     */
    @TableField(value = "company_id")
    @ApiModelProperty(value="所属公司id")
    private String companyId;

    /**
     * 部门备注信息
     */
    @TableField(value = "notes")
    @ApiModelProperty(value="部门备注信息")
    private String notes;

    /**
     * 部门排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="部门排序")
    private Integer sort;

    /**
     * 创建人所属部门
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(value="创建人所属部门")
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

    /**
     * 保留字段4
     */
    @TableField(value = "reserved4")
    @ApiModelProperty(value="保留字段4")
    private String reserved4;

    /**
     * 保留字段5
     */
    @TableField(value = "reserved5")
    @ApiModelProperty(value="保留字段5")
    private String reserved5;
}