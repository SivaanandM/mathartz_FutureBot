package com.beastbot.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class Crawler {

	private JFrame frmcrawler;
	private JTextField txtsymbol;
	private JTextField txtxmonth;
	private JTextField txtstrike;
	private JTextField txtxdd;
	private JTextField txtmm;
	private JTextField txtyy;
	JPanel pnlopt, pnlfut;
	private String operation, side;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crawler window = new Crawler("new","head",0);
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
	public Crawler(String opera, String forside, int identity) 
	{
		operation = opera;
		side = forside;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmcrawler = new JFrame();
		frmcrawler.setTitle("SCRIPTS");
		frmcrawler.setBounds(100, 100, 375, 435);
		frmcrawler.getContentPane().setLayout(null);
		frmcrawler.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmcrawler.getContentPane().setBackground(new Color(51, 51, 51));
		frmcrawler.setVisible(true);
		
		JPanel innerpanel = new JPanel();
		innerpanel.setBounds(10, 53, 338, 329);
		innerpanel.setBackground(new Color(80,75,78));
		frmcrawler.getContentPane().add(innerpanel);
		innerpanel.setLayout(null);
		
		JLabel label = new JLabel("INST-TYPE");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Verdana", Font.PLAIN, 18));
		label.setBounds(31, 10, 116, 49);
		innerpanel.add(label);
		
		JComboBox<String> cmbinstrument = new JComboBox<String>();
		cmbinstrument.setModel(new DefaultComboBoxModel(new String[] {"FUT", "OPT"}));
		cmbinstrument.setFont(new Font("Verdana", Font.PLAIN, 18));
		cmbinstrument.setBounds(149, 19, 156, 31);
		innerpanel.add(cmbinstrument);
		cmbinstrument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbinstrument.getSelectedItem() == "OPT")
				{
					pnlopt.setVisible(true);
					pnlfut.setVisible(false);
				}
				else
				{
					pnlopt.setVisible(false);
					pnlfut.setVisible(true);
				}
			}
		});
		
		JLabel lblSymbol = new JLabel("SYMBOL");
		lblSymbol.setHorizontalAlignment(SwingConstants.LEFT);
		lblSymbol.setForeground(Color.WHITE);
		lblSymbol.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSymbol.setBounds(31, 59, 82, 49);
		innerpanel.add(lblSymbol);
		
		txtsymbol = new JTextField();
		txtsymbol.setHorizontalAlignment(SwingConstants.LEFT);
		txtsymbol.setForeground(new Color(255, 220, 135));
		txtsymbol.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtsymbol.setColumns(10);
		txtsymbol.setCaretColor(Color.WHITE);
		txtsymbol.setBackground(new Color(36, 34, 29));
		txtsymbol.setBounds(149, 61, 156, 42);
		innerpanel.add(txtsymbol);
		
		pnlfut = new JPanel();
		pnlfut.setBackground(new Color(80,75,78));
		pnlfut.setBorder(null);
		
		pnlfut.setBounds(20, 106, 297, 57);
		pnlfut.setVisible(false);
		innerpanel.add(pnlfut);
		pnlfut.setLayout(null);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(10, 5, 97, 36);
		lblMonth.setHorizontalAlignment(SwingConstants.LEFT);
		lblMonth.setForeground(Color.WHITE);
		lblMonth.setFont(new Font("Verdana", Font.PLAIN, 16));
		pnlfut.add(lblMonth);
		
		txtxmonth = new JTextField();
		txtxmonth.setBounds(129, 6, 158, 43);
		txtxmonth.setHorizontalAlignment(SwingConstants.LEFT);
		txtxmonth.setForeground(new Color(255, 220, 135));
		txtxmonth.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtxmonth.setColumns(10);
		txtxmonth.setCaretColor(Color.WHITE);
		txtxmonth.setBackground(new Color(36, 34, 29));
		pnlfut.add(txtxmonth);
		
		pnlopt = new JPanel();
		pnlopt.setBounds(20, 106, 297, 160);
		pnlopt.setBackground(new Color(80,75,78));
		pnlopt.setVisible(false);
		pnlopt.setBorder(null);
		innerpanel.add(pnlopt);
		pnlopt.setLayout(null);
		
		JLabel label_2 = new JLabel("INST-TYPE");
		label_2.setBounds(10, 9, 96, 23);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		pnlopt.add(label_2);
		
		JComboBox<String> cmbright = new JComboBox<String>();
		cmbright.setModel(new DefaultComboBoxModel(new String[] {"CE", "PE"}));
		cmbright.setBounds(128, 6, 155, 29);
		cmbright.setFont(new Font("Verdana", Font.PLAIN, 18));
		pnlopt.add(cmbright);
		
		JLabel lblStrike = new JLabel("STRIKE");
		lblStrike.setBounds(10, 59, 67, 21);
		lblStrike.setHorizontalAlignment(SwingConstants.LEFT);
		lblStrike.setForeground(Color.WHITE);
		lblStrike.setFont(new Font("Verdana", Font.PLAIN, 16));
		pnlopt.add(lblStrike);
		
		txtstrike = new JTextField();
		txtstrike.setBounds(128, 46, 155, 44);
		txtstrike.setHorizontalAlignment(SwingConstants.LEFT);
		txtstrike.setForeground(new Color(255, 220, 135));
		txtstrike.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtstrike.setColumns(10);
		txtstrike.setCaretColor(Color.WHITE);
		txtstrike.setBackground(new Color(36, 34, 29));
		pnlopt.add(txtstrike);
		
		JLabel lblDate = new JLabel("DATE");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblDate.setBounds(10, 114, 108, 21);
		pnlopt.add(lblDate);
		
		txtxdd = new JTextField();
		txtxdd.setHorizontalAlignment(SwingConstants.LEFT);
		txtxdd.setForeground(new Color(255, 220, 135));
		txtxdd.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtxdd.setColumns(10);
		txtxdd.setCaretColor(Color.WHITE);
		txtxdd.setBackground(new Color(36, 34, 29));
		txtxdd.setBounds(128, 101, 50, 44);
		pnlopt.add(txtxdd);
		
		txtmm = new JTextField();
		txtmm.setHorizontalAlignment(SwingConstants.LEFT);
		txtmm.setForeground(new Color(255, 220, 135));
		txtmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtmm.setColumns(10);
		txtmm.setCaretColor(Color.WHITE);
		txtmm.setBackground(new Color(36, 34, 29));
		txtmm.setBounds(180, 101, 50, 44);
		pnlopt.add(txtmm);
		
		txtyy = new JTextField();
		txtyy.setHorizontalAlignment(SwingConstants.LEFT);
		txtyy.setForeground(new Color(255, 220, 135));
		txtyy.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtyy.setColumns(10);
		txtyy.setCaretColor(Color.WHITE);
		txtyy.setBackground(new Color(36, 34, 29));
		txtyy.setBounds(233, 101, 50, 44);
		pnlopt.add(txtyy);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setBounds(20, 280, 297, 38);
		innerpanel.add(btnSave);
		
		JLabel label_3 = new JLabel("?");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(new Color(255, 220, 135));
		label_3.setFont(new Font("Verdana", Font.BOLD, 18));
		label_3.setBounds(0, 11, 349, 43);
		frmcrawler.getContentPane().add(label_3);
		
	}
}
