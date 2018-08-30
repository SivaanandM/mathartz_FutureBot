package com.beastbot.list;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BeastViewListTableModel extends AbstractTableModel
{
	  private List<BeastViewList> values ;
	  private String[] columns ;
	  public BeastViewListTableModel(List<BeastViewList> avalues)
	  {
		  	super();
		  	values = avalues ;
		    columns = new String[]{"ID","HEAD","F1 Point","F2 Point","F3 Point","F4 Point","F5 Point","PLAYER"};
	  }
	// Number of column of your table
	  public int getColumnCount() {
	    return columns.length ;
	  }

	  // Number of row of your table
	  public int getRowsCount() {
	    return values.size();
	  }
	  @Override
	  public int getRowCount() {
		  return values.size();
	  }
	// Optional, the name of your column
	  public String getColumnName(int col) {
	    return columns[col] ;
	  }
	  @Override
	  public Object getValueAt(int row, int col) {
		  //return values.get(row).
		  BeastViewList list = values.get(row);
		    switch(col) {
		      case 0: return list.getid();
		      case 1: return list.getHeadDisplay();
		      case 2: return list.getF1Point();
		      case 3: return list.getF2Point();
		      case 4: return list.getF3Point();
		      case 5: return list.getF4Point();
		      case 6: return list.getF5Point();
		      case 7: return list.getPlayerDisplay();
		      // to complete here...
		      default: return null;
		    }
	  }
	  @Override
	  public void fireTableDataChanged() {
	        super.fireTableDataChanged();
	    }
}