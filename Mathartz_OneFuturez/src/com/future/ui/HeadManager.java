package com.future.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.future.collection.Scriptsdetail;
import com.future.commons.CommonObjects;
import com.future.commons.DbFuncs;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;

public class HeadManager {

	private JFrame frmHeadManager;
	private JTextField txthead;
	private JTextField textexpmmm;
	private JTextField txtexpyy;
	DbFuncs dbobj;
	Connection h2con=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeadManager window = new HeadManager();
					window.frmHeadManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HeadManager() {
		dbobj = new DbFuncs();
		h2con=dbobj.CheckandConnectDB(h2con);
		initialize();
		frmHeadManager.setVisible(true);
		String strcentralheaddate =dbobj.loadCentralizedHeaddate(h2con);
		if (strcentralheaddate != null)
		{
			textexpmmm.setText(strcentralheaddate.substring(0, 3));
			txtexpyy.setText(strcentralheaddate.substring(3, 5));
		}
		else
		{
			textexpmmm.setText((new SimpleDateFormat("MMM").format(new Date(System.currentTimeMillis()))).toUpperCase());
			txtexpyy.setText((new SimpleDateFormat("YY").format(new Date(System.currentTimeMillis()))));
			
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHeadManager = new JFrame();
		frmHeadManager.setTitle("Head Manager");
		frmHeadManager.getContentPane().setBackground(Color.BLACK);
		frmHeadManager.getContentPane().setLayout(null);
		
		JPanel mainpnl = new JPanel();
		mainpnl.setBounds(10, 45, 301, 206);
		mainpnl.setBackground(new Color(33,33,33));
		frmHeadManager.getContentPane().add(mainpnl);
		mainpnl.setLayout(null);
		
		JLabel label = new JLabel("SYMBOL");
		label.setBounds(25, 20, 61, 23);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Ebrima", Font.PLAIN, 16));
		mainpnl.add(label);
		
		txthead = new JTextField(CommonObjects.GlobalSoftwarefor);//CommonObjects.GlobalSoftwarefor
		txthead.setEditable(false);
		txthead.setBackground(Color.BLACK);
		txthead.setHorizontalAlignment(SwingConstants.CENTER);
		txthead.setForeground(new Color(255, 220, 135));
		txthead.setFont(new Font("Ebrima", Font.PLAIN, 20));
		txthead.setBounds(125, 11, 154, 38);
		mainpnl.add(txthead);
		
		JLabel lblExpmmm = new JLabel("EXP DATE");
		lblExpmmm.setHorizontalAlignment(SwingConstants.LEFT);
		lblExpmmm.setForeground(Color.WHITE);
		lblExpmmm.setFont(new Font("Ebrima", Font.PLAIN, 16));
		lblExpmmm.setBounds(25, 84, 84, 23);
		mainpnl.add(lblExpmmm);
		
		textexpmmm = new JTextField((String) null);
		textexpmmm.setHorizontalAlignment(SwingConstants.CENTER);
		textexpmmm.setForeground(new Color(255, 220, 135));
		textexpmmm.setFont(new Font("Ebrima", Font.PLAIN, 20));
		textexpmmm.setBackground(Color.BLACK);
		textexpmmm.setBounds(125, 75, 96, 38);
		textexpmmm.addKeyListener(new KeyAdapter() {

			  public void keyTyped(KeyEvent e) {
			    char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			  }

			});
		mainpnl.add(textexpmmm);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SetHead();
				frmHeadManager.dispose();
			}
		});
		btnSave.setFont(new Font("Ebrima", Font.BOLD, 14));
		btnSave.setBounds(25, 139, 255, 38);
		mainpnl.add(btnSave);
		
		txtexpyy = new JTextField((String) null);
		txtexpyy.setHorizontalAlignment(SwingConstants.CENTER);
		txtexpyy.setForeground(new Color(255, 220, 135));
		txtexpyy.setFont(new Font("Ebrima", Font.PLAIN, 20));
		txtexpyy.setBackground(Color.BLACK);
		txtexpyy.setBounds(231, 75, 48, 38);
		mainpnl.add(txtexpyy);
		
		JLabel lblHead = new JLabel("HEAD");
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblHead.setForeground(new Color(255, 220, 135));
		lblHead.setFont(new Font("Ebrima", Font.BOLD, 16));
		lblHead.setBounds(0, 0, 321, 42);
		frmHeadManager.getContentPane().add(lblHead);

		frmHeadManager.setBounds(100, 100, 337, 300);
		frmHeadManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
	    Action escapeAction = new AbstractAction() {
	      public void actionPerformed(ActionEvent e) {
	    	  frmHeadManager.dispose();
	      }
	    };
	    frmHeadManager.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	        escapeKeyStroke, "ESCAPE");
	    frmHeadManager.getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}
	

	public void SetHead()
	{
		try
		{
			List<Scriptsdetail> headdata = null;
			headdata = dbobj.getContractdata(h2con, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+txthead.getText()+"'AND INSTRUMENT ='FUTIDX' AND EXPMONTHYEAR='"+textexpmmm.getText()+txtexpyy.getText()+"';");
			if (headdata != null)
			{
				dbobj.getIdentityforInsert(h2con, "UPDATE TBL_CENTRAL SET SECID='"+headdata.get(0).getSecid()+"', SYMBOL='"+headdata.get(0).getSymbol()+"' WHERE ID=1;");
			}
			else
			{
				//give warning message box
				JOptionPane.showMessageDialog(frmHeadManager,"No Record Found for given search from Crawler DB, \n Kindly Refresh from Presto Contracts Crawler !! ", "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	
}
