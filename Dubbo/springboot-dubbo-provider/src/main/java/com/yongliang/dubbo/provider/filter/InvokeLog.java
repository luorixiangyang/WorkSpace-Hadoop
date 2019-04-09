package com.yongliang.dubbo.provider.filter;

import java.util.Date;

public class InvokeLog {
    /** 系统编码 **/
    private String sourceSystemCode;
    
    private String targetSystemCode;
    /** 接口编码**/
    private String serviceCode;
    
    /** 接口名称**/
    private String serviceName;
    
    /** 消息跟踪id **/
    private String traceId;
    /**请求数据*/
    private String requestData;
    /**响应数据*/
    private String responseData;
    /**开始调用时间*/
    private Date startTime;
    /**结束调用时间*/
    private Date endTime;
    /**响应时间*/
    private Long responseTime;
    /**接口调用标记 success 成功 failure失败   */
    private String flag;
    
	public String getSourceSystemCode() {
		return sourceSystemCode;
	}
	public void setSourceSystemCode(String sourceSystemCode) {
		this.sourceSystemCode = sourceSystemCode;
	}
	public String getTargetSystemCode() {
		return targetSystemCode;
	}
	public void setTargetSystemCode(String targetSystemCode) {
		this.targetSystemCode = targetSystemCode;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public String getRequestData() {
		return requestData;
	}
	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
