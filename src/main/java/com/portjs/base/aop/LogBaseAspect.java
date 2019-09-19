package com.portjs.base.aop;

import com.alibaba.fastjson.JSONObject;
import com.portjs.base.dao.TTraceLogMapper;
import com.portjs.base.model.TTraceLog;
import com.portjs.base.model.TUser;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日志切面
 * */


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

		try {
			// 接收到请求，记录请求内容
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			long startTime = System.currentTimeMillis();
			TTraceLog log = new TTraceLog();
			Signature signature = pjp.getSignature();
			log.setRemoteIp(request.getRequestURL().toString());
			log.setRemoteIp(request.getRemoteAddr());
			log.setMethodName(signature.getDeclaringTypeName()+"."+signature.getName());
			log.setRequestMessage(Arrays.toString(pjp.getArgs()));

			Object obj = pjp.proceed();

			//请求完成返回结果
			log.setResponseMessage(JSONObject.toJSONString(obj));
			log.setConsumeTime((int) (System.currentTimeMillis() - startTime));
			try {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				TUser tUser = (TUser) authentication.getPrincipal();
			}catch (Exception e){
			}
			log.setCreatetime(new Date());
//			LogInfo logInfo = ((MethodSignature) signature).getMethod().getAnnotation(LogInfo.class);
//			log.setNotes(logInfo.desc());
			//保存数据库
			tTraceLogMapper.insert(log);

			System.out.println(JSONObject.toJSONString(log));
			return obj;
		} catch (Exception e) {
			logger.error(tag + "日志切面出错", e);
			throw e;
		}
	}

}
