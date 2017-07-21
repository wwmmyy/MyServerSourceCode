package com.dist.service.impl;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dist.entity.SLog;
import com.dist.service.ILogService;
import com.dist.service.SDeviceServiceI;
import com.dist.service.SLogServiceI;
/**
 * Spring  用于日志记录AOP   用户日志操作记录    此方法在系统启动时由Spring AOP在后台自动监控调用
 * @author 王明远
 *
 */
@Service("logService")
public class LogServiceImpl implements ILogService {
	

	
	public HibernateTemplate hibernateTemplate;
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void log() {
    	 
    	  String reTime = format2.format(new Date());
//        System.out.println(reTime+"*************日志记录开始启动*******************");
    }
    
    //有参无返回值的方法
    public void logArg(JoinPoint joinPoint) {
        //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
//        Object[] args = joinPoint.getArgs();
//        System.out.println("目标参数列表：");
//        if (args != null) {
//            for (Object obj : args) {
//                System.out.println(obj + ",");
//            }
//            System.out.println();
//        }
    }

    //有参并有返回值的方法
    public void logArgAndReturn(JoinPoint joinPoint, Object returnObj) {
    	
    	
  
        //获取方法名 
        String methodName = joinPoint.getSignature().getName();
//        System.out.println("获取方法名 是：" + methodName);methodName.indexOf("findBy")!=-1||
        if(methodName.indexOf("login")!=-1||methodName.indexOf("delete")!=-1||methodName.indexOf("save")!=-1){
     	 //判断参数  
        if(joinPoint.getArgs() == null&&joinPoint.getArgs().length>0){//没有参数  
          return ;
        }else{
//        	   System.out.println("获取到的方法数组为 是：" );
        	    //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
               Object[] args = joinPoint.getArgs();	   
        	  //获取操作内容
            String opContent = optionContent(joinPoint.getArgs(),methodName);
//            System.out.println("操作内容 是：" + opContent);
//            System.out.println("执行结果是：" + JSON.toJSONString(returnObj));
            
         
            	  SLog  temp=new SLog();
                  temp.setEventData(opContent);
                  temp.setEventTime(new Date(System.currentTimeMillis()));
                 if(methodName.indexOf("login")!=-1){
                	 temp.setSLogtype("用户登录");
                 }else if(methodName.indexOf("delete")!=-1){
                	 temp.setSLogtype("删除数据");
                 }else if(methodName.indexOf("findBy")!=-1){
                	 temp.setSLogtype("查找信息");
                 }else if(methodName.indexOf("save")!=-1){
                	 temp.setSLogtype("数据保存");
                 } 
                  
                  temp.setSource(JSON.toJSONString(returnObj));
                  hibernateTemplate.getSessionFactory().getCurrentSession().save(temp);
            }
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 
       * 将参数值拼接为操作内容
     * @param args
     * @param mName
     * @return
     */
    public String optionContent(Object[] args, String mName){
      if(args == null){
        return null;
      }
      StringBuffer rs = new StringBuffer();
//      rs.append(mName);
      String className = null;
      int index = 1;
      //遍历参数对象 
      for(Object info : args){
        //获取对象类型
    	  if(info!=null&&info.getClass()!=null){
    	      className = info.getClass().getName();
    	        className = className.substring(className.lastIndexOf(".") + 1);
    	        rs.append("[参数"+index+"，类型:" + className + "，值:");
    	        //获取对象的所有方法
//    	        Method[] methods = info.getClass().getDeclaredMethods();
    	        // 遍历方法，判断get方法 
//    	        for(Method method : methods){
//    	          String methodName = method.getName();
//    	          // 判断是不是get方法
//    	          if(methodName.indexOf("get") == -1){//不是get方法 
//    	            continue;//不处理
//    	          }
//    	          Object rsValue = null;
//    	          try{
//    	            // 调用get方法，获取返回值
//    	            rsValue = method.invoke(info);
//    	          }catch (Exception e) {
//    	            continue;
//    	          }
//    	          //将值加入内容中
//    	          rs.append("(" + methodName+ ":" +JSON.toJSONString(rsValue)  + ")");
//    	        }
    	        rs.append(JSON.toJSONString(info)+ "]");
    	      
    		  
    	  }
    	  index ++;
      }
      return rs.toString();
    }
    
}