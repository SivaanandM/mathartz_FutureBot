package com.beastbot.list;

public class BeastViewList 
{
  int id;
  String headdisplay, playerdisplay;
  double F1Point, F2Point, F3Point, F4Point, F5Point;
  
  public BeastViewList(int id, String headdisplay, double F1Point, double F2Point, double F3Point, double F4Point,double F5Point, String playerdisplay)
  {
	  this.id = id;
	  this.headdisplay = headdisplay;
	  this.F1Point = F1Point;
	  this.F2Point = F2Point;
	  this.F3Point = F3Point;
	  this.F4Point = F4Point;
	  this.F5Point = F5Point;
	  this.playerdisplay = playerdisplay;
  }
 
  public int getid()
  {
		return id;
  }
  public String getHeadDisplay()
  {
		return headdisplay;
  }
  public double getF1Point()
  {
		return F1Point;
  }
  public double getF2Point()
  {
		return F2Point;
  }
  public double getF3Point()
  {
		return F3Point;
  }
  public double getF4Point()
  {
		return F4Point;
  }
  public double getF5Point()
  {
		return F5Point;
  }
  public String getPlayerDisplay()
  {
		return playerdisplay;
  }

  public void setF1Point(double f1p)
  {
		this.F1Point=f1p;
  }
  public void setF2Point(double f2p)
  {
		this.F2Point = f2p;
  }
  public void setF3Point(double f3p)
  {
		this.F3Point = f3p;
  }
  public void setF4Point(double f4p)
  {
		this.F4Point=f4p;
  }
  public void setF5Point(double f5p)
  {
		this.F5Point = f5p;
  }
  public String setPlayerDisplay()
  {
		return playerdisplay;
  }
  public String[] getData()
  {
    return new String[]{String.valueOf(id),headdisplay,String.valueOf(F1Point),String.valueOf(F2Point), String.valueOf(F3Point),String.valueOf(F4Point),String.valueOf(F5Point), playerdisplay};
  }
  
  
}
