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
 * @date 2019/9/21 10:16 上午
 */
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
     * 模块名称
     */
    @TableField(value = "model")
    @ApiModelProperty(value = "模块名称")
    private String model;

    /**
     * 动作名称
     */
    @TableField(value = "action")
    @ApiModelProperty(value = "动作名称")
    private String action;

    /**
     * 参数
     */
    @TableField(value = "param")
    @ApiModelProperty(value = "参数")
    private String param;

    /**
     * 返回结果
     */
    @TableField(value = "result")
    @ApiModelProperty(value = "返回结果")
    private String result;

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
    @TableField(value = "req_content")
    @ApiModelProperty(value = "请求内容")
    private String reqContent;

    /**
     * 返回内容
     */
    @TableField(value = "res_content")
    @ApiModelProperty(value = "返回内容")
    private String resContent;

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
     * 公司id
     */
    @TableField(value = "company_id")
    @ApiModelProperty(value = "公司id")
    private String companyId;

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




}