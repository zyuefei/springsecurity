package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com.portjs.base.model.TTraceLog")
@Data
@TableName(value = "t_trace_log")
public class TTraceLog {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 系统响应耗时
     */
    @TableField(value = "consume_time")
    @ApiModelProperty(value = "系统响应耗时")
    private Integer consumeTime;

    /**
     * 请求url
     */
    @TableField(value = "url")
    @ApiModelProperty(value = "请求url")
    private String url;

    /**
     * 类、方法位置
     */
    @TableField(value = "class_method")
    @ApiModelProperty(value = "类、方法位置")
    private String classMethod;

    /**
     * 请求内容
     */
    @TableField(value = "request_content")
    @ApiModelProperty(value = "请求内容")
    private String requestContent;

    /**
     * 返回内容
     */
    @TableField(value = "response_content")
    @ApiModelProperty(value = "返回内容")
    private String responseContent;

    /**
     * 客户端地址
     */
    @TableField(value = "remote_ip")
    @ApiModelProperty(value = "客户端地址")
    private String remoteIp;

    /**
     * 备注
     */
    @TableField(value = "notes")
    @ApiModelProperty(value = "备注")
    private String notes;

    /**
     * 部门
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(value = "部门")
    private String deptId;

    /**
     * 创建人
     */
    @TableField(value = "create_id")
    @ApiModelProperty(value = "创建人")
    private String createId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新者id
     */
    @TableField(value = "update_id")
    @ApiModelProperty(value = "更新者id")
    private String updateId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}