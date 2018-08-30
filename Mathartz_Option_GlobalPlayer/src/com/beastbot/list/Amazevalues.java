package com.beastbot.list;

import java.util.List;

public class Amazevalues {
	
	int id;
	String headsecid, playersecid, way;
	int C, LC, R;
	double points, low, high, nextline, baseline;
	
	public Amazevalues(int id, String headsecid, String playersecid, int c, int lc, int r, double points, double low, double high, double nextline, double baseline, String way)
	{
		this.id = id;
		this.headsecid = headsecid;
		this.playersecid = playersecid;
		this.C = c;
		this.LC = lc;
		this.R = r;
		this.points = points;
		this.low = low;
		this.high = high;
		this.nextline = nextline;
		this.baseline = baseline;
		this.way = way;
	}
	public int getid()
	{
		return id;
	}
	public String getheadsecid()
	{
		return headsecid;
	}
	public String getplayersecid()
	{
		return playersecid;
	}
	public int getc()
	{
		return C;
	}
	public int getlc()
	{
		return LC;
	}
	public int getr()
	{
		return R;
	}
	public double getpoints()
	{
		return points;
	}
	public double getlow()
	{
		return low;
	}
	public double gethigh()
	{
		return high;
	}
	public double getnextline()
	{
		return nextline;
	}
	public double getbaseline()
	{
		return baseline;
	}
	public String getway()
	{
		return way;
	}
	public String[] getData()
	{
	    return new String[]{String.valueOf(id), headsecid, playersecid,String.valueOf(C), String.valueOf(LC), String.valueOf(R), 
	    		String.valueOf(points), String.valueOf(low), String.valueOf(high), String.valueOf(nextline), String.valueOf(baseline), way};
	}
	
	public void setc(int cval)
	{
		this.C = cval;
	}
	public void setlc(int lcval)
	{
		this.LC = lcval;
	}
	public void setr(int rval)
	{
		this.R = rval;
	}
	public void setpoints(double pointsval)
	{
		this.points=pointsval;
	}
	public void setlow(double lowval)
	{
		this.low=lowval;
	}
	public void sethigh(double highval)
	{
		this.high = highval;
	}
	public void setnextline(double nextlineval)
	{
		this.nextline=nextlineval;
	}
	public void setbaseline(double baselineval)
	{
		this.baseline=baselineval;
	}
	public void setway(String wayval)
	{
		this.way=wayval;
	}
	
	public void SetAmazevalues(int c, int lc, int r, double points, double low, double high, double nextline, double baseline, String way)
	{
		this.C = c;
		this.LC = lc;
		this.R = r;
		this.points = points;
		this.low = low;
		this.high = high;
		this.nextline = nextline;
		this.baseline = baseline;
		this.way = way;
	}
	

}
