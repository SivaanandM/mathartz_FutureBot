package com.beastbot.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BeastView {

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
	public BeastView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Main frmBeastView Layout Design
		frmBeastview = new JFrame();
		frmBeastview.setTitle("Mathartz Trade Board");
		frmBeastview.setBackground(new Color(36,34,29));
		frmBeastview.getContentPane().setBackground(new Color(51, 51, 51));
		frmBeastview.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlhead = new JPanel();
		pnlhead.setBackground(new Color(51, 51, 51));
		frmBeastview.getContentPane().add(pnlhead, BorderLayout.NORTH);
		pnlhead.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblhead = new JLabel("Mathart'z View");
		
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
		
		
		
		
		txtdd = new JTextField();
		txtdd.setBounds(0, 0, 0, 0);
		txtdd.setHorizontalAlignment(SwingConstants.CENTER);
		txtdd.setForeground(new Color(255, 220, 135));
		txtdd.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtdd.setColumns(2);
		txtdd.setCaretColor(Color.WHITE);
		txtdd.setBackground(new Color(80,75,78));
		
		txtmmm = new JTextField();
		txtmmm.setBounds(0, 0, 0, 0);
		txtmmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtmmm.setForeground(new Color(255, 220, 135));
		txtmmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtmmm.setColumns(3);
		txtmmm.setCaretColor(Color.WHITE);
		txtmmm.setBackground(new Color(80,75,78));
		
		txtyy = new JTextField();
		txtyy.setText("18");
		txtyy.setBounds(0, 0, 0, 0);
		txtyy.setHorizontalAlignment(SwingConstants.CENTER);
		txtyy.setForeground(new Color(255, 220, 135));
		txtyy.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtyy.setColumns(2);
		txtyy.setCaretColor(Color.WHITE);
		txtyy.setBackground(new Color(80,75,78));
		
		JPanel pnldowncenter = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnldowncenter.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnldowncenter.setBackground(new Color(51, 51, 51));
		//float s =(((int) width) - ((int) width)/9);
		pnldowncenter.setPreferredSize(new Dimension((((int) width) - ((int) width)/3), 60));
		pnldown.add(pnldowncenter,BorderLayout.CENTER);
		
		
		btnrun = new JButton("RUN");
		btnrun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnrun.setPreferredSize(new Dimension(150, 35));
		btnrun.setBounds(531, 937, 150, 34);
		pnldowncenter.add(btnrun);
		
		btndcsv = new JButton("D-CSV");
		btndcsv.setPreferredSize(new Dimension(150, 35));
		btndcsv.setBounds(687, 936, 150, 35);
		pnldowncenter.add(btndcsv);
		
		btnclear = new JButton("CLEAR");
		btnclear.setPreferredSize(new Dimension(150, 35));
		btnclear.setBounds(999, 936, 150, 35);
		pnldowncenter.add(btnclear);
		
		btnstop = new JButton("STOP");
		btnstop.setPreferredSize(new Dimension(150, 35));
		btnstop.setBounds(843, 936, 150, 35);
		pnldowncenter.add(btnstop);
		
		
		
		
		GroupLayout gl_pnldown = new GroupLayout(pnldownleft);
		gl_pnldown.setHorizontalGroup(
			gl_pnldown.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnldown.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtmmm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtyy, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_pnldown.setVerticalGroup(
			gl_pnldown.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_pnldown.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnldown.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtdd, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtmmm, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtyy, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		pnldownleft.setLayout(gl_pnldown);
		
		
		
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
	}

}
