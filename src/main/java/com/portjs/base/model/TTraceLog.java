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
 * @date 2019/9/18 2:35 下午
 */
@ApiModel(value = "com.portjs.base.model.TTraceLog")
@Data
@TableName(value = "t_trace_log")
public class TTraceLog {
    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty(value = "null")
    private Integer id;

    /**
     * 调用时间
     */
    @TableField(value = "CREATETIME")
    @ApiModelProperty(value = "调用时间")
    private Date createtime;

    /**
     * 系统响应耗时
     */
    @TableField(value = "consume_TIME")
    @ApiModelProperty(value = "系统响应耗时")
    private Integer consumeTime;

    /**
     * 功能模块名称
     */
    @TableField(value = "model_name")
    @ApiModelProperty(value = "功能模块名称")
    private String modelName;

    /**
     * 调用方法
     */
    @TableField(value = "METHOD_INFO")
    @ApiModelProperty(value = "调用方法")
    private String methodInfo;

    /**
     * 方法名称
     */
    @TableField(value = "METHOD_NAME")
    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @TableField(value = "REQUEST_MESSAGE")
    @ApiModelProperty(value = "null")
    private String requestMessage;

    @TableField(value = "RESPONSE_MESSAGE")
    @ApiModelProperty(value = "null")
    private String responseMessage;

    /**
     * 操作账号
     */
    @TableField(value = "OPERATOR_NAME")
    @ApiModelProperty(value = "操作账号")
    private String operatorName;

    /**
     * 客户端地址
     */
    @TableField(value = "remote_ip")
    @ApiModelProperty(value = "客户端地址")
    private String remoteIp;

    /**
     * 客户端用户名称
     */
    @TableField(value = "remote_user")
    @ApiModelProperty(value = "客户端用户名称")
    private String remoteUser;

    /**
     * 保留字段1
     */
    @TableField(value = "reserved1")
    @ApiModelProperty(value = "保留字段1")
    private String reserved1;

    /**
     * 保留字段2
     */
    @TableField(value = "reserved2")
    @ApiModelProperty(value = "保留字段2")
    private String reserved2;

    /**
     * 保留字段3
     */
    @TableField(value = "reserved3")
    @ApiModelProperty(value = "保留字段3")
    private String reserved3;

    /**
     * 保留字段4
     */
    @TableField(value = "reserved4")
    @ApiModelProperty(value = "保留字段4")
    private String reserved4;

    /**
     * 保留字段5
     */
    @TableField(value = "reserved5")
    @ApiModelProperty(value = "保留字段5")
    private String reserved5;
}