package com.portjs.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@ApiModel(value = "com.portjs.base.model.TUser")
@Data
@TableName(value = "t_user")
public class TUser implements UserDetails {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 登录账号
     */
    @TableField(value = "account")
    @ApiModelProperty(value = "登录账号")
    private String account;

    /**
     * 登录密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "登录密码")
    private String password;

    /**
     * 用户名
     */
    @TableField(value = "nickname")
    @ApiModelProperty(value = "用户名")
    private String nickname;

    /**
     * 用户真实姓名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "用户真实姓名")
    private String name;

    /**
     * 用户名拼音，用于快速搜索
     */
    @TableField(value = "pingyin")
    @ApiModelProperty(value = "用户名拼音，用于快速搜索")
    private String pingyin;

    /**
     * 性别
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 身份证号
     */
    @TableField(value = "idcard")
    @ApiModelProperty(value = "身份证号")
    private String idcard;

    /**
     * 出生日期
     */
    @TableField(value = "birth_date")
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    /**
     * 职务
     */
    @TableField(value = "duty")
    @ApiModelProperty(value = "职务")
    private String duty;

    /**
     * 职级
     */
    @TableField(value = "duty_lev")
    @ApiModelProperty(value = "职级")
    private String dutyLev;

    /**
     * 手机
     */
    @TableField(value = "phonenum")
    @ApiModelProperty(value = "手机")
    private String phonenum;

    /**
     * 固定电话
     */
    @TableField(value = "telephone")
    @ApiModelProperty(value = "固定电话")
    private String telephone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 家庭地址
     */
    @TableField(value = "address")
    @ApiModelProperty(value = "家庭地址")
    private String address;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableField(value = "wrong_count")
    @ApiModelProperty(value = "登录失败次数")
    private Integer wrongCount;
    /**
     * 账号是否冻结状态
     */
    @TableField(value = "enable")
    @ApiModelProperty(value = "账号是否冻结状态")
    private Integer enable;

    /**
     * 所属公司
     */
    @TableField(value = "company_id")
    @ApiModelProperty(value = "所属公司")
    private String companyId;


    /**
     * 最近一次的登录时间
     */
    @TableField(value = "last_login_time")
    @ApiModelProperty(value = "最近一次的登录时间")
    private Date lastLoginTime;

    /**
     * 上次修改密码的时间，后续进行密码过期提醒
     */
    @TableField(value = "last_upd_passwd_time")
    @ApiModelProperty(value = "上次修改密码的时间，后续进行密码过期提醒")
    private Date lastUpdPasswdTime;

    /**
     * 历史密码，多条记录使用逗号分隔。
     */
    @TableField(value = "history_password")
    @ApiModelProperty(value = "历史密码，多条记录使用逗号分隔。")
    private String historyPassword;

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


    @TableField(exist = false)
    private List<TRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles == null){
            return authorities;
        }
        for (TRole role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (enable == 1){
            return true;
        }
        return false;
    }

}