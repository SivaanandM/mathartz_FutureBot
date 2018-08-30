package com.beastbot.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.beastbot.presto.*;
import com.beastbot.common.CommonObjects;
import com.beastbot.common.DbFuncs;
import com.beastbot.list.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;

import org.h2.jdbcx.JdbcDataSource;

public class ScriptSearch {

	private JFrame frmScriptSearch;
	private JTextField textSymbol;
	private JTextField txtExpmm;
	private JTextField txtExpyyyy;
	private JTable table;
	private List<Scriptsdetail> records=null;
	//presto_commons objpresto;
	TableModel model;
	DbFuncs objdb;
	Connection h2con=null;
	private static int HEADER_HEIGHT = 25;
	String col[]= {"SEC-ID","SYMBOL","EXCHANGE","INSTRUMENTS","LOT-SIZE","TICK-SIZE","EXPIRY-DD","EXPIRY-MMMYY","OPT-TYPE","STRIKE"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScriptSearch window = new ScriptSearch(null);
					window.frmScriptSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScriptSearch(presto_commons obj) 
	{
		objdb = new DbFuncs();
		h2con=objdb.CheckandConnectDB(h2con);
		if (obj == null)
		{
			CommonObjects.objpresto = new presto_commons();
		}
		//else
		//{
			CommonObjects.objpresto = obj;
		//}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		renderControl();
		
	}
	public void toCSV(JTable table, String filename){
	    try{
	        TableModel model = table.getModel();
	        FileWriter excel = new FileWriter("C:\\Users\\findc06\\Desktop\\"+filename+".csv");

	        for(int i = 0; i < model.getColumnCount(); i++){
	            excel.write(model.getColumnName(i) + ",");
	        }

	        excel.write("\n");

	        for(int i=0; i< model.getRowCount(); i++) {
	            for(int j=0; j < model.getColumnCount(); j++) {
	                excel.write(model.getValueAt(i,j).toString()+",");
	            }
	            excel.write("\n");
	        }

	        excel.close();

	    }catch(IOException e){ System.out.println(e); }
	}
	
	private void renderControl()
	{
		try	
		{
			frmScriptSearch = new JFrame();
			frmScriptSearch.setVisible(true);
			frmScriptSearch.setTitle("Presto Contract Crawler");
			frmScriptSearch.getContentPane().setBackground(new Color(51,51,51));
			frmScriptSearch.getContentPane().setLayout(new BorderLayout(0, 0));
			frmScriptSearch.setBounds(100, 100, 945, 629);
			frmScriptSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmScriptSearch.setResizable(false);
			
			JLabel lblScriptsCrawler = new JLabel("Sec-Id Crawler");
			lblScriptsCrawler.setHorizontalAlignment(SwingConstants.CENTER);
			lblScriptsCrawler.setForeground(new Color(255, 220, 135));
			lblScriptsCrawler.setFont(new Font("Verdana", Font.PLAIN, 24));
			frmScriptSearch.getContentPane().add(lblScriptsCrawler, BorderLayout.NORTH);
			
			JPanel pnlmiddle = new JPanel();
			frmScriptSearch.getContentPane().add(pnlmiddle, BorderLayout.CENTER);
			pnlmiddle.setLayout(null);
			pnlmiddle.setBackground(new Color(51,51,51));
			
			JPanel pnlsearchcontrol = new JPanel();
			pnlsearchcontrol.setBounds(183, 11, 601, 144);
			pnlsearchcontrol.setBackground(new Color(80,75,78));
			pnlmiddle.add(pnlsearchcontrol);
			pnlsearchcontrol.setLayout(null);
			
			JLabel lblSymbol = new JLabel("Symbol");
			lblSymbol.setBounds(52, 11, 82, 49);
			lblSymbol.setHorizontalAlignment(SwingConstants.LEFT);
			lblSymbol.setForeground(Color.WHITE);
			lblSymbol.setFont(new Font("Verdana", Font.PLAIN, 14));
			pnlsearchcontrol.add(lblSymbol);
			
			textSymbol = new JTextField();
			textSymbol.setBounds(116, 16, 171, 35);
			textSymbol.setHorizontalAlignment(SwingConstants.LEFT);
			textSymbol.setForeground(new Color(255, 220, 135));
			textSymbol.setFont(new Font("Verdana", Font.PLAIN, 20));
			textSymbol.setColumns(10);
			textSymbol.setCaretColor(Color.WHITE);
			textSymbol.setBackground(new Color(36, 34, 29));
			textSymbol.addKeyListener(new KeyAdapter() {

				  public void keyTyped(KeyEvent e) {
				    char keyChar = e.getKeyChar();
				    if (Character.isLowerCase(keyChar)) {
				      e.setKeyChar(Character.toUpperCase(keyChar));
				    }
				  }

				});
			pnlsearchcontrol.add(textSymbol);
			
			JLabel lblMmmyy = new JLabel("MMM/YY");
			lblMmmyy.setBounds(311, 11, 82, 49);
			lblMmmyy.setHorizontalAlignment(SwingConstants.LEFT);
			lblMmmyy.setForeground(Color.WHITE);
			lblMmmyy.setFont(new Font("Verdana", Font.PLAIN, 14));
			pnlsearchcontrol.add(lblMmmyy);
			
			txtExpmm = new JTextField();
			txtExpmm.setBounds(383, 16, 93, 35);
			pnlsearchcontrol.add(txtExpmm);
			txtExpmm.setText("MMM");
			txtExpmm.setPreferredSize(new Dimension(60, 50));
			txtExpmm.setHorizontalAlignment(SwingConstants.CENTER);
			txtExpmm.setForeground(new Color(255, 220, 135));
			txtExpmm.setColumns(4);
			txtExpmm.setCaretColor(Color.WHITE);
			txtExpmm.setBackground(new Color(36, 34, 29));
			txtExpmm.addKeyListener(new KeyAdapter() {

				  public void keyTyped(KeyEvent e) {
				    char keyChar = e.getKeyChar();
				    if (Character.isLowerCase(keyChar)) {
				      e.setKeyChar(Character.toUpperCase(keyChar));
				    }
				  }

				});
			
			txtExpyyyy = new JTextField();
			txtExpyyyy.setBounds(486, 16, 64, 35);
			pnlsearchcontrol.add(txtExpyyyy);
			txtExpyyyy.setText("YY");
			txtExpyyyy.setPreferredSize(new Dimension(80, 50));
			txtExpyyyy.setHorizontalAlignment(SwingConstants.CENTER);
			txtExpyyyy.setForeground(new Color(255, 220, 135));
			txtExpyyyy.setColumns(2);
			txtExpyyyy.setCaretColor(Color.WHITE);
			txtExpyyyy.setBackground(new Color(36, 34, 29));
			DateFormat df = new SimpleDateFormat("MMM-yy"); // Just the year, with 2 digits
			String formattedDate = df.format(Calendar.getInstance().getTime());
			
			txtExpmm.setText(formattedDate.split("-")[0].toUpperCase());
			txtExpyyyy.setText(formattedDate.split("-")[1]);
			
			JButton btnGetScriptList = new JButton("Get List");
			btnGetScriptList.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnGetScriptList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					records = CommonObjects.objpresto.getMatchedScripts(textSymbol.getText(), txtExpmm.getText()+txtExpyyyy.getText());
					String [] batchstmt = new String[records.size()];
					if ((records != null) && (records.size() > 0))
					{
						objdb.executeNonQuery(h2con, "DELETE FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+textSymbol.getText()+"' and INSTRUMENT='FUTIDX';");
						objdb.executeNonQuery(h2con, "DELETE FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+textSymbol.getText()+"' and EXPMONTHYEAR='"+txtExpmm.getText()+txtExpyyyy.getText()+"';");
						
						String [][] values = new String[records.size()][10];
						for(int i=0; i < records.size(); i++)
						{
							
							values[i][0] = records.get(i).getSecid();
							values[i][1] = records.get(i).getSymbol();
							values[i][2] = records.get(i).getExchange();
							values[i][3] = records.get(i).getInstrument();
							values[i][4] = records.get(i).getLotsize();
							values[i][5] = records.get(i).getTicksize();
							values[i][6] = records.get(i).getExpdd();
							values[i][7] = records.get(i).getExpmonthyear();
							values[i][8] = records.get(i).getOpttype();
							values[i][9] = records.get(i).getStrike();
							
							batchstmt[i] = "INSERT INTO TBL_MASTER_CONTRACTS (SECID, SYMBOL, EXCHANGE, INSTRUMENT, LOTSIZE, TICKSIZE, EXPDD, EXPMONTHYEAR, OPTTYPE, STRIKE) "
									+ "VALUES ('"+values[i][0]+"','"+values[i][1]+"','"+values[i][2]+"','"+values[i][3]+"','"+values[i][4]+"'"
											+ ",'"+values[i][5]+"','"+values[i][6]+"','"+values[i][7]+"','"+values[i][8]+"','"+values[i][9]+"');";
							
						}
						int insertcount = objdb.executeBatchStatement(h2con, batchstmt);
						model = new DefaultTableModel(values, col);
						table.setModel(model);	
						JOptionPane.showMessageDialog(frmScriptSearch,
								(values.length)+"Sec Id found & inserted for "+ textSymbol.getText() + " In Contract Month "+txtExpmm.getText()+txtExpyyyy.getText()+
								" \n CSV genrated in Destop as "+textSymbol.getText()+".csv", "INFO",JOptionPane.INFORMATION_MESSAGE);
						if (values.length > 0)
						{
							toCSV(table,textSymbol.getText());
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frmScriptSearch, "No Matched Sec Id Found for given symbol and contract month, \n Kindly provide valid FUTIDX & OPTIDX symbols", "INFO",JOptionPane.INFORMATION_MESSAGE);
						
					}
				}
			});
			btnGetScriptList.setBounds(52, 84, 235, 35);
			pnlsearchcontrol.add(btnGetScriptList);
			
			JButton btnReset = new JButton("Reset");
			btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textSymbol.setText("");
					model = new DefaultTableModel(col,0);
					table.setModel(model);
				}
			});
			btnReset.setBounds(311, 84, 240, 35);
			pnlsearchcontrol.add(btnReset);
			
			
			table = new JTable();
			table.setBounds(10, 559, 923, -345);
			table.setBackground(new Color(51, 51, 51));
			table.setFillsViewportHeight(true);
			table.setFont(new Font("Tahoma", Font.PLAIN, 15));
			table.setRowHeight(23);
			model = new DefaultTableModel(col,0);
			table = new JTable(model){
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
			
			JTableHeader header = table.getTableHeader();
			header.setForeground(new Color(255, 220, 135));
			header.setBackground(new Color(51, 51, 51));
		    header.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    header.setPreferredSize(new Dimension(100, HEADER_HEIGHT));
		    JScrollPane scrollPane = new JScrollPane(table);
		    scrollPane.setBounds(22, 185, 897, 367);
		    pnlmiddle.add(scrollPane);
		    scrollPane.setEnabled(false);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setViewportBorder(null);
			scrollPane.getViewport().setBackground(new Color(51, 51, 51));
			
			
			JSeparator separator = new JSeparator();
			separator.setBounds(22, 166, 897, 20);
			pnlmiddle.add(separator);
			
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		
		}
		
	}
}
