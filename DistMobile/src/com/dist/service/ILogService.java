package com.dist.service;

import org.aspectj.lang.JoinPoint;
/**
 * 这个是日志记录方法的接口
 * @author wmy
 *
 */

public interface ILogService {
    //无参的日志方法
    public void log();
    //有参的日志方法
    public void logArg(JoinPoint point);
    //有参有返回值的方法
    public void logArgAndReturn(JoinPoint point,Object returnObj);
}
