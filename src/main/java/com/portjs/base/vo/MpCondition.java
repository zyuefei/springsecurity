package com.portjs.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/9 12:23
 */
@ApiModel(value="筛选条件")
@Data
public class MpCondition {

    @ApiModelProperty(value="属性")
    private String attribute;

    @ApiModelProperty(value="比较操作")
    private String operate;

    @ApiModelProperty(value="值")
    private String value;

    @ApiModelProperty(value="时间格式化字符串")
    private String dateformat;
}
