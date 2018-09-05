package com.future.algos;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.future.commons.CommonObjects;
import com.future.collection.AmazeCentervalues;
import com.future.collection.BeastViewList;
import com.future.collection.FormulaData;
import com.future.collection.SquadScripts;
import com.future.collection.Tradeinfo;
import com.future.collection.getListcommon;

public class FormulaAmazeUTurnF5 {
	//amaze execution related data
	int identity,c,lc,r;
	String headsecid,playersecid,way;
	double ltp, points,low,high,nextline,baseline,tpl,spoint;
	Date ltt;
	//Amaze formula inputs data
	double x,y;
	Date st,mt,et;
	int lcount, round, qty;
	Boolean tradeswitch, isend;
	public static SimpleDateFormat datefmt=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	getListcommon listcom = new getListcommon();
	Boolean Isboxed =false;
	AmazeCentervalues fvalue= null;
	FormulaData finput =null;
	BeastViewList bv = null;
	String fname = "F5";
	
	
	public void FormulaAmazeDriver(String [] ids, double LTP, Date LTT)
	{
		try
		{
			//System.out.println("LTP - "+ltp + " LTT - "+ltt);
			Isboxed =false;
			ltp= LTP;
			ltt= LTT;
			for (String id : ids) 
			{
				identity = Integer.valueOf(id);
				finput = listcom.getFormulaDataByID(identity,  CommonObjects.GlobalAmazeF5);
				if (finput != null)
				{
					if (!finput.getIsend())
					{
						AssignvVariables();
						Amaze();
						UpdateVariables();
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally 
		{
			
		}
	}
	public void UpdateVariables()
	{
		try
		{
			fvalue.SetAmazeCentervalues(c, lc, r, points, low, high, nextline, baseline, way,tpl,spoint);
			finput.setIsend(isend);
			bv = listcom.getBeastViewListByID(identity,CommonObjects.GlobalBeastViewList);
			bv.setF5Point(points);
			if ((way.equalsIgnoreCase("L3"))||(way.equalsIgnoreCase("L2"))||(way.equalsIgnoreCase("R3"))||(way.equalsIgnoreCase("R2")))
			{
				bv.setF5PL(tpl);
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		
	}
	public void AssignvVariables()
	{
		try
		{
			
			fvalue = listcom.getAmazeCenterValuesByID(identity, CommonObjects.GlobalAmazeUTurnValuesF5);
			c = fvalue.getc();
			lc = fvalue.getlc();
			r = fvalue.getr();
			headsecid = fvalue.getheadsecid();
			way= fvalue.getway();
			points = fvalue.getpoints();
			low = fvalue.getlow();
			high = fvalue.gethigh();
			nextline = fvalue.getnextline();
			baseline = fvalue.getbaseline();
			tpl = fvalue.gettpl();
			spoint  = fvalue.getspoint();
			
			x =finput.getX();
			y = finput.getY();
			st = finput.getSTDate();
			mt = finput.getMTDate();
			et = finput.getETDate();
			lcount = (int) finput.getLcount();
			round = (int) finput.getRound();
			qty = finput.getQty();
			tradeswitch = finput.getTradeswitch();
			isend = finput.getIsend();
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally 
		{
			
		}
	}
	
	public void Amaze()
	{
		try
		{
			//Start Formula First Condition
			if(ltt.after(st))
			{
				//L1 & R1 LOGIC FLOW IMPLEMENT IN L1R1Flow()
				if (way.equalsIgnoreCase("FT"))
				{
					//for the first iteration setting low and high and finding low and high till
					if ((low == 0 ) && (high == 0))
					{
						low = ltp;
						high = ltp;
					}
					else
					{
						if (ltp > high)
						{
							high = ltp;
						}
						if (ltp < low)
						{
							low =ltp;
						}
					}
					L0R0Flow();
				}
				if ((way.equalsIgnoreCase("L0"))||(way.equalsIgnoreCase("R0")))
				{
					L1Flow();
					R1Flow();
				}
				if (way.equalsIgnoreCase("L1") || way.equalsIgnoreCase("L5") || way.equalsIgnoreCase("L4"))
				{
					L2Flow();
					if (!way.equalsIgnoreCase("L2"))
					{
						L3Flow();
						L4Flow();
					}
				}
				if(way.equalsIgnoreCase("L3"))
				{
					L5Flow();
					//R5Flow();
				}
				if (way.equalsIgnoreCase("R1") || way.equalsIgnoreCase("R5") || way.equalsIgnoreCase("R4"))
				{
					//R2Flow();
					//if (!way.equalsIgnoreCase("R2"))
					//{
						//R3Flow();
						//R4Flow();
					//}
				}
				if(way.equalsIgnoreCase("R3"))
				{
					L5Flow();
					//R5Flow();
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally 
		{
			
		}
	}
	public void L0R0Flow()
	{
		try
		{
			if (ltp > (low + (low*(x/100))))
			{
				//spoint = (low + (low*((x/2)/100)));
				way = "L0";
				isend = true;
			}
			else if (ltp < (high - (high*(x/100))))
			{
				spoint = (high - (high*((x/2)/100)));
				way = "R0";
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void L1Flow()
	{
		try
		{
			/**
			 * L1 Box Implementation  
			 * 
			 */
			if (Isboxed == false)
			{
				if (ltp > (spoint + (spoint*(x/100))))
				{
					//If last trade time is greater then MT then ending in if loop
					if (ltt.after(mt))
					{
						// setting this variable to to end formula 
						isend = true;
					}
					else
					{
						//Entering Market By BUY command
						String strorderid =null;
						strorderid = placeorder(identity, qty,"BUY");
						Tradeinfo ti=new Tradeinfo(identity, fname, "BUY", "L1", ltt, strorderid, ltp);
						CommonObjects.GlobalTradeInfo.add(ti);
						way = "L1";
						nextline = spoint + (spoint*(x/100));
						baseline = spoint;
						tpl = ((ltp * (qty*75)) * -1);
					}
					Isboxed = true;
				}
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void L2Flow()
	{
		try
		{
		if (Isboxed == false)
		{
		  if(ltt.after(et))
		  {
			  points = points + 0.5;
			  String strorderid =null;
			  if (c>=1)
			  {
				  // SELLING PARTIAL CODE
				  strorderid = placeorder(identity, (int) (qty/2),"SELL");
				  tpl = (tpl + (ltp * (int) ((qty*75)/2)));
			  }
			  else
			  {
				  strorderid = placeorder(identity, qty,"SELL");
				  tpl = (tpl + (ltp * (qty*75)));
			  }
			  Tradeinfo ti=new Tradeinfo(identity, fname, "SELL", "L2", ltt, strorderid, ltp);
			  CommonObjects.GlobalTradeInfo.add(ti);
			  way = "L2";
			  isend = true;
			  Isboxed = true;
		  }
		}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		
	}
	public void L3Flow()
	{
		try
		{
			if (Isboxed == false)
			{
				if (ltp < baseline)
				{
					if (c==0)
					{
						String strorderid =null;
						strorderid = placeorder(identity, qty,"SELL");
						points = points - 2;
						Tradeinfo ti=new Tradeinfo(identity, fname, "SELL", "L3", ltt, strorderid, ltp);
						CommonObjects.GlobalTradeInfo.add(ti);
						way = "L3";
						tpl = (tpl + (ltp*(qty*75)));
					}
					else
					{
						String strorderid =null;
						strorderid = placeorder(identity, (int)(qty/2),"SELL");
						points = points - 1;
						Tradeinfo ti=new Tradeinfo(identity, fname, "SELL", "L3", ltt, strorderid, ltp);
						CommonObjects.GlobalTradeInfo.add(ti);
						way = "L3";
						tpl = (tpl + (ltp*(int)((qty*75)/2)));
					}
					c = c-1;
					r = r+1;
					if (c == -1)
					{
						lc = lc+1;
					}
					if (lc == lcount)
					{
						isend = true;
					}
					if (r == round)
					{
						isend = true;
					}
					isend = true;
					c = 0;
					Isboxed = true;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void L4Flow()
	{
		try
		{
			if (Isboxed == false)
			{
				if (ltp >= (nextline + (nextline*(y/100))))
				{
					//baseline = nextline;
					nextline = (nextline + (nextline*(y/100)));
					points=points+1;
					c=c+1;
					if (c==2)
					{
						points = points + 0.2;
					}
					if(c == 1)
					{
						String strorderid =null;
						strorderid = placeorder(identity, (int) (qty/2),"SELL");
						points = points + 1.1;
						Tradeinfo ti=new Tradeinfo(identity, fname, "SELL", "L4", ltt, strorderid, ltp);
						CommonObjects.GlobalTradeInfo.add(ti);
						way ="L4";
						tpl = (tpl + (ltp * (int) ((qty*75)/2)));
					}
					Isboxed = true;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally
		{
			
		}
	}
	public void L5Flow()
	{
		try
		{
			if (Isboxed == false)
			{
				if(ltp > (baseline + (baseline*(x/100))))
				{
					if (ltt.after(mt))
					{
						isend = true;
					}
					else
					{
						String strorderid =null;
						strorderid = placeorder(identity, qty,"BUY");
						Tradeinfo ti=new Tradeinfo(identity, fname, "BUY", "L5", ltt, strorderid, ltp);
						CommonObjects.GlobalTradeInfo.add(ti);
						way="L5";
						nextline = (baseline + (baseline*(x/100)));
						tpl = (tpl + ((ltp*(qty*75)) * -1));
					}
					Isboxed = true;
				}
			
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally
		{
			
		}
	}
	public void R1Flow()
	{
		try
		{
			/**
			 * R1 Box Implementation  
			 * 
			 */
			if (Isboxed == false)
			{
				if(ltp < (spoint - (spoint*(x/100))))
				{
					//If last trade time is greater then MT then ending in if loop
					if (ltt.after(mt))
					{
						// setting this variable to to end formula 
						isend = true;
					}
					else
					{
						//Entering Market By SELL command
						//String strorderid =null;
						//strorderid = placeorder(identity, qty,"SELL");
						//sTradeinfo ti=new Tradeinfo(identity, fname, "SELL", "R1", ltt, strorderid, ltp);
						//CommonObjects.GlobalTradeInfo.add(ti);
						//way = "R1";
						spoint = (spoint - (spoint * (x/100)));
						//nextline = spoint - (spoint*(x/100));
						//baseline = spoint;
						//tpl = (ltp * qty);
					}
					Isboxed = true;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void R2Flow()
	{
		try
		{
			if (Isboxed == false)
			{
				if(ltt.after(et))
				{
					//points = points + 0.5;	
					//String strorderid= null;
					//if (c >=1 )
					//{
					//	strorderid = placeorder(identity, (int) (qty/2),"BUY");
					//	tpl = (tpl + ((ltp * (int) (qty/2)) * -1));
								
					//}
					//else
					//{
					//	strorderid = placeorder(identity, qty,"BUY");
					//	tpl = (tpl + ((ltp * qty) * -1));
					//}
					//Tradeinfo ti=new Tradeinfo(identity, fname, "BUY", "R2", ltt, strorderid, ltp);
					//CommonObjects.GlobalTradeInfo.add(ti);
					way = "R2";
					isend = true;
					Isboxed = true;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void R3Flow()
	{
		try
		{
			if (Isboxed == false)
			{
				if (ltp > baseline)
				{
					//if (c==0)
					//{
						//String strorderid =null;
						//strorderid = placeorder(identity, qty,"BUY");
						//Tradeinfo ti=new Tradeinfo(identity, fname, "BUY", "R3", ltt, strorderid, ltp);
						way = "R3";
						//CommonObjects.GlobalTradeInfo.add(ti);
						//points = points-2;
						//tpl = (tpl + ((ltp * qty) * -1));
					//}
					//else
					//{
						//String strorderid = null;
						//strorderid = placeorder(identity, (int) (qty/2),"BUY");
						//Tradeinfo ti=new Tradeinfo(identity, fname, "BUY", "R3", ltt, strorderid, ltp);
						//way = "R3";
						//CommonObjects.GlobalTradeInfo.add(ti);
						//points = points-1;
						//tpl = (tpl + ((ltp * (int) (qty/2)) * -1));
					//}
					//c = c-1;
					//r = r+1;
					//if (c == -1)
					//{
					//	lc= lc +1;
					//}
					//if (lc == lcount)
					//{
					//	isend = true;
					//}
					//if (r == round)
					//{
					//	isend = true;
					//}
					c= 0;
					Isboxed = true;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void R4Flow()
	{
		try
		{
			if (Isboxed == false)
			{
				if (ltp <= (nextline - (nextline*(y/100))))
				{
					baseline =nextline;
					nextline = (nextline - (nextline*(y/100)));
					//points =points +1;
					//c=c+1;
					//if(c==1)
					//{
						//String strorderid;
						//strorderid = placeorder(identity, (int) (qty/2),"BUY");
						//Tradeinfo ti=new Tradeinfo(identity, fname, "BUY", "R4", ltt, strorderid, ltp);
						way = "R4";
						//CommonObjects.GlobalTradeInfo.add(ti);
						//points = points+1;
						//tpl = (tpl + ((ltp * (int) (qty/2)) * -1));
					//}
					Isboxed = true;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void R5Flow()
	{
		try
		{
			if (Isboxed == false)
			{
				if(ltp < (baseline - (baseline*(x/100))))
				{
					String strorderid= null;
					if (ltt.after(mt))
					{
						isend =  true;
					}
					else
					{
						//strorderid = placeorder(identity, qty,"SELL");
						//Tradeinfo ti=new Tradeinfo(identity, fname, "SELL", "R5", ltt, strorderid, ltp);
						//CommonObjects.GlobalTradeInfo.add(ti);
						way = "R5";
						nextline = (baseline - (baseline*(x/100)));
						//tpl = tpl + (ltp * qty);
					}
					Isboxed = true;
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	
	public String placeorder(int id,int quant, String orderside)
	{
		String clientid = null;
		try
		{
			System.out.println("Condition PASS : "+String.valueOf(id) + "; FORMULA : F5");
			if (tradeswitch == true)
			{
				SquadScripts pl= listcom.getSquadScriptsByID(id, CommonObjects.GlobalSquadScript);
				String sectype;
				if(pl.getInstrument().equalsIgnoreCase("FUTIDX"))
				{
					sectype="FUT";
				}
				else if (pl.getInstrument().equalsIgnoreCase("OPTIDX"))
				{
					sectype="OPT";
				}
				else
				{
					sectype="CM";
				}
				
				String expdate = pl.getExpdd()+"-"+com.future.prestolib.Date.getmonthvalue(pl.getExpmonthyear().substring(0, 3))+"-"+pl.getExpmonthyear().substring(3, 5);
				clientid = CommonObjects.objpresto.userPlaceOrderNSE("omnesys", sectype, pl.getheadsymbol(), pl.getheadsecid(), expdate, "FA9749", String.valueOf(quant), "0.0", "0.0", pl.getOpttype(), pl.getStrike(),
						"MARKET", "Presto_Mathsartz_Strategy", "Testing Order", "DAY", orderside);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		finally
		{
			
		}
		return clientid;
	}
	
}
