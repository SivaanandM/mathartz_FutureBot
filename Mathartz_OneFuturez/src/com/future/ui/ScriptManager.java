package com.future.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import com.future.collection.Scriptsdetail;
import com.future.collection.SquadScripts;
import com.future.commons.CommonObjects;
import com.future.commons.DbFuncs;

import javax.swing.JButton;

public class ScriptManager {

	private JFrame frmcrawler;
	private JTextField txtmarker ,  lblhead;
	private JTextField lblstrikevalue;
	private String operation, side;
	private int seedid;
	JComboBox<String> cmbright;
	DbFuncs dbobj;
	String centraldate, centralstrike;
	Connection h2con=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScriptManager window = new ScriptManager("EDIT",1);
					window.frmcrawler.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScriptManager(String opera, int identity) 
	{
		operation = opera;
		seedid = identity;
		dbobj = new DbFuncs();
		h2con=dbobj.CheckandConnectDB(h2con);
		centraldate = dbobj.loadCentralizedDate(h2con);
		centralstrike = dbobj.loadCentralizedStrike(h2con);
		initialize();
		frmcrawler.setVisible(true);
		if (operation.toUpperCase().equals("EDIT"))
		{
			SquadScripts ss = dbobj.getSquadScriptData(h2con, "SELECT * FROM TBL_Trade_Line WHERE ID="+seedid+";").get(0);
			cmbright.setSelectedItem(ss.getOpttype().toUpperCase());
			txtmarker.setText(ss.getMarker());
			lblstrikevalue.setText(ss.getStrike());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmcrawler = new JFrame();
		frmcrawler.setBackground(Color.BLACK);
		frmcrawler.setTitle("Squad Manager");
		frmcrawler.setBounds(100, 100, 363, 405);
		frmcrawler.getContentPane().setLayout(null);
		frmcrawler.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmcrawler.getContentPane().setBackground(Color.BLACK);
		frmcrawler.setVisible(true);
		frmcrawler.setLocationRelativeTo(null);
		
		JPanel innerpanel = new JPanel();
		innerpanel.setBounds(10, 48, 327, 305);
		innerpanel.setBackground(new Color(33,33,33));
		frmcrawler.getContentPane().add(innerpanel);
		innerpanel.setLayout(null);
		
		JLabel lblSymbol = new JLabel("SYMBOL");
		lblSymbol.setHorizontalAlignment(SwingConstants.LEFT);
		lblSymbol.setForeground(Color.WHITE);
		lblSymbol.setFont(new Font("Ebrima", Font.PLAIN, 16));
		lblSymbol.setBounds(35, 23, 82, 23);
		innerpanel.add(lblSymbol);
		
		JButton btnSave = new JButton("LOAD");
		btnSave.setFont(new Font("Ebrima", Font.BOLD, 14));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (operation.toUpperCase().equals("ADD"))
				{
					HeadAdd();
				}
				else if (operation.toUpperCase().equals("EDIT"))
				{
					HeadEdit(seedid);
				}
				frmcrawler.dispose();
			}
		});
		btnSave.setBounds(35, 244, 255, 38);
		innerpanel.add(btnSave);
		
		JLabel lblOpttype = new JLabel("OPT-TYPE");
		lblOpttype.setBounds(35, 72, 96, 23);
		innerpanel.add(lblOpttype);
		lblOpttype.setHorizontalAlignment(SwingConstants.LEFT);
		lblOpttype.setForeground(Color.WHITE);
		lblOpttype.setFont(new Font("Ebrima", Font.PLAIN, 16));
		
		cmbright = new JComboBox<String>();
		cmbright.setBounds(135, 68, 155, 29);
		innerpanel.add(cmbright);
		cmbright.setModel(new DefaultComboBoxModel(new String[] {"CE", "PE"}));
		cmbright.setFont(new Font("Ebrima", Font.PLAIN, 18));
		
		JLabel lblmarker = new JLabel("MARKER");
		lblmarker.setBounds(35, 131, 67, 21);
		innerpanel.add(lblmarker);
		lblmarker.setHorizontalAlignment(SwingConstants.LEFT);
		lblmarker.setForeground(Color.WHITE);
		lblmarker.setFont(new Font("Ebrima", Font.PLAIN, 16));
		
		txtmarker = new JTextField("0");
		txtmarker.setBounds(135, 119, 155, 44);
		innerpanel.add(txtmarker);
		txtmarker.setHorizontalAlignment(SwingConstants.CENTER);
		txtmarker.setForeground(new Color(255, 220, 135));
		txtmarker.setFont(new Font("Ebrima", Font.PLAIN, 20));
		txtmarker.setColumns(10);
		txtmarker.setCaretColor(Color.WHITE);
		txtmarker.setBackground(Color.BLACK);
		
		
		JLabel lblStrike_1 = new JLabel("STRIKE");
		lblStrike_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblStrike_1.setForeground(Color.WHITE);
		lblStrike_1.setFont(new Font("Ebrima", Font.PLAIN, 16));
		lblStrike_1.setBounds(35, 196, 67, 21);
		innerpanel.add(lblStrike_1);
		
		lblhead = new JTextField(CommonObjects.GlobalSoftwarefor);
		lblhead.setEditable(false);
		lblhead.setBackground(Color.BLACK);
		lblhead.setHorizontalAlignment(SwingConstants.CENTER);
		lblhead.setForeground(new Color(255, 220, 135));
		lblhead.setFont(new Font("Ebrima", Font.PLAIN, 20));
		lblhead.setBounds(136, 11, 154, 38);
		innerpanel.add(lblhead);
		
		lblstrikevalue = new JTextField(centralstrike);
		lblstrikevalue.setEditable(false);
		lblstrikevalue.setBackground(Color.BLACK);
		lblstrikevalue.setHorizontalAlignment(SwingConstants.CENTER);
		lblstrikevalue.setForeground(new Color(255, 220, 135));
		lblstrikevalue.setFont(new Font("Ebrima", Font.PLAIN, 20));
		lblstrikevalue.setBounds(136, 183, 154, 44);
		innerpanel.add(lblstrikevalue);
		
		txtmarker.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				try
				{
			        JTextField textField = (JTextField) e.getSource();
			        String text = textField.getText();
			        if (text.equalsIgnoreCase("") || text.equalsIgnoreCase("-"))
			        {
			        	text = "0";
			        }
			        lblstrikevalue.setText(String.valueOf(Integer.valueOf(centralstrike) + Integer.valueOf(text)));
				}
				catch(Exception ex)
				{
					
				}
		      }
			String temp= "";
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') || (c == '-')||(c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_DELETE) ))
		      {
		        e.consume();
		        
		      }
		    }
		  });
		
		JLabel lblcaption = new JLabel(operation.toUpperCase());
		lblcaption.setHorizontalAlignment(SwingConstants.CENTER);
		lblcaption.setForeground(new Color(255, 220, 135));
		lblcaption.setFont(new Font("Ebrima", Font.BOLD, 16));
		lblcaption.setBounds(0, 0, 347, 42);
		frmcrawler.getContentPane().add(lblcaption);
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
	    Action escapeAction = new AbstractAction() {
	      public void actionPerformed(ActionEvent e) {
	    	  frmcrawler.dispose();
	      }
	    };
	    frmcrawler.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	        escapeKeyStroke, "ESCAPE");
	    frmcrawler.getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}
	
	public void HeadAdd()
	{
		try
		{
			List<Scriptsdetail> headdata = null;
			headdata = dbobj.getContractdata(h2con, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+lblhead.getText()+"' AND OPTTYPE='"+cmbright.getSelectedItem().toString()+"' AND STRIKE='"+(Integer.valueOf(lblstrikevalue.getText()))+"' AND EXPDD='"+centraldate.split("-")[0]+"' AND EXPMONTHYEAR='"+centraldate.split("-")[1]+centraldate.split("-")[2]+"';");
			if (headdata != null)
			{
				int id = dbobj.getIdentityforInsert(h2con, "INSERT INTO TBL_TRADE_LINE (HEADID, HEADDISPLAY," + 
						"HEADSYMBOL, EXCHANGE, INSTRUMENT ,LOTSIZE, TICKSIZE, EXPDD,EXPMONTHYEAR, OPTTYPE , STRIKE, MARKER) VALUES ('"+headdata.get(0).getSecid()+"','"+headdata.get(0).getSymbol()+"','"+headdata.get(0).getSymbol()+"','"+headdata.get(0).getExchange()+"','"+headdata.get(0).getInstrument()+"','"+headdata.get(0).getLotsize()+"'"
								+ ", '"+headdata.get(0).getTicksize()+"','"+headdata.get(0).getExpdd()+"','"+headdata.get(0).getExpmonthyear()+"','"+headdata.get(0).getOpttype()+"','"+headdata.get(0).getStrike()+"','"+txtmarker.getText()+"');");
				dbobj.executeNonQuery(h2con, "INSERT INTO TBL_BEAST_VIEW (ID,RIGHT,STRIKE) VALUES ("+id+",'"+headdata.get(0).getOpttype()+"','"+headdata.get(0).getStrike()+"');");
			}
			else
			{
				//give warning message box
				JOptionPane.showMessageDialog(frmcrawler,"No Record Found for given search from Crawler DB, \n Kindly Refresh from Presto Contracts Crawler !! ", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void HeadEdit(int id)
	{
		try
		{
			
			List<Scriptsdetail> headdata = null;
			headdata = dbobj.getContractdata(h2con, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+lblhead.getText()+"' AND OPTTYPE='"+cmbright.getSelectedItem().toString()+"' AND STRIKE='"+(Integer.valueOf(lblstrikevalue.getText()))+"' AND EXPDD='"+centraldate.split("-")[0]+"' AND EXPMONTHYEAR='"+centraldate.split("-")[1]+centraldate.split("-")[2]+"';");
			if (headdata != null)
			{
				dbobj.getIdentityforInsert(h2con, "UPDATE TBL_TRADE_LINE SET HEADID='"+headdata.get(0).getSecid()+"', HEADDISPLAY='"+headdata.get(0).getSymbol()+"'," + 
						"HEADSYMBOL='"+headdata.get(0).getSymbol()+"', EXCHANGE='"+headdata.get(0).getExchange()+"', INSTRUMENT='"+headdata.get(0).getInstrument()+"' ,LOTSIZE='"+headdata.get(0).getLotsize()+"',"
								+ " TICKSIZE='"+headdata.get(0).getTicksize()+"', EXPDD='"+headdata.get(0).getExpdd()+"',EXPMONTHYEAR='"+headdata.get(0).getExpmonthyear()+"', OPTTYPE='"+headdata.get(0).getOpttype()+"' , STRIKE='"+headdata.get(0).getStrike()+"' ,MARKER='"+txtmarker.getText()+"' WHERE ID="+id+";");
				dbobj.executeNonQuery(h2con, "DELETE FROM TBL_BEAST_VIEW WHERE ID="+id+";");
				dbobj.executeNonQuery(h2con, "INSERT INTO TBL_BEAST_VIEW (ID,RIGHT,STRIKE) VALUES ("+id+",'"+headdata.get(0).getOpttype()+"','"+headdata.get(0).getStrike()+"');");
			
			}
			else
			{
				//give warning message box
				JOptionPane.showMessageDialog(frmcrawler,"No Record Found for given search from Crawler DB, \n Kindly Refresh from Presto Contracts Crawler !! ", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	
}
