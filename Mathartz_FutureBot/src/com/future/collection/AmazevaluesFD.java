package com.future.collection;

public class AmazevaluesFD {
	
	int id;
	String headsecid, way;
	int C, LC, R;
	double points, fd, nextline, baseline;
	double tpl;
	
	public AmazevaluesFD(int id, String headsecid, int c, int lc, int r, double points, double fd, double nextline, double baseline, String way, double tpl)
	{
		this.id = id;
		this.headsecid = headsecid;
		this.C = c;
		this.LC = lc;
		this.R = r;
		this.points = points;
		this.fd = fd;
		this.nextline = nextline;
		this.baseline = baseline;
		this.way = way;
		this.tpl = tpl;
	}
	public int getid()
	{
		return id;
	}
	public String getheadsecid()
	{
		return headsecid;
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
	public double getfd()
	{
		return fd;
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
	public Double gettpl()
	{
		return tpl;
	}
	public String[] getData()
	{
	    return new String[]{String.valueOf(id), headsecid, String.valueOf(LC), String.valueOf(R), 
	    		String.valueOf(points), String.valueOf(fd), String.valueOf(nextline), String.valueOf(baseline), way, String.valueOf(tpl)};
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
	public void setlow(double fdval)
	{
		this.fd=fdval;
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
	public void settpl(double tpl)
	{
		this.tpl = tpl;
	}
	
	public void SetAmazevalues(int c, int lc, int r, double points, double fd, double nextline, double baseline, String way, double tpl)
	{
		this.C = c;
		this.LC = lc;
		this.R = r;
		this.points = points;
		this.fd = fd;
		this.nextline = nextline;
		this.baseline = baseline;
		this.way = way;
		this.tpl = tpl;
	}
	

}
