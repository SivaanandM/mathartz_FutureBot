package com.future.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Layout;

import com.future.commons.CommonObjects;
import com.future.commons.DbFuncs;
import com.future.collection.BeastViewList;
import com.future.collection.BeastViewListTableModel;
import com.future.collection.Scriptsdetail;
import com.future.collection.SquadScripts;
import com.future.collection.Tradeinfo;
import com.future.prestolib.presto_commons;
import com.future.engine.FeedAPITesterWithQueue;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;



public class BeastView implements KeyListener{

	private JFrame frmBeastview;
	private JButton btnrun;
	private JButton btndcsv;
	private JButton btnclear;
	private JButton btnstop;
	int intial;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();
    DbFuncs dbobj;
    private JScrollPane scrollPane; 
    FeedAPITesterWithQueue objfeeder;
    
    private JTable maintable, totaltable;
    private JPanel pnlmiddle;
    TableModel mainmodel;
   // String col[]= {"ID","SCRIPT","F1 Point","F1 PL","F2 Point","F2 PL","F3 Point","F3 PL",
   // 		"F4 Point","F4 PL","F5 Point","F5 PL","F6 Point","F6 PL"};
    List<BeastViewList> ViewList ;
    private static int HEADER_HEIGHT = 25;
    private String configprop=System.getProperty("user.dir")+File.separator+"conf"+File.separator+"feed.properties";
    TableColumnModel columnModel,columnModeltotal;
    DefaultTableModel totmodel;
    DefaultTableCellRenderer renderer, renderertotal;
    String strtitle="";
    Connection h2con=null;
    String [][] totalvalues;
    String [] totalhead = { "Total", "F1P","F1PL", "F2P","F2PL", "F3P","F3PL", "F4P","F4PL", "F5P","F5PL", "F6P","F6PL"};
    private JPanel downleftpnl;
    private JLabel lblNewLabel;
    private JTextField txtdd;
    private JTextField txtmmm;
    private JTextField txtyy;
    private JPanel cntr;
    private JTextField txtstrike;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeastView window = new BeastView();
					window.frmBeastview.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BeastView() 
	{
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input =new FileInputStream(configprop);
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		strtitle = "MATHARTZ "+prop.getProperty("SOFTWARE");
		CommonObjects.GlobalSoftwarefor=prop.getProperty("SOFTWARE");
	
		dbobj = new DbFuncs();
		h2con = dbobj.CheckandConnectDB(h2con);
		CommonObjects.objpresto = new presto_commons();
		ViewList = dbobj.getBeastViewData(h2con, "SELECT * FROM TBL_BEAST_VIEW ORDER BY ID;");
		initialize();
		
		maintable.setFocusable(true);
		frmBeastview.setVisible(true);
		validatecentral();
	}
	
