package com.beastbot.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class TradeInsight {

	private JFrame frmtradeinsight;
	String col[]= {"ORDER TYPE","TIME","PRICE"};
	private JTable tblinsights;
	TableModel modelinsights;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TradeInsight window = new TradeInsight();
					window.frmtradeinsight.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TradeInsight() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmtradeinsight = new JFrame();
		frmtradeinsight.setTitle("Trade Insight");
		frmtradeinsight.setBounds(100, 100, 444, 491);
		frmtradeinsight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmtradeinsight.getContentPane().setBackground(new Color(51,51,51));
		
		tblinsights = new JTable();
		tblinsights.setBounds(10, 559, 923, -345);
		tblinsights.setBackground(new Color(51, 51, 51));
		tblinsights.setFillsViewportHeight(true);
		tblinsights.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblinsights.setRowHeight(23);
		modelinsights = new DefaultTableModel(col,0);
		tblinsights = new JTable(modelinsights){
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
		
		
		JTableHeader header = tblinsights.getTableHeader();
		header.setForeground(new Color(36,34,29));
	    header.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    frmtradeinsight.getContentPane().setLayout(null);
	    JScrollPane scrollPane = new JScrollPane(tblinsights);
	    scrollPane.setBounds(10, 84, 408, 356);
	    frmtradeinsight.getContentPane().add(scrollPane);
	    scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBounds(211, 11, 10, 65);
		frmtradeinsight.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("HEAD");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 191, 18);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		frmtradeinsight.getContentPane().add(lblNewLabel);
		
		JLabel lblPlayer = new JLabel("PLAYER");
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblPlayer.setBounds(227, 11, 191, 18);
		frmtradeinsight.getContentPane().add(lblPlayer);
		
		JLabel lblhead = new JLabel("?");
		lblhead.setHorizontalAlignment(SwingConstants.CENTER);
		lblhead.setForeground(new Color(255, 220, 135));
		lblhead.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblhead.setBounds(10, 43, 191, 18);
		frmtradeinsight.getContentPane().add(lblhead);
		
		JLabel lblplayer = new JLabel("?");
		lblplayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblplayer.setForeground(new Color(255, 220, 135));
		lblplayer.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblplayer.setBounds(231, 47, 191, 18);
		frmtradeinsight.getContentPane().add(lblplayer);

	}
}
