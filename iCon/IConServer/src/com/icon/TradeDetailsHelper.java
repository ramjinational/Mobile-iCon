package com.icon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeDetailsHelper {
	
	private Map<String, Trade> tradeInfo;
	public TradeDetailsHelper(){
		tradeInfo = new HashMap<String, Trade>();
		tradeInfo.put("OR123", new Trade("OR123", "Affirmed", "Settled", "100", "1000", "HKD"));
		tradeInfo.put("OR456", new Trade("OR456", "UnAffirmed", "UnSettled", "200", "2000", "JPY"));
	}

	public List<TradeStatus> getAllTrades() {
		List<TradeStatus> tradeStatuses = new ArrayList<TradeStatus>();
		for(Trade trade : tradeInfo.values()){
			TradeStatus tradeStatus = new TradeStatus(trade.getOrderRef(), trade.getConfStatus(), trade.getSettStatus());
			tradeStatuses.add(tradeStatus);
		}
		return tradeStatuses;
	}

}
