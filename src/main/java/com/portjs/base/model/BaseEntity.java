package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.Transient;

import java.util.Date;

@Data
public class BaseEntity {


    @TableField(exist = false)
    private String createName;

    @TableField(exist = false)
    private String updateName;

    /**
     * 部门id
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(value = "部门id")
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
