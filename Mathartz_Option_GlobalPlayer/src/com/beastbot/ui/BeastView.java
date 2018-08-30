package com.beastbot.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.beastbot.common.CommonObjects;
import com.beastbot.common.DbFuncs;
import com.beastbot.list.Amazevalues;
import com.beastbot.list.BeastViewList;
import com.beastbot.list.BeastViewListTableModel;
import com.beastbot.list.SquadScripts;
import com.beastbot.list.Tradeinfo;
import com.beastbot.list.getListcommon;
import com.beastbot.presto.Date;
import com.beastbot.presto.presto_commons;
import com.beastbot.presto.engine.FeedAPITesterWithQueue;



public class BeastView implements KeyListener{

	private JFrame frmBeastview;
	private JTextField txtdd;
	private JTextField txtmmm;
	private JTextField txtyy;
	private JButton btnrun;
	private JButton btndcsv;
	private JButton btnclear;
	private JButton btnstop;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();
    String [] centraldate ;
    DbFuncs dbobj;
    private JScrollPane scrollPane; 
    private JTable maintable;
    private JPanel pnlmiddle;
    TableModel mainmodel;
    String col[]= {"ID","HEAD","F1 Point","F2 Point","F3 Point","F4 Point","F5 Point","PLAYER"};
    List<BeastViewList> ViewList ;
    private static int HEADER_HEIGHT = 32;
    
    TableColumnModel columnModel;
    DefaultTableCellRenderer renderer;
    
