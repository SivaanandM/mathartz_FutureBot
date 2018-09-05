package com.future.engine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.future.commons.CommonObjects;
import com.future.algos.FormulaAmazeCenterF2;
import com.future.algos.FormulaAmazeF1;

import com.future.algos.FormulaAmazeF3;
import com.future.algos.FormulaAmazeF4;
import com.future.algos.FormulaAmazeF6;
import com.future.algos.FormulaAmazeUTurnF5;
import com.future.collection.FormulaData;
import com.sft.feedprovider.MarketDataProvider;





public class FeedConsumer extends Thread{

	private FeedBlockingQueue feedBlockingQueue = null;
	FormulaAmazeF1 objamazeF1;
	FormulaAmazeCenterF2 objamazeF2;
	FormulaAmazeF3 objamazeF3;
	FormulaAmazeF4 objamazeF4;
	FormulaAmazeUTurnF5 objamazeF5;
	FormulaAmazeF6 objamazeF6;
	Calendar c = Calendar.getInstance();
	Date date = new Date("12/31/1979 23:59:59");
	SimpleDateFormat monthyearDayCon = new SimpleDateFormat(
			"yyyyMMdd hh:mm:ss a");
	
	public FeedConsumer() {
		// TODO Auto-generated constructor stub
		TimeZone istTime = TimeZone.getTimeZone("IST");
		objamazeF1 = new FormulaAmazeF1();
		objamazeF2 = new FormulaAmazeCenterF2();
		objamazeF3 = new FormulaAmazeF3();
		objamazeF4 = new FormulaAmazeF4();
		objamazeF5 = new FormulaAmazeUTurnF5();
		objamazeF6 = new FormulaAmazeF6();
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
								double lasttradeprice = (mdp.getLastTradePrice(CommonObjects.Globaluniqueheadid[i][0])/100);
								Date Lasttradetime = new Date((mdp.getLastTradeTime(CommonObjects.Globaluniqueheadid[i][0]) * 1000) + c.getTimeInMillis());
								//System.out.println(" LTP - "+ lasttradeprice +
								//		" LTT - "+ Lasttradetime);
								objamazeF1.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								objamazeF2.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								objamazeF3.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								objamazeF4.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								objamazeF5.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								objamazeF6.FormulaAmazeDriver(fids,lasttradeprice , Lasttradetime);
								break;
							}
						}
					}
				}
			}
		}
	}
}