	public void validatecentral()
	{
		CommonObjects.Globalcentralvalues = dbobj.getMultiColumnRecords(h2con, "SELECT DATE,STRIKE FROM TBL_CENTRAL WHERE ID = 1;");
		if ((CommonObjects.Globalcentralvalues[0][0] == null) || (CommonObjects.Globalcentralvalues[0][1] == null))
		{
			JOptionPane.showMessageDialog(frmBeastview, "Centralized Date & Stike is missing or invalid !!", "Missing Central Values", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			txtdd.setText(CommonObjects.Globalcentralvalues[0][0].split("-")[0]);
			txtmmm.setText(CommonObjects.Globalcentralvalues[0][0].split("-")[1]);
			txtyy.setText(CommonObjects.Globalcentralvalues[0][0].split("-")[2]);
			txtstrike.setText(CommonObjects.Globalcentralvalues[0][1]);
		}
	}

	public void Alligntable()
	{

        columnModel = maintable.getColumnModel();
		//columnModel.getColumn(0).setPreferredWidth(0);
		columnModel.getColumn(0).setMinWidth(0);
		columnModel.getColumn(0).setMaxWidth(0);
		columnModel.getColumn(0).setWidth(0);
		columnModel.getColumn(1).setPreferredWidth(50);
		columnModel.getColumn(2).setPreferredWidth(50);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(100);
		columnModel.getColumn(7).setPreferredWidth(100);
		columnModel.getColumn(8).setPreferredWidth(100);
		columnModel.getColumn(9).setPreferredWidth(100);
		columnModel.getColumn(10).setPreferredWidth(100);
		columnModel.getColumn(11).setPreferredWidth(100);
		columnModel.getColumn(12).setPreferredWidth(100);
		columnModel.getColumn(13).setPreferredWidth(100);
		columnModel.getColumn(14).setPreferredWidth(100);
		maintable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		renderer = new DefaultTableCellRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    columnModel.getColumn(0).setCellRenderer(renderer);
	    columnModel.getColumn(1).setCellRenderer(renderer);
	    columnModel.getColumn(2).setCellRenderer(renderer);
	    columnModel.getColumn(3).setCellRenderer(renderer);
	    columnModel.getColumn(4).setCellRenderer(renderer);
	    columnModel.getColumn(5).setCellRenderer(renderer);
	    columnModel.getColumn(6).setCellRenderer(renderer);
	    columnModel.getColumn(7).setCellRenderer(renderer);
	    columnModel.getColumn(8).setCellRenderer(renderer);
	    columnModel.getColumn(9).setCellRenderer(renderer);
	    columnModel.getColumn(10).setCellRenderer(renderer);
	    columnModel.getColumn(11).setCellRenderer(renderer);
	    columnModel.getColumn(12).setCellRenderer(renderer);
	    columnModel.getColumn(13).setCellRenderer(renderer);
	    columnModel.getColumn(14).setCellRenderer(renderer);
	    
	    columnModeltotal = totaltable.getColumnModel();
	   
	    columnModeltotal.getColumn(0).setPreferredWidth(125);
	    columnModeltotal.getColumn(1).setPreferredWidth(100);
	    columnModeltotal.getColumn(2).setPreferredWidth(100);
		columnModeltotal.getColumn(3).setPreferredWidth(100);
		columnModeltotal.getColumn(4).setPreferredWidth(100);
		columnModeltotal.getColumn(5).setPreferredWidth(100);
		columnModeltotal.getColumn(6).setPreferredWidth(100);
		columnModeltotal.getColumn(7).setPreferredWidth(100);
		columnModeltotal.getColumn(8).setPreferredWidth(100);
		columnModeltotal.getColumn(9).setPreferredWidth(100);
		columnModeltotal.getColumn(10).setPreferredWidth(100);
		columnModeltotal.getColumn(11).setPreferredWidth(100);
		if (scrollPane.getVerticalScrollBar().isVisible() == false)
		{
			columnModeltotal.getColumn(12).setPreferredWidth(100);
		}
		else
		{
			columnModeltotal.getColumn(12).setPreferredWidth(117);
		}
		totaltable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		renderertotal = new DefaultTableCellRenderer();
		renderertotal.setHorizontalAlignment(JLabel.CENTER);
		columnModeltotal.getColumn(0).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(1).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(2).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(3).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(4).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(5).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(6).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(7).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(8).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(9).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(10).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(11).setCellRenderer(renderertotal);
		columnModeltotal.getColumn(12).setCellRenderer(renderertotal);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Main frmBeastView Layout Design
		frmBeastview = new JFrame();
		frmBeastview.setTitle("MathsArtz "+CommonObjects.GlobalSoftwarefor);
		frmBeastview.setBackground(new Color(36,34,29));
		frmBeastview.getContentPane().setBackground(new Color(51, 51, 51));
		frmBeastview.getContentPane().setLayout(new BorderLayout(0, 0));
		frmBeastview.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmBeastview.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		    	String exitcode ="0";
		        JFrame exitframe = new JFrame("Exit Check");
		        exitcode = JOptionPane.showInputDialog(exitframe, "Enter Secret Code to Exit #");
		        if(exitcode.equalsIgnoreCase("7"))
		        {
		            System.exit(0);
		        }
		    }
		});
		
		JPanel pnlhead = new JPanel();
		pnlhead.setBackground(Color.BLACK);
		frmBeastview.getContentPane().add(pnlhead, BorderLayout.NORTH);
		pnlhead.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		JPanel pnldown = new JPanel();
		pnldown.setBackground(Color.BLACK);
		pnldown.setPreferredSize(new Dimension((int) width, 60));
		frmBeastview.getContentPane().add(pnldown, BorderLayout.SOUTH);
		pnldown.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel pnldowncenter = new JPanel();
		pnldowncenter.setBackground(Color.BLACK);
		pnldowncenter.setPreferredSize(new Dimension((((int) width) - ((int) width)/3), 60));
		pnldown.add(pnldowncenter);
		
		
		btnrun = new JButton("RUN");
		btnrun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				CommonObjects.isRunning=true;
				btnrun.setText("RUNNING..");
				CommonObjects.GlobalBeastViewList = ViewList;
				
				CommonObjects.GlobalAmazeF1 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F1';");
				CommonObjects.GlobalAmazeF2 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F2';");
				CommonObjects.GlobalAmazeF3 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F3';");
				CommonObjects.GlobalAmazeF4 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F4';");
				CommonObjects.GlobalAmazeF5 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F5';");
				CommonObjects.GlobalAmazeF6 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F6';");
				
				CommonObjects.GlobalSquadScript = dbobj.getSquadScriptData(h2con, "SELECT * FROM TBL_TRADE_LINE;");
				CommonObjects.Globaluniqueheadid = dbobj.getMultiColumnRecords(h2con, "SELECT DISTINCT(HEADID), HEADSYMBOL FROM TBL_TRADE_LINE;");
				CommonObjects.Globaltradlinemap = dbobj.getuniquetransposeId(h2con);
				
				CommonObjects.GlobalAmazeValuesF1 = dbobj.getInitialAmazevalues(h2con);
				CommonObjects.GlobalAmazeCenterValuesF2 = dbobj.getInitialAmazecentervalues(h2con);
				CommonObjects.GlobalAmazeValuesF3 = dbobj.getInitialAmazevaluesFD(h2con);
				CommonObjects.GlobalAmazeValuesF4 = dbobj.getInitialAmazevalues(h2con);
				CommonObjects.GlobalAmazeUTurnValuesF5 = dbobj.getInitialAmazecentervalues(h2con);
				CommonObjects.GlobalAmazeValuesF6 = dbobj.getInitialAmazevaluesZ(h2con);
				
				CommonObjects.GlobalTradeInfo = new ArrayList<Tradeinfo>();
				objfeeder =new FeedAPITesterWithQueue();
				 new Thread(() -> {
					 objfeeder.startfeed();
				 }).start();
				 btnrun.setEnabled(false);
				 btnclear.setEnabled(false);
				 btndcsv.setEnabled(false);
				 btnstop.setEnabled(true);
			}
		});
		btnrun.setPreferredSize(new Dimension(150, 35));
		
		btndcsv = new JButton("D-CSV");
		btndcsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [][] dashboard = dbobj.getMultiColumnRecords(h2con,"SELECT * FROM TBL_BEAST_VIEW;");
				dbobj.dCSV(dashboard, "dashboard");
				String [][] tradein = dbobj.getMultiColumnRecords(h2con,"SELECT * FROM TBL_TRADE_INFO;");
				dbobj.dCSV(tradein, "tradeinfo");
				JOptionPane.showMessageDialog(frmBeastview,"Trade Points & Trade Info Downloaded", "INFO",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btndcsv.setPreferredSize(new Dimension(150, 35));
		
		btnclear = new JButton("CLEAR");
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int opcion = JOptionPane.showConfirmDialog(null, "Are you sure, Want to Clear ?\n It will get deleted point from dashboard & TradeInfo.", "Clear Trades", JOptionPane.YES_NO_OPTION);
				if (opcion == 0) {
				dbobj.executeNonQuery(h2con, "UPDATE TBL_BEAST_VIEW set F1POINT=0, F2POINT=0, F3POINT=0, F4POINT=0, F5POINT=0 ,F6POINT=0,"
						+ "F1PL=0, F2PL=0, F3PL=0, F4PL=0, F5PL=0 ,F6PL=0 where id > 0;");
				dbobj.executeNonQuery(h2con, "DELETE FROM TBL_TRADE_INFO;");
				}
			}
		});
		btnclear.setPreferredSize(new Dimension(150, 35));
		
		btnstop = new JButton("STOP **");
		btnstop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{				
				try
				{
					Date date = new Date() ;
					SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;
					dateFormat.format(date);
					System.out.println(dateFormat.format(date));
					boolean confirmation = false;
					if(dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("15:30")))
					{
						String exitcode ="0";
				        JFrame exitframe = new JFrame("Confirm Stop !!");
				        exitcode = JOptionPane.showInputDialog(exitframe, "Stoping Before 15:30 Lead to Incomplete Trades.\nEnter Secret Code to Force Stop #");
				        if(exitcode.equalsIgnoreCase("7"))
				        {
				        	confirmation = true;
				        }
					    
					}
					else
					{
						confirmation = true;
					}
					if (confirmation == true)
					{
					    String [] updatebeastview = new String[CommonObjects.GlobalBeastViewList.size()];
					    int i = 0;
					    for (BeastViewList bv : CommonObjects.GlobalBeastViewList) 
					    {
					    	updatebeastview[i] = "UPDATE TBL_BEAST_VIEW SET F1POINT="+bv.getF1Point()+", F2POINT="+bv.getF2Point()+","
					    			+ "F3POINT="+bv.getF3Point()+",F4POINT="+bv.getF4Point()+" ,F5POINT="+bv.getF5Point()+",F6POINT="+bv.getF6Point()+""
					    					+ ",F1PL="+bv.getF1PL()+",F2PL="+bv.getF2PL()+",F3PL="+bv.getF3PL()+",F4PL="+bv.getF4PL()+",F5PL="+bv.getF5PL()+",F6PL="+bv.getF6PL()+" WHERE ID="+bv.getid()+";";
					    	i++;
						}
					    dbobj.executeBatchStatement(h2con, updatebeastview);
					    dbobj.executeNonQuery(h2con, "DELETE FROM TBL_TRADE_INFO;");
					    i=0;
					    String [] insettradeinfo = new String[CommonObjects.GlobalTradeInfo.size()];
					    for(Tradeinfo ti : CommonObjects.GlobalTradeInfo)
					    {
					    	insettradeinfo[i] = "INSERT INTO TBL_TRADE_INFO (ID,FNAME,ORDERTYPE,WAY,FST,ORDERID,PRICE) VALUES ("+ti.getid()+","
					    			+ "'"+ti.getFname()+"','"+ti.getOrdertype()+"','"+ti.getway()+"','"+ti.getfst().toString()+"','"+ti.getclientorderid()+"',"+ti.getPrice()+");";
					    	i++;
					    }
					    dbobj.executeBatchStatement(h2con, insettradeinfo);
					   
						CommonObjects.isRunning=false;
						new Thread(() -> {
							 objfeeder.stopfeed();
						 }).start();
						btnrun.setText("RUN");
						btnrun.setEnabled(true);
						btndcsv.setEnabled(true);
						btnclear.setEnabled(true);
						btnstop.setEnabled(false);
						
					}
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
				
			}
		});
		btnstop.setPreferredSize(new Dimension(150, 35));
		btnstop.setEnabled(false);
		pnldowncenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnldowncenter.add(btnrun);
		pnldowncenter.add(btndcsv);
		pnldowncenter.add(btnclear);
		pnldowncenter.add(btnstop);
		
		downleftpnl = new JPanel();
		downleftpnl.setBackground(new Color(33,33,33));
		downleftpnl.setPreferredSize(new Dimension(560, 360));
		pnldown.add(downleftpnl, BorderLayout.WEST);
		downleftpnl.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel(CommonObjects.GlobalSoftwarefor);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ebrima", Font.BOLD, 18));
		lblNewLabel.setPreferredSize(new Dimension(150, 30));
		lblNewLabel.setForeground(Color.WHITE);
		downleftpnl.add(lblNewLabel,BorderLayout.WEST);
		
		cntr = new JPanel();
		cntr.setBackground(new Color(33,33,33));
		cntr.setPreferredSize(new Dimension(150, 30));
		downleftpnl.add(cntr, BorderLayout.CENTER);
		cntr.setLayout(null);
		
		txtdd = new JTextField("");
		txtdd.setBounds(10, 11, 44, 39);
		cntr.add(txtdd);
		txtdd.setHorizontalAlignment(SwingConstants.CENTER);
		txtdd.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        e.consume();
		      }
		    }
		  });
		
		txtdd.setForeground(new Color(255, 220, 135));
		txtdd.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtdd.setColumns(2);
		txtdd.setCaretColor(Color.WHITE);
		txtdd.setBackground(Color.BLACK);
		
		txtmmm = new JTextField((String) null);
		txtmmm.setBounds(64, 11, 60, 38);
		cntr.add(txtmmm);
		txtmmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtmmm.setForeground(new Color(255, 220, 135));
		txtmmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtmmm.setColumns(3);
		txtmmm.setCaretColor(Color.WHITE);
		txtmmm.setBackground(Color.BLACK);
		txtmmm.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!(!Character.isDigit(c) ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        e.consume();
		        e.setKeyChar(Character.toUpperCase(c));
		      }
		      if (Character.isLowerCase(c)) {
			      e.setKeyChar(Character.toUpperCase(c));
			    }
		    }
		  });
		txtyy = new JTextField((String) null);
		txtyy.setBounds(134, 11, 45, 38);
		cntr.add(txtyy);
		txtyy.setHorizontalAlignment(SwingConstants.CENTER);
		txtyy.setForeground(new Color(255, 220, 135));
		txtyy.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtyy.setColumns(2);
		txtyy.setCaretColor(Color.WHITE);
		txtyy.setBackground(Color.BLACK);
		txtyy.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        e.consume();
		      }
		    }
		  });
		
		txtstrike = new JTextField((String) null);
		txtstrike.setHorizontalAlignment(SwingConstants.CENTER);
		txtstrike.setForeground(new Color(255, 220, 135));
		txtstrike.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtstrike.setColumns(2);
		txtstrike.setCaretColor(Color.WHITE);
		txtstrike.setBackground(Color.BLACK);
		txtstrike.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        e.consume();
		      }
		    }
		  });
		txtstrike.setBounds(217, 11, 115, 38);
		cntr.add(txtstrike);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(196, 12, 11, 38);
		cntr.add(separator);
		
		JButton btnFix = new JButton("FIX");
		btnFix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((txtdd.getText().equalsIgnoreCase(""))||(txtmmm.getText().equalsIgnoreCase(""))||(txtyy.getText().equalsIgnoreCase(""))||(txtstrike.getText().equalsIgnoreCase("")))
				{
					JOptionPane.showMessageDialog(frmBeastview, "Check Date and Strike value !!", "Invalid Values", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					dbobj.executeNonQuery(h2con, "UPDATE TBL_CENTRAL SET DATE='"+txtdd.getText()+"-"+txtmmm.getText()+"-"+txtyy.getText()+"',STRIKE='"+txtstrike.getText()+"' WHERE ID=1;");
					List <SquadScripts> squads = dbobj.getSquadScriptData(h2con, "SELECT * FROM TBL_TRADE_LINE ORDER BY ID;");
					for (SquadScripts sq : squads) 
					{
						Scriptsdetail headdata = null;
						headdata = dbobj.getContractdata(h2con, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+sq.getheadsymbol()+"' AND OPTTYPE='"+sq.getOpttype()+"' AND STRIKE='"+(Integer.valueOf(txtstrike.getText()) + Integer.valueOf(sq.getMarker()))+"' AND EXPDD='"+txtdd.getText()+"' AND EXPMONTHYEAR='"+txtmmm.getText().toUpperCase()+txtyy.getText()+"';").get(0);
						
						dbobj.getIdentityforInsert(h2con, "UPDATE TBL_TRADE_LINE SET HEADID='"+headdata.getSecid()+"', HEADDISPLAY='"+headdata.getSymbol()+"'," + 
								"HEADSYMBOL='"+headdata.getSymbol()+"', EXCHANGE='"+headdata.getExchange()+"', INSTRUMENT='"+headdata.getInstrument()+"' ,LOTSIZE='"+headdata.getLotsize()+"',"
										+ " TICKSIZE='"+headdata.getTicksize()+"', EXPDD='"+headdata.getExpdd()+"',EXPMONTHYEAR='"+headdata.getExpmonthyear()+"', OPTTYPE='"+headdata.getOpttype()+"' , STRIKE='"+headdata.getStrike()+"' ,MARKER='"+sq.getMarker()+"' WHERE ID="+sq.getid()+";");
						dbobj.executeNonQuery(h2con, "DELETE FROM TBL_BEAST_VIEW WHERE ID="+sq.getid()+";");
						dbobj.executeNonQuery(h2con, "INSERT INTO TBL_BEAST_VIEW (ID,RIGHT,STRIKE) VALUES ("+sq.getid()+",'"+headdata.getOpttype()+"','"+headdata.getStrike()+"');");
					
					}
					
					
				}
			}
		});
		btnFix.setBounds(342, 11, 58, 38);
		cntr.add(btnFix);
		
		
		pnlmiddle = new JPanel();
		pnlmiddle.setBorder(null);
		pnlmiddle.setBackground(Color.BLACK);
		frmBeastview.getContentPane().add(pnlmiddle, BorderLayout.CENTER);
		
		
	    frmBeastview.setSize((int)width, (int)height-40);
	    frmBeastview.setResizable(true);
	    
	    
	    maintable = new JTable();
	    maintable.setBounds(10, 559, 923, -345);
	    maintable.setBackground(Color.BLACK);
	    maintable.setFillsViewportHeight(true);
	    maintable.setFont(new Font("Ebrima", Font.BOLD, 12));
	    mainmodel = new BeastViewListTableModel(ViewList);
	    
		maintable = new JTable(mainmodel){
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		        Component returnComp = super.prepareRenderer(renderer, row, column);
		        Color alternateColor = new Color(10,11,12);//(58,54,51);
		        Color whiteColor = new Color(33,33,33);
		        if (!returnComp.getBackground().equals(getSelectionBackground())){
		            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
		            returnComp .setBackground(bg);
		            returnComp.setForeground(Color.WHITE);
		            bg = null;
		        }
		    
		        if((column == 1) || (column == 0)|| (column == 2))
		        {
		        	returnComp.setFont(new Font("Ebrima", Font.PLAIN, 12));
		        	returnComp.setForeground(Color.white);
		        	if (column == 1)
		        	{
		        		String value = maintable.getValueAt(row, column).toString();
		        		if (value.equalsIgnoreCase("CE"))
		        		{
		        			returnComp.setForeground(Color.WHITE);
		        		}
		        		else
		        		{
		        			returnComp.setForeground(new Color(18,226,237));
		        		}
		        	}
		        }
		        if (column > 2)
		        {
			        double value = Double.parseDouble(maintable.getValueAt(row, column).toString());
			        if (value == 0)
		        	{
			        	returnComp.setFont(new Font("Ebrima", Font.PLAIN, 0));
		        	}
			        else
			        {	if (value > 0)
			        	{
			        		returnComp.setForeground(new Color(250,229,165));
			        	}
			        	else if (value < 0)
			        	{
			        		returnComp.setForeground(new Color(145,208,242));
			        	}
				        
				        returnComp.setFont(new Font("Ebrima", Font.PLAIN, 12));
			        }
		        }
		        
		        return returnComp;
		    }
		    @Override
		    public boolean isCellEditable(int i, int i1) {
		        return false; //To change body of generated methods, choose Tools | Templates.
		    }   
		};
		maintable.setBackground(Color.GRAY);
		maintable.setRowHeight(20);
		maintable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		maintable.setRowSelectionAllowed(true);
		maintable.setShowGrid(true);
		maintable.setIntercellSpacing(new Dimension(1, 0));
		maintable.setShowVerticalLines(true);
		maintable.setShowHorizontalLines(false);
		maintable.setSelectionBackground(new Color(0,70,140));
		
		
		JTableHeader header = maintable.getTableHeader();
		header.setForeground(Color.WHITE);
		header.setBackground(Color.black);
	    header.setFont(new Font("Ebrima", Font.BOLD, 14));
	    header.setPreferredSize(new Dimension(0, HEADER_HEIGHT));
	    scrollPane = new JScrollPane(maintable);
	    scrollPane.setBounds(22, 185, 897, 367);
	    pnlmiddle.add(scrollPane);
	    scrollPane.setEnabled(false);
	    scrollPane.getViewport().setBackground(Color.BLACK);
	    scrollPane.setBackground(Color.BLACK);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportBorder(null);
		scrollPane.setPreferredSize(new Dimension((int)width-25, (int)height-185));
		scrollPane.setBorder(null);
		scrollPane.setColumnHeaderView(new JViewport() {
		      @Override public Dimension getPreferredSize() {
		          Dimension d = super.getPreferredSize();
		          d.height = HEADER_HEIGHT;
		          return d;
		        }
		      });
		
		totalvalues = dbobj.getMultiColumnRecords(h2con, "SELECT 'TOTAL',  round(SUM(F1POINT),2),  round(SUM(F1PL),2),   round(SUM(F2POINT),2),  round(SUM(F2PL),2),  round(SUM(F3POINT),2),  round(SUM(F3PL),2),  round(SUM(F4POINT),2), round(SUM(F4PL),2), round( SUM(F5POINT),2),  round(SUM(F5PL),2),  round(SUM(F6POINT),2),  round(SUM(F6PL),2) FROM TBL_BEAST_VIEW;");
		totmodel = new DefaultTableModel(totalvalues, totalhead);
		totaltable = new JTable(totmodel) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		        Component returnComp = super.prepareRenderer(renderer, row, column);
		        
		        if (column > 0)
		        {
		        	if ( totaltable.getValueAt(row, column) != null)
		        	{
			        	double value = Double.parseDouble((String) totaltable.getValueAt(row, column));
			        	if (value > 0)
			        	{
			        		returnComp.setForeground(new Color(250,229,165));
			        		returnComp.setFont(new Font("Ebrima", Font.PLAIN, 18));
			        	}
			        	else if (value < 0)
			        	{
			        		returnComp.setForeground(new Color(145,208,242));
			        		returnComp.setFont(new Font("Ebrima", Font.PLAIN, 18));
			        	}
			        	else
			        	{
			        		returnComp.setFont(new Font("Ebrima", Font.PLAIN, 0));
			        	}
		        	}
		        }
		        else
		        {
		        	returnComp.setForeground(Color.WHITE);
		        	returnComp.setFont(new Font("Ebrima", Font.BOLD, 18));
		        }
		        
				return returnComp;
			}
		};
		totaltable.setFont(new Font("Ebrima", Font.PLAIN, 11));
		totaltable.setBorder(new LineBorder(new Color(192, 192, 192)));
		totaltable.setTableHeader(null);
		totaltable.setRowSelectionAllowed(true);
		totaltable.setRowHeight(34);
		totaltable.setPreferredSize(new Dimension((int)width -25, 35));
		totaltable.setBackground(Color.BLACK);
		totaltable.setShowGrid(true);
		totaltable.setShowVerticalLines(true);
		totaltable.setSelectionBackground(new Color(0,70,140));

		
		pnlmiddle.add(totaltable);
		Alligntable();
		
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try
				{
					Timer timer = new Timer(0, new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							try
							{
								int selectedrow = maintable.getSelectedRow();
            		            if (selectedrow != -1)
            		            {
            		            		selectedrow = maintable.getSelectedRow();
            		            }
            		            // Need to Right logic for refresh maintable
            		            if (CommonObjects.isRunning == false)
            		            {
            		            	ViewList = dbobj.getBeastViewData(h2con, "SELECT * FROM TBL_BEAST_VIEW ORDER BY ID;");
            		            	mainmodel =new  BeastViewListTableModel(ViewList);
            		            	
            		            	totalvalues = dbobj.getMultiColumnRecords(h2con, "SELECT 'TOTAL',  round(SUM(F1POINT),2),  round(SUM(F1PL),2),   round(SUM(F2POINT),2),  round(SUM(F2PL),2),  round(SUM(F3POINT),2),  round(SUM(F3PL),2),  round(SUM(F4POINT),2), round(SUM(F4PL),2), round( SUM(F5POINT),2),  round(SUM(F5PL),2),  round(SUM(F6POINT),2),  round(SUM(F6PL),2) FROM TBL_BEAST_VIEW;");
            		        		totmodel = new DefaultTableModel(totalvalues, totalhead);
            		            }
            		            else
            		            {
            		            	totalvalues=new String[][]{{"Total", "0.0","0.0", "0.0","0.0", "0.0","0.0", "0.0","0.0", "0.0","0.0", "0.0","0.0"}};
            		            	mainmodel = new  BeastViewListTableModel(CommonObjects.GlobalBeastViewList);
            		            	//getting total from main model
            		            	double temp;
            		            	DecimalFormat f = new DecimalFormat("##.00");
            		            	for (int i=0 ; i < mainmodel.getRowCount() ; i++)
            		            	{
            		            		temp =(double) mainmodel.getValueAt(i, 3);
            		            		totalvalues[0][1] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][1])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 4);
            		            		totalvalues[0][2] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][2])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 5);
            		            		totalvalues[0][3] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][3])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 6);
            		            		totalvalues[0][4] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][4])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 7);
            		            		totalvalues[0][5] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][5])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 8);
            		            		totalvalues[0][6] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][6])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 9);
            		            		totalvalues[0][7] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][7])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 10);
            		            		totalvalues[0][8] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][8])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 11);
            		            		totalvalues[0][9] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][9])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 12);
            		            		totalvalues[0][10] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][10])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 13);
            		            		totalvalues[0][11] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][11])+temp));
            		            		temp =(double) mainmodel.getValueAt(i, 14);
            		            		totalvalues[0][12] =String.valueOf(f.format(Double.valueOf((String) totalvalues[0][12])+temp));
            		            	}
            		            	totmodel = new DefaultTableModel(totalvalues, totalhead);
            		            }
            		            maintable.setModel(mainmodel);
            		            columnModel = maintable.getColumnModel();
            		            
            		            totaltable.setModel(totmodel);
            		            columnModeltotal= totaltable.getColumnModel();
            		            
            		            Alligntable();
            		            
            		            if (selectedrow != -1)
					            {
							    	 if (maintable.getRowCount() > 0)
							    	 {
							    		 try
							    		 {
							    			 maintable.setRowSelectionInterval(selectedrow, selectedrow);
							    		 }
							    		 catch(Exception ex)
							    		 {
							    			 
							    		 }
							    	 }
					            } 
            		            
							}
							catch(Exception ex)
							{
								System.out.println(ex.toString());
							}
						}
						
					});
					timer.setDelay(1000); // delay for 30 seconds
            		timer.start();
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
			}
		});
		
		
	
		maintable.addKeyListener(new KeyAdapter() {
		      public void keyPressed(KeyEvent e) 
		      {
			    	  if (e.getKeyCode() == 27) 
					  {
			    		  //CTRL+ A
			    		  maintable.getSelectionModel().clearSelection();
					  }
			    	  if (e.isControlDown() && e.getKeyCode() == 65) 
					  {
			    		  //CTRL+ A
			    		  ScriptManager sm = new ScriptManager("ADD", 0);
					  }
			    	  if (e.isControlDown() && e.getKeyCode() == 69) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			//CTRL+ E
			    			ScriptManager sm = new ScriptManager("EDIT", (int)maintable.getValueAt(maintable.getSelectedRow(),0));  
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if (e.isControlDown() && e.getKeyCode() == 49) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //CTL + 1 
			    			  TradeInsight tradeinfo1=new TradeInsight("F1",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if (e.isControlDown() && e.getKeyCode() == 50) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //CTL + 2
			    			  TradeInsight tradeinfo1=new TradeInsight("F2",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if (e.isControlDown() && e.getKeyCode() == 51) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //CTL + 3 
			    			  TradeInsight tradeinfo1=new TradeInsight("F3",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  
			    	  if (e.isControlDown() && e.getKeyCode() == 52) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //CTL + 4
			    			  TradeInsight tradeinfo1=new TradeInsight("F4",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if (e.isControlDown() && e.getKeyCode() == 53) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //CTL + 5 
			    			  TradeInsight tradeinfo1=new TradeInsight("F5",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if (e.isControlDown() && e.getKeyCode() == 54) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //CTL + 6
			    			  TradeInsight tradeinfo1=new TradeInsight("F6",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if (e.isControlDown() && e.getKeyCode() == 83) 
					  {
			    		  //CTRL+ S
			    		  ScriptSearch search = new ScriptSearch(CommonObjects.objpresto);
			          }
			    	  if ( e.getKeyCode() == 112) 
					  {
			    		  //F1
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  Formulations f1=new Formulations("F1", maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if ( e.getKeyCode() == 113) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //F2
			    			  Formulations f2=new Formulations("F2", maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
			    		  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if ( e.getKeyCode() == 114) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //F3
			    			  Formulations f3=new Formulations("F3", maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
			    		  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
					  }
			    	  if ( e.getKeyCode() == 115) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //F4
			    			  Formulations f4=new Formulations("F4", maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
			    		  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
					  }
			    	  if ( e.getKeyCode() == 116) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //F5
			    			  Formulations f5=new Formulations("F5", maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
			    		  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
				      }
			    	  if ( e.getKeyCode() == 117) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //F6
			    			  Formulations f6=new Formulations("F6", maintable.getValueAt(maintable.getSelectedRow(),1).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
			    		  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
				      }
			    	  if (e.isControlDown() && e.getKeyCode() == 68) 
					  {
			    		  //CTRL+ D
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
				    		  int opcion = JOptionPane.showConfirmDialog(null, "Are you sure, Want to Delete Head ?\n It will get deleted from Dashboard, Script and Formula.", "Delete Script", JOptionPane.YES_NO_OPTION);
							  if (opcion == 0) {
					    		  String stmts[] = new String[3];
					    		  stmts[0] = "DELETE FROM TBL_TRADE_LINE WHERE ID="+(int)(maintable.getValueAt(maintable.getSelectedRow(),0))+";"; 
					    		  stmts[1] = "DELETE FROM TBL_BEAST_VIEW WHERE ID="+(int)(maintable.getValueAt(maintable.getSelectedRow(),0))+";"; 
					    		  stmts[2] = "DELETE FROM TBL_FORMULA_DATA WHERE ID="+(int)(maintable.getValueAt(maintable.getSelectedRow(),0))+";"; 
					    		  dbobj.executeBatchStatement(h2con, stmts);
							  }
				    	  }
			    		  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
		    	 
		      }
		    });
		intial = 1;
		frmBeastview.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) 
		    {
		    	if (intial != 1)
		    	{
		    	//width = frmBeastview.getWidth();
		    	//height = frmBeastview.getHeight();
		    	pnldown.setPreferredSize(new Dimension((int) frmBeastview.getWidth(), 60));
		    	pnldowncenter.setPreferredSize(new Dimension((((int) frmBeastview.getWidth()) - ((int) width)/3), 60));
		    	//frmBeastview.setSize((int)width, (int)height-40);
		    	scrollPane.setPreferredSize(new Dimension((int)frmBeastview.getWidth()-25, (int)frmBeastview.getHeight()-185));
		    	totaltable.setPreferredSize(new Dimension((int)frmBeastview.getWidth() -25, 35));
		    	}
		    	
		    	
		        
		    }
		    
		});
		intial = intial+1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if (e.isControlDown() && e.getKeyCode() == 67) 
		{
  		  //CTRL+ C
  		  ScriptSearch search = new ScriptSearch(CommonObjects.objpresto);
        }
		if (e.isControlDown() && e.getKeyCode() == 65) 
		{
  		  //CTRL+ A
			ScriptManager sm = new ScriptManager("ADD", 0);	 
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