    Connection h2con=null;
    

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
		dbobj = new DbFuncs();
		h2con = dbobj.CheckandConnectDB(h2con);
		CommonObjects.objpresto = new presto_commons();
		centraldate = dbobj.loadCentralizedDate(h2con);
		ViewList = dbobj.getBeastViewData(h2con, "SELECT * FROM TBL_BEAST_VIEW ORDER BY ID;");
		initialize();
		frmBeastview.setVisible(true);
	}

	public void Alligntable()
	{

        columnModel = maintable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(5);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(100);
		columnModel.getColumn(7).setPreferredWidth(200);
		maintable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		renderer = new DefaultTableCellRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    columnModel.getColumn(0).setCellRenderer(renderer);
	    columnModel.getColumn(2).setCellRenderer(renderer);
	    columnModel.getColumn(3).setCellRenderer(renderer);
	    columnModel.getColumn(4).setCellRenderer(renderer);
	    columnModel.getColumn(5).setCellRenderer(renderer);
	    columnModel.getColumn(6).setCellRenderer(renderer);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Main frmBeastView Layout Design
		frmBeastview = new JFrame();
		frmBeastview.setTitle("Mathartz Amaze Board");
		frmBeastview.setBackground(new Color(36,34,29));
		frmBeastview.getContentPane().setBackground(new Color(51, 51, 51));
		frmBeastview.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlhead = new JPanel();
		pnlhead.setBackground(new Color(51, 51, 51));
		frmBeastview.getContentPane().add(pnlhead, BorderLayout.NORTH);
		pnlhead.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblhead = new JLabel("Mathart'z AMAZE");
		
		lblhead.setHorizontalAlignment(SwingConstants.CENTER);		
		lblhead.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblhead.setForeground(new Color(240,159,108));
		pnlhead.add(lblhead);
		
		JPanel pnldown = new JPanel();
		pnldown.setBackground(new Color(51, 51, 51));
		pnldown.setPreferredSize(new Dimension((int) width, 60));
		pnldown.setLayout(new BorderLayout());
		frmBeastview.getContentPane().add(pnldown, BorderLayout.SOUTH);
		
		JPanel pnldownleft = new JPanel();
		pnldownleft.setBackground(new Color(51, 51, 51));
		pnldownleft.setPreferredSize(new Dimension(((int) width)/7, 60));
		pnldown.add(pnldownleft,BorderLayout.WEST);
		
		
		
		
		txtdd = new JTextField(centraldate[0]);
		txtdd.setBounds(0, 0, 0, 0);
		txtdd.setHorizontalAlignment(SwingConstants.CENTER);
		txtdd.setForeground(new Color(255, 220, 135));
		txtdd.setFont(new Font("Verdana", Font.PLAIN, 16));
		txtdd.setColumns(2);
		txtdd.setCaretColor(Color.WHITE);
		txtdd.setBackground(new Color(80,75,78));
		
		txtmmm = new JTextField(centraldate[1]);
		txtmmm.setBounds(0, 0, 0, 0);
		txtmmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtmmm.setForeground(new Color(255, 220, 135));
		txtmmm.setFont(new Font("Verdana", Font.PLAIN, 16));
		txtmmm.setColumns(3);
		txtmmm.setCaretColor(Color.WHITE);
		txtmmm.setBackground(new Color(80,75,78));
		
		txtyy = new JTextField(centraldate[2]);
		txtyy.setBounds(0, 0, 0, 0);
		txtyy.setHorizontalAlignment(SwingConstants.CENTER);
		txtyy.setForeground(new Color(255, 220, 135));
		txtyy.setFont(new Font("Verdana", Font.PLAIN, 16));
		txtyy.setColumns(2);
		txtyy.setCaretColor(Color.WHITE);
		txtyy.setBackground(new Color(80,75,78));
		
		JPanel pnldowncenter = new JPanel();
		pnldowncenter.setBackground(new Color(51, 51, 51));
		pnldowncenter.setPreferredSize(new Dimension((((int) width) - ((int) width)/3), 60));
		pnldown.add(pnldowncenter,BorderLayout.CENTER);
		
		
		btnrun = new JButton("RUN");
		btnrun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				CommonObjects.isRunning=true;
				CommonObjects.GlobalBeastViewList = ViewList;
				CommonObjects.GlobalAmazeFormulaList =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F1';");
				
				CommonObjects.GlobalAmazeF2 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F2';");
				CommonObjects.GlobalAmazeF3 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F3';");
				CommonObjects.GlobalAmazeF4 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F4';");
				CommonObjects.GlobalAmazeF5 =  dbobj.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='F5';");
				
				CommonObjects.GlobalSquadScript = dbobj.getSquadScriptData(h2con, "SELECT * FROM TBL_TRADE_LINE;");
				CommonObjects.Globaluniqueheadid = dbobj.getMultiColumnRecords(h2con, "SELECT DISTINCT(HEADID), HEADSYMBOL FROM TBL_TRADE_LINE;");
				CommonObjects.Globaltradlinemap = dbobj.getuniquetransposeId(h2con);
				CommonObjects.GlobalAmazeValues = dbobj.getInitialAmazevalues(h2con);
				CommonObjects.GlobalAmazeValuesF2 = dbobj.getInitialAmazevalues(h2con);
				CommonObjects.GlobalAmazeValuesF3 = dbobj.getInitialAmazevalues(h2con);
				CommonObjects.GlobalAmazeValuesF4 = dbobj.getInitialAmazevalues(h2con);
				CommonObjects.GlobalAmazeValuesF5 = dbobj.getInitialAmazevalues(h2con);
				CommonObjects.GlobalTradeInfo = new ArrayList<Tradeinfo>();
				FeedAPITesterWithQueue objfeeder =new FeedAPITesterWithQueue();
				 new Thread(() -> {
					 objfeeder.startfeed();
				 }).start();			
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
				dbobj.executeNonQuery(h2con, "UPDATE TBL_BEAST_VIEW set F1POINT=0, F2POINT=0, F3POINT=0, F4POINT=0, F5POINT=0 where id > 0;");
				dbobj.executeNonQuery(h2con, "DELETE FROM TBL_TRADE_INFO;");
				}
			}
		});
		btnclear.setPreferredSize(new Dimension(150, 35));
		
		btnstop = new JButton("SYNC **");
		btnstop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{				
				try
				{
				    String [] updatebeastview = new String[CommonObjects.GlobalBeastViewList.size()];
				    int i = 0;
				    for (BeastViewList bv : CommonObjects.GlobalBeastViewList) 
				    {
				    	updatebeastview[i] = "UPDATE TBL_BEAST_VIEW SET F1POINT='"+bv.getF1Point()+"', F2POINT='"+bv.getF2Point()+"',"
				    			+ "F3POINT='"+bv.getF3Point()+"',F4POINT='"+bv.getF4Point()+"' ,F5POINT='"+bv.getF5Point()+"' WHERE ID="+bv.getid()+";";
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
				}
				catch(Exception ex)
				{
					
				}
				
			}
		});
		btnstop.setPreferredSize(new Dimension(150, 35));
		GroupLayout gl_pnldowncenter = new GroupLayout(pnldowncenter);
		gl_pnldowncenter.setHorizontalGroup(
			gl_pnldowncenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnldowncenter.createSequentialGroup()
					.addGap(((int)width/4)-150)
					.addComponent(btnrun, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btndcsv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnclear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnstop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(399))
		);
		gl_pnldowncenter.setVerticalGroup(
			gl_pnldowncenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnldowncenter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnldowncenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(btndcsv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnrun, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnclear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnstop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		pnldowncenter.setLayout(gl_pnldowncenter);
		
		JButton btndatefix = new JButton("FIX");
		btndatefix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Boolean done = dbobj.executeNonQuery(h2con, "UPDATE TBL_CENTRAL_DATE  SET DATE = '"+txtdd.getText()+"-"+txtmmm.getText()+"-"+txtyy.getText()+"'");
				JOptionPane.showMessageDialog(frmBeastview,"Date Centralized  : "+String.valueOf(done), "INFO",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		
		
		
		GroupLayout gl_pnldown = new GroupLayout(pnldownleft);
		gl_pnldown.setHorizontalGroup(
			gl_pnldown.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnldown.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtdd, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtmmm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtyy, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btndatefix)
					.addContainerGap())
		);
		gl_pnldown.setVerticalGroup(
			gl_pnldown.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnldown.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnldown.createParallelGroup(Alignment.LEADING)
						.addComponent(txtdd, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
						.addComponent(btndatefix, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
						.addGroup(gl_pnldown.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtyy, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
							.addComponent(txtmmm, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
					.addContainerGap())
		);
		pnldownleft.setLayout(gl_pnldown);
		
		pnlmiddle = new JPanel();
		pnlmiddle.setBorder(null);
		pnlmiddle.setBackground(new Color(51, 51, 51));
		frmBeastview.getContentPane().add(pnlmiddle, BorderLayout.CENTER);
		
		
		frmBeastview.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmBeastview.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	        	System.out.println("Bye Bye ...");
	        	frmBeastview.dispose();
	            System.exit(0);
	        }
	    });
		
	    frmBeastview.setSize((int)width, (int)height-40);
	    frmBeastview.setResizable(false);
	    
	    
	    maintable = new JTable();
	    maintable.setBounds(10, 559, 923, -345);
	    maintable.setBackground(new Color(51, 51, 51));
	    maintable.setFillsViewportHeight(true);
	    maintable.setFont(new Font("Tahoma", Font.BOLD, 14));
	    mainmodel = new BeastViewListTableModel(ViewList);
	    
		maintable = new JTable(mainmodel){
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		        Component returnComp = super.prepareRenderer(renderer, row, column);
		        Color alternateColor = new Color(58,54,51);
		        Color whiteColor = new Color(79,75,72);
		        if (!returnComp.getBackground().equals(getSelectionBackground())){
		            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
		            returnComp .setBackground(bg);
		            returnComp.setForeground(Color.WHITE);
		            bg = null;
		        }
		        return returnComp;
		    }
		    @Override
		    public boolean isCellEditable(int i, int i1) {
		        return false; //To change body of generated methods, choose Tools | Templates.
		    }   
		};
		maintable.setBorder(null);
		maintable.setBackground(Color.GRAY);
		maintable.setRowHeight(25);
		maintable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		maintable.setRowSelectionAllowed(true);
		Alligntable();
		
		JTableHeader header = maintable.getTableHeader();
		header.setForeground(new Color(255, 220, 135));
		header.setBackground(new Color(51, 51, 51));
	    header.setFont(new Font("Tahoma", Font.BOLD, 14));
	    header.setPreferredSize(new Dimension(100, HEADER_HEIGHT));
	    JScrollPane scrollPane = new JScrollPane(maintable);
	    scrollPane.setBounds(22, 185, 897, 367);
	    pnlmiddle.add(scrollPane);
	    scrollPane.setEnabled(false);
	    scrollPane.getViewport().setBackground(new Color(51, 51, 51));
	    scrollPane.setBackground(new Color(51, 51, 51));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportBorder(null);
		scrollPane.setPreferredSize(new Dimension((int)width-25, (int)height-175));
		scrollPane.setBorder(null);
		scrollPane.setColumnHeaderView(new JViewport() {
		      @Override public Dimension getPreferredSize() {
		          Dimension d = super.getPreferredSize();
		          d.height = HEADER_HEIGHT;
		          return d;
		        }
		      });
		
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
            		            }
            		            else
            		            {
            		            	mainmodel = new  BeastViewListTableModel(CommonObjects.GlobalBeastViewList);
            		            }
            		            maintable.setModel(mainmodel);
            		            columnModel = maintable.getColumnModel();
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
		    	  
			    	  if (e.isControlDown() && e.getKeyCode() == 72) 
					  {
			    		  //CTRL+ H
							Crawler headadd=new Crawler("add", "head", 0);
			          }
			    	  if (e.isAltDown() && e.getKeyCode() == 72) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //ALT + H
			    			  Crawler headedit=new Crawler("edit", "head", (int)maintable.getValueAt(maintable.getSelectedRow(),0));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if (e.isControlDown() && e.getKeyCode() == 80) 
					  {
			    		  //CTRL+ p
							Crawler playeradd=new Crawler("add", "player", (int)maintable.getValueAt(maintable.getSelectedRow(),0));
			          }
			    	  if (e.isAltDown() && e.getKeyCode() == 80) 
					  {
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  //ALT + p
			    			  Crawler playeredit=new Crawler("edit", "player", (int)maintable.getValueAt(maintable.getSelectedRow(),0));
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
			    			  TradeInsight tradeinfo1=new TradeInsight("F1",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
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
			    			  TradeInsight tradeinfo1=new TradeInsight("F2",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
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
			    			  TradeInsight tradeinfo1=new TradeInsight("F3",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
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
			    			  TradeInsight tradeinfo1=new TradeInsight("F4",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
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
			    			  TradeInsight tradeinfo1=new TradeInsight("F5",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
			    	  if (e.isControlDown() && e.getKeyCode() == 67) 
					  {
			    		  //CTRL+ C
			    		  ScriptSearch search = new ScriptSearch(CommonObjects.objpresto);
			          }
			    	  if ( e.getKeyCode() == 112) 
					  {
			    		  //F1
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
			    			  Formulations objF1=new Formulations("F1",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
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
			    		  Formulations objF2=new Formulations("F2",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
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
			    		  Formulations objF3=new Formulations("F3",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
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
			    		  Formulations objF4=new Formulations("F4",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
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
			    		  Formulations objF5=new Formulations("F5",maintable.getValueAt(maintable.getSelectedRow(),1).toString(), maintable.getValueAt(maintable.getSelectedRow(),7).toString(), (int)(maintable.getValueAt(maintable.getSelectedRow(),0)));
				    	  }
				    	  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
				      }
			    	  if (e.isControlDown() && e.getKeyCode() == 68) 
					  {
			    		  //CTRL+ C
			    		  if (maintable.getSelectedRowCount() == 1)
				    	  {
				    		  int opcion = JOptionPane.showConfirmDialog(null, "Are you sure, Want to Delete Head ?\n It will get deleted from dashboard, head and formula.", "Delete Head", JOptionPane.YES_NO_OPTION);
							  if (opcion == 0) {
					    		  String stmts[] = new String[3];
					    		  stmts[0] = "DELETE FROM TBL_TRADE_LINE WHERE ID="+(int)(maintable.getValueAt(maintable.getSelectedRow(),0))+";"; 
					    		  stmts[1] = "DELETE FROM TBL_BEAST_VIEW WHERE ID="+(int)(maintable.getValueAt(maintable.getSelectedRow(),0))+";"; 
					    		  stmts[2] = "DELETE FROM TBL_FORMULA_DATA WHERE ID="+(int)(maintable.getValueAt(maintable.getSelectedRow(),0))+";"; 
					    		  dbobj.executeBatchStatement(h2con, stmts);
					    		  JOptionPane.showMessageDialog(frmBeastview, "Deleted Sucessfully !!", "INFO",JOptionPane.INFORMATION_MESSAGE);
							  }
				    	  }
			    		  else
				    	  {
				    		  JOptionPane.showMessageDialog(frmBeastview, "Kindly Select One Row...", "INFO",JOptionPane.INFORMATION_MESSAGE);
				    	  }
			          }
		    	 
		      }
		    });
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if (e.isControlDown() && e.getKeyCode() == 67) 
		  {
  		  //CTRL+ H
  		  ScriptSearch search = new ScriptSearch(CommonObjects.objpresto);
        }
		if (e.isControlDown() && e.getKeyCode() == 72) 
		{
  		  //CTRL+ H
				Crawler headadd=new Crawler("add", "head", 0);
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
