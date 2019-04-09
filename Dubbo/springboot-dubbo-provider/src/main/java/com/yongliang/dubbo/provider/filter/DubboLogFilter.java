package com.yongliang.dubbo.provider.filter;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;

@Activate
public class DubboLogFilter implements Filter {
    

    protected final static Logger logger = LoggerFactory.getLogger(DubboLogFilter.class);
    
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		InvokeLog invokeLog = new InvokeLog();
	
		//设置请求时间
		invokeLog.setStartTime(new Date());
		//设置请求报文
		setRequest(invocation, invokeLog);
		//接口调用开始时间
		Result result = null;
		try {
			result = invoker.invoke(invocation);
			if(result.hasException()) {
				//如果有异常
				invokeLog.setFlag("failure");
				invokeLog.setResponseData(Throwables.getStackTraceAsString(result.getException()));
			} else {
				//如果成功
				if(result.getValue() != null){
					String returnStr = JSON.toJSONString(result.getValue());
					invokeLog.setResponseData(returnStr);
				}
				//TODO DUBBO接口如果无返回值暂未处理
			}
		} catch (RuntimeException e) {
			invokeLog.setFlag("failure");
			invokeLog.setResponseData(Throwables.getStackTraceAsString(result.getException()));
		} catch (Exception e) {
			invokeLog.setFlag("failure");
			invokeLog.setResponseData(Throwables.getStackTraceAsString(result.getException()));
		} finally {
			//接口调用时长
			invokeLog.setEndTime(new Date());
			invokeLog.setResponseTime(invokeLog.getEndTime().getTime()
					- invokeLog.getStartTime().getTime());
			//SpringHelper.getBean("invokeLogService", InvokeLogService.class).insert(invokeLog);
		}
		logger.info(JSON.toJSONString(invokeLog));
		return result;
	}
	
	/**
	 * 设置接口调用参数
	 * 
	*/
	protected void setRequest(Invocation invocation, InvokeLog invokeLog) {
		try {
			Object[] arguments = invocation.getArguments();
			if (arguments != null && arguments.length > 0) {
				
			    String requestStr = JSON.toJSONString(invocation.getArguments()); 
				invokeLog.setRequestData(requestStr);
			}
		} catch (Exception e) {
			// 不能影响dubbo的接口运行
			invokeLog.setRequestData(Throwables.getStackTraceAsString(e));
		}
	}
}
