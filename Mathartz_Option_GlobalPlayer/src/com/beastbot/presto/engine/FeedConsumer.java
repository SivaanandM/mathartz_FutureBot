package com.beastbot.presto.engine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.beastbot.common.CommonObjects;
import com.beastbot.formula.F2Amaze_call_side;
import com.beastbot.formula.F3Amaze_call_side;
import com.beastbot.formula.F4_Amaze_put_side;
import com.beastbot.formula.F5_Amaze_put_side;
import com.beastbot.formula.FormulaAmaze;
import com.sft.feedprovider.MarketDataProvider;





public class FeedConsumer extends Thread{

	private FeedBlockingQueue feedBlockingQueue = null;
	FormulaAmaze objFamaze;
	F2Amaze_call_side  objf2;
	F3Amaze_call_side  objf3;
	F4_Amaze_put_side  objf4;
	F5_Amaze_put_side  objf5;
	Calendar c = Calendar.getInstance();
	Date date = new Date("12/31/1979 23:59:59");
	SimpleDateFormat monthyearDayCon = new SimpleDateFormat(
			"yyyyMMdd hh:mm:ss a");
	
	public FeedConsumer() {
		// TODO Auto-generated constructor stub
		TimeZone istTime = TimeZone.getTimeZone("IST");
		objFamaze = new FormulaAmaze();
		objf2 = new F2Amaze_call_side();
		objf3 = new F3Amaze_call_side();
		objf4 = new F4_Amaze_put_side();
		objf5 = new F5_Amaze_put_side();
		monthyearDayCon.setTimeZone(istTime);
		c.setTime(date);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		feedBlockingQueue=FeedBlockingQueue.getInstance();
		while(true)
		{
			Map<String, MarketDataProvider> symbolMap = feedBlockingQueue.take();
			Collection<MarketDataProvider> s = symbolMap.values();
			
			for (MarketDataProvider mdp : s) {	
				for (int i=0 ; i<CommonObjects.Globaluniqueheadid.length;i++)
				{
					if(symbolMap.containsKey(CommonObjects.Globaluniqueheadid[i][0]))
					{
						String[] fids;
						for (int k=0 ; k < CommonObjects.Globaltradlinemap.length; k++)
						{
							if ( CommonObjects.Globaltradlinemap[k][0].equals(CommonObjects.Globaluniqueheadid[i][0]))
							{
								fids = CommonObjects.Globaltradlinemap[k][1].split(":");
								//System.out.println("LTP - "+ mdp.getLastTradePrice(CommonObjects.Globaluniqueheadid[i][0])/100 +
								//		"LTT -");
								double lasttradeprice = (mdp.getLastTradePrice(CommonObjects.Globaluniqueheadid[i][0])/100);
								Date Lasttradetime = new Date((mdp.getLastTradeTime(CommonObjects.Globaluniqueheadid[i][0]) * 1000) + c.getTimeInMillis());
								objFamaze.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								objf2.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								objf3.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								objf4.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								objf5.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								break;
							}
						}
					}
				}
			}
		}
	}
}
