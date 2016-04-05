package com.icon;

public class TradeStatus {
	private String orderRef;
	private String confStatus;
	private String settStatus;
	
	public TradeStatus(String orderRef, String confStatus, String settStatus) {
		super();
		this.orderRef = orderRef;
		this.confStatus = confStatus;
		this.settStatus = settStatus;
	}
	public String getOrderRef() {
		return orderRef;
	}
	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}
	public String getConfStatus() {
		return confStatus;
	}
	public void setConfStatus(String confStatus) {
		this.confStatus = confStatus;
	}
	public String getSettStatus() {
		return settStatus;
	}
	public void setSettStatus(String settStatus) {
		this.settStatus = settStatus;
	}
	
	
}
