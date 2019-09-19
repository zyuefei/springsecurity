package com.portjs.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.portjs.base.dao.TUserMapper;
import com.portjs.base.model.Page;
import com.portjs.base.model.TUser;
import com.portjs.base.mpUtil.FBaseMapper;
import com.portjs.base.util.ResponseMessage;
import com.portjs.base.util.CodeEnum;
import com.portjs.base.vo.MpCondition;
import com.portjs.base.vo.PageVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 注意，如果使用fastjson 请升级到1.2.60版本级以上
 * 基础contorller，可根据自己需求自定义，
 * 该版本默认带上createId，createTIme，updateId，updateTime信息
 * @Author:zhangyuefei
 * @Date:2019/8/5 11:09
 */
@Controller
public abstract class BaseController<T>{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TUserMapper userMapper;


    /**
     * 获取当前用户信息
     * @return
     */
    public TUser getUserInfo(){
        //真是环境用户
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		TUser tUser = (TUser) authentication.getPrincipal();

        //默认admin用户
//        TUser tUser = userMapper.loginUserByAccount("test");

        return tUser;
    }

    public abstract FBaseMapper<T> getMapper();



    public ResponseMessage success(Object data){
        return new ResponseMessage(CodeEnum.SUCCESS,data);
    }
    public ResponseMessage success(Integer index){
        return new ResponseMessage(index);
    }
    public ResponseMessage success(){
        return new ResponseMessage(CodeEnum.SUCCESS,"");
    }
    public ResponseMessage failure(){
        return new ResponseMessage(CodeEnum.ERROR,"");
    }
    public ResponseMessage failure(String message){
        return new ResponseMessage(CodeEnum.ERROR.getCode(),message,"");
    }


    /**
     * 根据属性名获取属性值
     * @param fieldName
     * @param object
     * @return
     */
    private  String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Class c =  object.getClass();
            boolean flag = false;
            Field[] list = c.getDeclaredFields();
            for (Field field:list){
                String name = field.getName();
                if (name.equals(fieldName)){
                    flag = true;
                }
            }
            if (!flag){
                c = object.getClass().getSuperclass();
            }
            Field field = c.getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return  (String)field.get(object);
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据属性名设置属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    private  void setFieldValueByFieldName(String fieldName, Object object,String value) {
        try {
//            fieldName是否属于该类，不属于父类
            boolean flag = false;
            // 获取obj类的字节文件对象
            Class c = object.getClass();
            // 获取该类的成员变量
            Field[] list = c.getDeclaredFields();
            for (Field field:list){
                String name = field.getName();
                if (name.equals(fieldName)){
                    flag = true;
                }
            }
            if (!flag){
                c = object.getClass().getSuperclass();
            }
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(object, value);

        } catch (Exception e) {
//            e.printStackTrace();

        }
    }



