package com.icon;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/getAllTrades")
public class TradeInfoService {
	
	private TradeDetailsHelper tradeDetailsHelper = new TradeDetailsHelper();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TradeStatus getTrades(){
		return new TradeStatus("123","Affirmed","Settled");
	}
}