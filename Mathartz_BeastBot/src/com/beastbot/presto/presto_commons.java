package com.beastbot.presto;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.marketcetera.types.trade.OrderStatus;
import org.marketcetera.types.trade.OrderType;
import org.marketcetera.types.trade.Side;
import org.marketcetera.types.trade.TimeInForce;

import com.beastbot.list.Scriptsdetail;
//import com.tradebot.presto.Date;
//import com.tradebot.presto.EsbConnection;
//import com.tradebot.presto.ReportHandler;
import com.symphonyfintech.gateway.EsbOrder;
import com.symphonyfintech.gateway.PositionBean;
import com.symphonyfintech.gateway.ReportHolder;
import com.symphonyfintech.gateway.SecurityType;
import com.symphonyfintech.gateway.SymbolDetail;
//import com.tradebot.dbcommons.db_commons;
//import com.tradebot.dbcommons.tradebot_utility;

public class presto_commons {
	
	static List<SymbolDetail> symbolDetailExchange,
	symbolDetailExchangeInstrument, SYMBOLDETAIL;
	static ReportHolder allReport;
	static List<ReportHolder> allList;
	static SymbolDetail SYMBOLDETAILS;
	static String USERNAME, ESBUSER, PASSWORD, ORDERTYPE, ESBSECURITYID,
		SECURITYTYPE, ESBEXCHANGE, INSTRUMENTTYPE, ESBOPTIONTYPE,
		ESBSYMBOL, REMARK, ESBACCOUNT, SIDE, INPUT, ORIGINALORDERID,
		STRATEGYINSTANCEID, EXTERNALINSTANCEID, EXPDATE, clientOrderID,
		QUANTITY, PRICE, STOPPRICE, ESBSTRIKEPPRICE, INSTANCEIDCUSTOMFIELD,
		SEGMENT;
	static List<byte[]> allReportFormated, openOrderFormated,
	tradeHistoryFormated;
	static List<PositionBean> position = null;
	static boolean connectionStatus;
	static OrderStatus ESBORDERSTATUS;
	static byte[] clientLastTradeHistory, clientLatestReport, REPORT;
	static OrderType ESBORDERTYPE;
	static SecurityType ESBSECURITYTYPE;
	static Side ESBSIDE;
	static TimeInForce ESBTIMEINFORCE;
	static String TIMEINFORCE = "DAY";
	static BigDecimal ESBPRICE, ESBSTOPPRICE;
	static Integer ESBQUANTITY;
	
	static Date ESBEXPDATE;
	static GregorianCalendar gcal;
	static XMLGregorianCalendar xgcal = null;
	static Scanner IN = new Scanner(System.in);
	static EsbConnection esbConnect;
	static Iterator<byte[]> iter;
	ReportHandler reportHandler;
	
	static String FutOptExchange = "NSEFO", EquExchange = "NSECM", ComExchange = "NSEDC", McxExchange = "MCX";	
	static String FutSegment = "FUT", optSegment = "OPT", EquSegment = "CM";
	static String FutIdxInsttype = "FUTIDX", FutStkInsttype = "FUTSTK", OptIdxInsttype = "OPTIDX", 
			OptStkInsttype = "OPTSTK", EquInsttype = "Equities", FutComInsttype = "FUTCOM";
	
	
	 
	
	private String configprop=System.getProperty("user.dir")+File.separator+"resource"+File.separator+"config.properties";
	String tradelogpath;
	
	public presto_commons()
	{
		try
		{
			USERNAME="shanmuga";
			PASSWORD = "sm@12345";
			reportHandler = new ReportHandler();
			esbConnect = new EsbConnection();
			esbConnect.initialize(reportHandler);
			logintopresto();
		}
		catch(Exception ex)
		{
			
		}
	}
	public boolean logintopresto()
	{
		boolean connected =false;
		try
		{
			esbConnect.setDealerName(USERNAME);
			esbConnect.setDealerPassword(PASSWORD);
			esbConnect.forceLogoutFromORS(USERNAME, PASSWORD);
			esbConnect.loginToOrs(USERNAME, PASSWORD);
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		}
		connected = esbConnect.getConnectionStatus(USERNAME);
		return connected;
	}
	
