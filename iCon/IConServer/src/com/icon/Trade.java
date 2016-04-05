package com.icon;

public class Trade {
	private String orderRef;
	private String confStatus;
	private String settStatus;
	private String qty;
	private String amount;
	private String currency;
	
	
	public Trade(String orderRef, String confStatus, String settStatus,
			String qty, String amount, String currency) {
		super();
		this.orderRef = orderRef;
		this.confStatus = confStatus;
		this.settStatus = settStatus;
		this.qty = qty;
		this.amount = amount;
		this.currency = currency;
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
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
