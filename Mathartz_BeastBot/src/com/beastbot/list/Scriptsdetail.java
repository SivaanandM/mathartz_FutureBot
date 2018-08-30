package com.beastbot.list;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Scriptsdetail 
{
	String secid;  
	String symbol,exchange,instrument,lotsize, ticksize,expdd,expmmmdd,opttype,strike ;  
	public Scriptsdetail(String feedid, String symbol, String exchange, String instrument, String lotsize, String ticksize, String expdd, String expmmmdd, String opttype, String strike ) {  
	    this.secid = feedid;  
	    this.symbol = symbol;  
	    this.exchange = exchange;  
	    this.instrument = instrument;  
	    this.lotsize = lotsize;  
	    this.ticksize = ticksize;
	    this.expdd = expdd;
	    this.expmmmdd = expmmmdd;  
	    this.opttype = opttype;  
	    this.strike = strike;  
  
	}  
	
	public String getSecid()
	{
		return secid;
	}
	public String getSymbol()
	{
		return symbol;
	}
	public String getExchange()
	{
		return exchange;
	}
	public String getInstrument()
	{
		return instrument;
	}
	public String getLotsize()
	{
		return lotsize;
	}
	public String getTicksize()
	{
		return ticksize;
	}
	public String getExpdd()
	{
		return expdd;
	}
	public String getExpmmmdd()
	{
		return expmmmdd;
	}
	public String getOpttype()
	{
		return opttype;
	}
	public String getStrike()
	{
		return strike;
	}

}
