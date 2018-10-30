package com.future.engine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.future.commons.CommonObjects;
import com.future.algos.FormulaAmazeZCallF1;
import com.future.algos.FormulaAmazeUCallF2;

import com.future.algos.FormulaAmazeZPutF3;
import com.future.algos.FormulaAmazeUPutF4;
import com.future.algos.FormulaAMCallF5;
import com.future.algos.FormulaAMPutF6;
import com.future.collection.FormulaData;
import com.sft.feedprovider.MarketDataProvider;





public class FeedConsumer extends Thread{

	private FeedBlockingQueue feedBlockingQueue = null;
	FormulaAmazeZCallF1 objamazeF1;
	FormulaAmazeUCallF2 objamazeF2;
	FormulaAmazeZPutF3 objamazeF3;
	FormulaAmazeUPutF4 objamazeF4;
	FormulaAMCallF5 objamazeF5;
	FormulaAMPutF6 objamazeF6;
	Calendar c = Calendar.getInstance();
	Date date = new Date("12/31/1979 23:59:59");
	SimpleDateFormat monthyearDayCon = new SimpleDateFormat(
			"yyyyMMdd hh:mm:ss a");
	
	public FeedConsumer() {
		// TODO Auto-generated constructor stub
		TimeZone istTime = TimeZone.getTimeZone("IST");
		objamazeF1 = new FormulaAmazeZCallF1();
		objamazeF2 = new FormulaAmazeUCallF2();
		objamazeF3 = new FormulaAmazeZPutF3();
		objamazeF4 = new FormulaAmazeUPutF4();
		objamazeF5 = new FormulaAMCallF5();
		objamazeF6 = new FormulaAMPutF6();
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
			
			for (MarketDataProvider mdp : s) 
			{	
				double lasttradeprice = (mdp.getLastTradePrice(CommonObjects.HeadID)/100);
				Date Lasttradetime = new Date((mdp.getLastTradeTime(CommonObjects.HeadID) * 1000) + c.getTimeInMillis());
				System.out.println(" LTP - "+ lasttradeprice +		" LTT - "+ Lasttradetime);
				objamazeF1.FormulaAmazeDriver(CommonObjects.Globaltradlinemap, lasttradeprice, Lasttradetime);
				objamazeF2.FormulaAmazeDriver(CommonObjects.Globaltradlinemap, lasttradeprice, Lasttradetime);
				objamazeF3.FormulaAmazeDriver(CommonObjects.Globaltradlinemap, lasttradeprice, Lasttradetime);
				objamazeF4.FormulaAmazeDriver(CommonObjects.Globaltradlinemap, lasttradeprice, Lasttradetime);
				objamazeF5.FormulaAmazeDriver(CommonObjects.Globaltradlinemap, lasttradeprice, Lasttradetime);
				objamazeF6.FormulaAmazeDriver(CommonObjects.Globaltradlinemap, lasttradeprice, Lasttradetime);
				break;
			}
		}
	}
}