    @ApiOperation("新增")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody T entity){
        boolean flag = insert(entity);

        if (flag){
            return new ResponseMessage(CodeEnum.SUCCESS,entity);
        }else {
            return new ResponseMessage(CodeEnum.ERROR,"");
        }
    }

    /**
     * 根据id获取单条信息
     * @param id
     * @return
     */
    @ApiImplicitParam(name = "id", value = "主键id", required = true)
    @ApiOperation("详情")
    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseMessage get(@PathVariable String id){
        T bean = getById(id);
        return new ResponseMessage(CodeEnum.SUCCESS,bean);
    }

    /**
     * 根据id 删除信息
     * @param ids
     * @return
     */
    @ApiOperation("删除")
    @ApiImplicitParam(name = "ids", value = "主键ids", required = true)
    @PostMapping("/delete")
    @ResponseBody
    public ResponseMessage delete(@RequestBody JSONArray ids){
       List<String> idList =  JSON.parseArray(ids.toJSONString(),String.class);
        boolean flag = SqlHelper.retBool(getMapper().deleteBatchIds(idList));
        if (flag){
            return new ResponseMessage(CodeEnum.SUCCESS,"");
        }else {
            return new ResponseMessage(CodeEnum.ERROR,"");
        }
    }

    /**
     * 有则更新，无则保存
     * @param entity
     * @return
     */
    @ApiOperation("新增或修改")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public ResponseMessage saveOrUpdate(@RequestBody T entity){
//        设置更新人id
        TUser tUser = getUserInfo();
        setFieldValueByFieldName("updateId",entity,tUser.getId());

        boolean flag=false;
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
            flag = com.baomidou.mybatisplus.core.toolkit.StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal)) ? insert(entity) : updateById(entity);
        }
        if (flag){
            return new ResponseMessage(CodeEnum.SUCCESS,"");
        }else {
            return new ResponseMessage(CodeEnum.ERROR,"");
        }
    }


    /**
     *
     * @param json
     * {
     * 	"page":{
     * 		"pageNum":1,
     * 		"pageSize":2
     *        },
     * 	"condition":[
     *        {
     * 			"attribute":"id",
     * 			"operate":"eq",
     * 			"value":"1"
     *        }
     * 	]
     * }
     * eq =
     * gt >
     * ge >=
     * lt <
     * le <=
     *
     * @return
     */
    @ApiOperation("分页查询")
    @PostMapping("/query")
    @ResponseBody
    public ResponseMessage query(@RequestBody PageVo pageVo){
        Page<T> page = pageVo.getPage();
        List<MpCondition> conditions =pageVo.getCondition();

        List<OrderItem> array = page.getOrders();
        if (array!=null || array.size()==0){
            OrderItem orderItem = new OrderItem();
            orderItem.setAsc(true);
            orderItem.setColumn("create_time");
            List<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem);
            page.setOrders(orderItems);
        }

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (conditions != null) {
            for (int i = 0; i < conditions.size(); i++) {
                MpCondition condition = conditions.get(i);
                String operate = condition.getOperate();
                String attribute = condition.getAttribute();
                attribute = humpToUnderline(attribute);
                String dateFormate = condition.getDateformat();
                Object value = condition.getValue();
                if (StringUtils.isEmpty(dateFormate)) {
                    dateFormate = "%Y-%m-%d";
                }

                if (StringUtils.isEmpty(value)){
                    continue;
                }
                switch (operate) {
                    case "eq":
                        queryWrapper.eq(attribute, value);
                        break;
                    case "gt":
                        queryWrapper.gt(attribute, value);
                        break;
                    case "ge":
                        queryWrapper.ge(attribute, value);
                        break;
                    case "lt":
                        queryWrapper.lt(attribute, value);
                        break;
                    case "le":
                        queryWrapper.le(attribute, value);
                        break;
                    case "like":
                        queryWrapper.like(attribute, "%" + value + "%");
                        break;
                    case "date_eq":
                        queryWrapper.apply("date_format(" + attribute + ",'" + dateFormate + "')={0}", value);
                        break;
                    case "date_gt":
                        queryWrapper.apply("date_format(" + attribute + ",'" + dateFormate + "')>{0}", value);
                        break;
                    case "date_ge":
                        queryWrapper.apply("date_format(" + attribute + ",'" + dateFormate + "')>={0}", value);
                        break;
                    case "date_lt":
                        queryWrapper.apply("date_format(" + attribute + ",'" + dateFormate + "')<{0}", value);
                        break;
                    case "date_le":
                        queryWrapper.apply("date_format(" + attribute + ",'" + dateFormate + "')<={0}", value);
                        break;
                    case "dept_container":
                        queryWrapper.apply("FIND_IN_SET(dept_id,getDepartmentChild({0})", value);
                        break;
                    default:
                        queryWrapper.eq(attribute, value);
                        break;
                }
            }
        }

        //加入公司id过滤
        TUser tUser = getUserInfo();
        queryWrapper.eq("harbor_id",tUser.getCompanyId());

        //加入select值
        String selectAttr = pageVo.getSelectAttr();
        if(!StringUtils.isEmpty(selectAttr)){
            String[] attrs = selectAttr.split(",");
            String sqlSelect = "";//sql中select语句中的select部分
            for (String attr:attrs){
                 sqlSelect += "a." + humpToUnderline(attr)+",";
            }
            sqlSelect = sqlSelect.substring(0,sqlSelect.lastIndexOf(","));
//            upadteName and createName
            sqlSelect = sqlSelect.replace("a.create_name","u.name AS createName");
            sqlSelect = sqlSelect.replace("a.update_name","u2.name AS updateName");
            sqlSelect = sqlSelect.replace("a.dept_name","d.name AS deptName");
            queryWrapper.select(sqlSelect);
            IPage<Map<String, Object>> page1 = pageMap(page,queryWrapper);
            return new ResponseMessage(CodeEnum.SUCCESS,page1);
        }else {
            Page<T> page1 = (Page<T>)page(page,queryWrapper);
            return new ResponseMessage(CodeEnum.SUCCESS,page);
        }

    }



    /**
     * 判断数据库操作是否成功
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    private boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    private boolean updateById(T entity) {
        TUser user = getUserInfo();
        setFieldValueByFieldName("updateId", entity, user.getId());
        return retBool(getMapper().updateById(entity));
    }
    private T getById(Serializable id) {
        return getMapper().selectById(id);
    }
    private boolean insert(T entity) {
        //判断是否具有createId 和deptId 属性并修改其值
        TUser user = getUserInfo();
        String deptId = getFieldValueByFieldName("deptId",entity);
        if (StringUtils.isEmpty(deptId)) {
            setFieldValueByFieldName("deptId", entity, user.getDeptId());
        }
        setFieldValueByFieldName("createId",entity,user.getId());
        return retBool(getMapper().insert(entity));
    }
    private IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
        return getMapper().fSelectPage(page, queryWrapper);
    }

    /**
     * 根据select字段返回值
     * @param page
     * @param queryWrapper
     * @return
     */
    private IPage<Map<String, Object>> pageMap(IPage<T> page, Wrapper<T> queryWrapper) {
        return getMapper().fSelectMapsPage(page, queryWrapper);
    }


    /**
     * 驼峰转下划线
     * @param para
     * @return
     */
    public  String humpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        if (!para.contains("_")) {
            for(int i=0;i<para.length();i++){
                if(Character.isUpperCase(para.charAt(i))){
                    sb.insert(i+temp, "_");
                    temp+=1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

}
