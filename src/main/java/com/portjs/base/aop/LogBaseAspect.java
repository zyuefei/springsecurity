package com.portjs.base.aop;

import com.alibaba.fastjson.JSONObject;
import com.portjs.base.dao.TTraceLogMapper;
import com.portjs.base.model.BaseEntity;
import com.portjs.base.model.TTraceLog;
import com.portjs.base.model.TUser;
import com.portjs.base.util.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * 日志切面
 */


@Component
@Aspect
public class LogBaseAspect {
    private static final String tag = "LogAspect======";
    private static final Logger logger = LoggerFactory.getLogger(LogBaseAspect.class);

    @Autowired
    TTraceLogMapper tTraceLogMapper;

    // 切面
    @Pointcut("@annotation(com.portjs.base.aop.LogInfo)") // 两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        TTraceLog log = new TTraceLog();
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            MethodSignature signature = (MethodSignature) pjp.getSignature();
//设置基本信息
            log.setUrl(request.getRequestURL().toString());
            log.setRemoteIp(getIpAddress(request));
            log.setClassMethod(signature.getDeclaringTypeName() + "." + signature.getName());
            log.setReqContent(Arrays.toString(pjp.getArgs()));
            log.setCreateTime(new Date());


            //获取param 请求内容的主要参数
            String targetName = pjp.getTarget().getClass().getName();
            Object[] arguments = pjp.getArgs();
            Class targetClass = Class.forName(targetName);
            Method method = signature.getMethod();
            LogInfo logInfo = method.getAnnotation(LogInfo.class);
            String key = logInfo.param();
            System.out.println("action" + key);
            String[] paramNames = getParamterNames(method);

            StringBuilder logParam = new StringBuilder();
            //如果注解中没有param的内容，则尝试从对象中获取
            if (StringUtils.isEmpty(key)) {
                for (int i = 0; i < arguments.length; i++) {
                    logParam.append(getParam(arguments[i]));
                }
            } else {
                ExpressionParser parser = new SpelExpressionParser();
                Expression expression = parser.parseExpression(key);
                EvaluationContext context = new StandardEvaluationContext();
                for (int i = 0; i < arguments.length; i++) {
                    context.setVariable(paramNames[i], arguments[i]);
                }
                logParam.append(expression.getValue(context, String.class));
            }
            //设置param值结束,如果有model字段则取model字段
            String modelName = (StringUtils.isEmpty(logInfo.model())) ? getClassApi(targetClass) : logInfo.model();
            String action = logInfo.action();
            String notes = logInfo.desc();
            log.setModel(modelName);
            log.setAction(action);
            log.setNotes(notes);
            log.setParam(logParam.toString());
            //获取注解中的字段内容结束
        } catch (Exception e) {
            e.printStackTrace();
        }


        //处理请求
        Object obj = pjp.proceed();


        try {
            //设置返回结果简介
            if (obj instanceof ResponseMessage) {
                ResponseMessage responseMessage = (ResponseMessage) obj;
                log.setResult(responseMessage.getMessage());
            } else {
                log.setResult(JSONObject.toJSONString(obj));
            }
            //请求完成返回结果
            log.setResContent(JSONObject.toJSONString(obj));
            log.setConsumeTime((int) (System.currentTimeMillis() - startTime));

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            TUser tUser = (TUser) authentication.getPrincipal();
            log.setCreateId(tUser.getId());

            log.setCreateTime(new Date());
            //保存数据库
            tTraceLogMapper.insert(log);

            System.out.println("日志信息");
            System.out.println(log);
        } catch (Exception e) {
            logger.error(tag + "日志切面出错", e);
            e.printStackTrace();
        }
        return obj;
    }


    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    private String[] getParamterNames(Method method) {
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        return u.getParameterNames(method);
    }


    /**
     * 获取类上controller上的swagger api 注解
     *
     * @param cl1
     * @return
     */
    private String getClassApi(Class cl1) {
//获取RequestMapping注解
        LogModel logModel = (LogModel) cl1.getAnnotation(LogModel.class);
        //如果有logmode注解则取logmode注解，没有则取得swagger的api注解
        if (logModel != null) {
            return logModel.value();
        } else {
            Api anno = (Api) cl1.getAnnotation(Api.class);
//获取类注解的value值
            String[] values = anno.tags();
            return Arrays.toString(values);
        }
    }


    private String getParam(Object param) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        //通过反射获取到类
        Class cl1 = param.getClass();
//获取类中所有的方法
        for (Field field : cl1.getDeclaredFields()) {
            LogShowParam logShowParam = field.getAnnotation(LogShowParam.class);
            if (logShowParam != null) {
                String paramValue = logShowParam.value();
                //获取对象值
                field.setAccessible(true);
                Object value = field.get(param);
                sb.append(paramValue)
                        .append("(")
                        .append(field.getName())
                        .append("):")
                        .append(value)
                        .append("\n");
            }
        }
        if (StringUtils.isEmpty(sb.toString())) {
            //如果没有logshowParam注解则取swgger ApiModelProperty 注解中的值,只取得前2-6，id不取
            for (int i = 1; i < cl1.getDeclaredFields().length && i < 6; i++) {
                Field field = cl1.getDeclaredFields()[i];
                ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                if (apiModelProperty != null) {
                    String paramValue = apiModelProperty.value();
                    //获取对象值
                    field.setAccessible(true);
                    Object value = field.get(param);
                    sb.append(paramValue)
                            .append("(")
                            .append(field.getName())
                            .append("):")
                            .append(value)
                            .append("\n");
                }
            }

        }
        return sb.toString();
    }

}
