package com.portjs.base.vo;

import com.portjs.base.model.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * 分页查询的vo
 * @Author:zhangyuefei
 * @Date:2019/9/9 11:55
 */
@ApiModel(value="通用分页查询条件部分")
@Data
public class PageVo {

    @ApiModelProperty(value="分页信息")
    private Page page;

    @ApiModelProperty(value="筛选条件")
    private List<MpCondition> condition;

    @ApiModelProperty(value="筛选条件,attr:value这种形式的请求条件")
    private HashMap<String,Object> conditon;

    @ApiModelProperty(value="返回值列表，以,分割")
    private String selectAttr;

}