	public boolean checkandLoginFinvasia()
	{
		Boolean connectionStatus = false;
		try
		{
			connectionStatus = esbConnect.getConnectionStatus(USERNAME);
			if (connectionStatus == false)
			{
				System.out.print("Is Connected to Broker : "+ logintopresto()); 
		    }
			connectionStatus = esbConnect.getConnectionStatus(USERNAME);
			return connectionStatus;
			
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		}
		return connectionStatus;
	}
	
	public void forcelogout()
	{
		esbConnect.forceLogoutFromORS(USERNAME, PASSWORD);
	}

	
	
	public List<SymbolDetail> get_CM_FUT_Details(String exchange,String symbol,String segment, String instype )
	{
	 try
	 {
		 checkandLoginFinvasia();
		 ESBEXCHANGE = exchange;
		 ESBSYMBOL = symbol;
		 SEGMENT = segment;
		 INSTRUMENTTYPE = instype;
		 SYMBOLDETAIL = esbConnect.getSymbolDetailsForInstrType(ESBEXCHANGE, ESBSYMBOL, INSTRUMENTTYPE, SEGMENT);
	 }
	 catch(Exception ex)
	 {
		 System.out.println(exchange.toString());
	 }
	 finally
	 {
		 	
	 }
	 return SYMBOLDETAIL;
	}
	public List<SymbolDetail> get_OPT_Details(String exchange,String symbol,String expdate, String optType )
	{
		try
		{
			 checkandLoginFinvasia();
			 ESBEXCHANGE = exchange;
			 ESBSYMBOL = symbol;
			 EXPDATE = expdate;
			 ESBOPTIONTYPE = optType;
			 SYMBOLDETAIL = esbConnect.getSymbolDetailsDerivativeOPT(ESBEXCHANGE, ESBSYMBOL, EXPDATE, ESBOPTIONTYPE);
		}
		catch(Exception ex)
		{
			
		}
		return SYMBOLDETAIL;
	}
	
	
	public List<Scriptsdetail> getMatchedScripts(String strSymbol, String strexpMMMDD)
	{
		List<Scriptsdetail> sciptsdetailobj=new ArrayList<Scriptsdetail>(); 
		try
		{
			//Getting Detial for NSECM, CM, Equites
			checkandLoginFinvasia();
			SYMBOLDETAIL = get_CM_FUT_Details("NSEFO", strSymbol, "FO", "FUTIDX");
			Scriptsdetail sc;
			if (SYMBOLDETAIL != null)
			{
				for (SymbolDetail sd : SYMBOLDETAIL) 
				{
					sc = new Scriptsdetail(sd.getSecID(), sd.getSymbol(), sd.getExchange(), sd.getInstrumenttype(),sd.getLotsize(), sd.getTicksize(), sd.getExpiryDay(), sd.getExpiryMonth(), sd.getOpType(), sd.getStrikePrice());
					sciptsdetailobj.add(sc);
				}
			}
			SYMBOLDETAIL = get_OPT_Details("NSEFO", strSymbol, strexpMMMDD, "CE");
			if (SYMBOLDETAIL != null)
			{
				for (SymbolDetail sd : SYMBOLDETAIL) 
				{
					sc = new Scriptsdetail(sd.getSecID(), sd.getSymbol(), sd.getExchange(), sd.getInstrumenttype(),sd.getLotsize(), sd.getTicksize(), sd.getExpiryDay(), sd.getExpiryMonth(), sd.getOpType(), sd.getStrikePrice());
					sciptsdetailobj.add(sc);
				}
			}
			SYMBOLDETAIL = get_OPT_Details("NSEFO", strSymbol, strexpMMMDD, "PE");
			if (SYMBOLDETAIL != null)
			{
				for (SymbolDetail sd : SYMBOLDETAIL) 
				{
					sc = new Scriptsdetail(sd.getSecID(), sd.getSymbol(), sd.getExchange(), sd.getInstrumenttype(),sd.getLotsize(), sd.getTicksize(), sd.getExpiryDay(), sd.getExpiryMonth(), sd.getOpType(), sd.getStrikePrice());
					sciptsdetailobj.add(sc);
				}
			}
			
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		}
		return sciptsdetailobj;
	}

}
  
