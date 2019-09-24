package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author  zhangyuefei
 * @date  2019/9/19 6:53 下午
 * @version 1.0
 */
@ApiModel(value="com.portjs.base.model.SpringSession")
@Data
@TableName(value = "spring_session")
public class SpringSession {
    @TableId(value = "PRIMARY_ID", type = IdType.INPUT)
    @ApiModelProperty(value="null")
    private String primaryId;

    @TableField(value = "SESSION_ID")
    @ApiModelProperty(value="null")
    private String sessionId;

    @TableField(value = "CREATION_TIME")
    @ApiModelProperty(value="null")
    private Long creationTime;

    @TableField(value = "LAST_ACCESS_TIME")
    @ApiModelProperty(value="null")
    private Long lastAccessTime;

    @TableField(value = "MAX_INACTIVE_INTERVAL")
    @ApiModelProperty(value="null")
    private Integer maxInactiveInterval;

    @TableField(value = "EXPIRY_TIME")
    @ApiModelProperty(value="null")
    private Long expiryTime;

    @TableField(value = "PRINCIPAL_NAME")
    @ApiModelProperty(value="null")
    private String principalName;
}