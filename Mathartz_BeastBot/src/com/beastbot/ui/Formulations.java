package com.beastbot.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JCheckBox;


public class Formulations {

	private JFrame paneformula;
	private JTextField txtx;
	private JTextField txty;
	private JTextField txtsthh;
	private JTextField txtmthh;
	private JTextField txtethh;
	private JTextField txtstmm;
	private JTextField txtmtmm;
	private JTextField txtetmm;
	private JTextField txtlcount;
	private JTextField txtround;
	private JTextField txtqty;
	JButton btnDelete;
	JButton btnsave;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulations window = new Formulations();
					window.paneformula.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Formulations() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Formula Pane Format
		InputHandler keyhand = new InputHandler();
		paneformula = new JFrame();
		paneformula.getContentPane().setBackground(new Color(51, 51, 51));
		paneformula.setVisible(true);
		paneformula.setTitle("Formula Inputs");
		paneformula.setBounds(100, 100, 305, 675);
		paneformula.setBackground(new Color(36,34,29));
		paneformula.getContentPane().setLayout(null);
		paneformula.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(80, 75, 78));
		panel.setBounds(10, 51, 279, 585);
		paneformula.getContentPane().add(panel);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.LEFT);
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblX.setBounds(118, 6, 50, 49);
		panel.add(lblX);
		
		JLabel lblY = new JLabel("Y");
		lblY.setHorizontalAlignment(SwingConstants.LEFT);
		lblY.setForeground(Color.WHITE);
		lblY.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblY.setBounds(120, 56, 65, 49);
		panel.add(lblY);
		
		JLabel lblSt = new JLabel("ST");
		lblSt.setHorizontalAlignment(SwingConstants.LEFT);
		lblSt.setForeground(Color.WHITE);
		lblSt.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblSt.setBounds(104, 109, 81, 49);
		panel.add(lblSt);
		
		JLabel lblMt = new JLabel("MT");
		lblMt.setHorizontalAlignment(SwingConstants.LEFT);
		lblMt.setForeground(Color.WHITE);
		lblMt.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblMt.setBounds(104, 157, 64, 45);
		panel.add(lblMt);
		
		JLabel lblEt = new JLabel("ET");
		lblEt.setHorizontalAlignment(SwingConstants.LEFT);
		lblEt.setForeground(Color.WHITE);
		lblEt.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEt.setBounds(104, 206, 81, 49);
		panel.add(lblEt);
		
		JLabel lblLossCnt = new JLabel("LCOUNT");
		lblLossCnt.setHorizontalAlignment(SwingConstants.LEFT);
		lblLossCnt.setForeground(Color.WHITE);
		lblLossCnt.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblLossCnt.setBounds(42, 257, 139, 49);
		panel.add(lblLossCnt);
		
		JLabel lblRound = new JLabel("ROUND");
		lblRound.setHorizontalAlignment(SwingConstants.LEFT);
		lblRound.setForeground(Color.WHITE);
		lblRound.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblRound.setBounds(52, 312, 139, 49);
		panel.add(lblRound);
		
		txtx = new JTextField();
		txtx.setHorizontalAlignment(SwingConstants.RIGHT);
		txtx.setForeground(new Color(255, 220, 135));
		txtx.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtx.setColumns(10);
		txtx.setCaretColor(Color.WHITE);
		txtx.setBackground(new Color(36, 34, 29));
		txtx.setBounds(143, 10, 81, 41);
		panel.add(txtx);
		
		txty = new JTextField();
		txty.setHorizontalAlignment(SwingConstants.RIGHT);
		txty.setForeground(new Color(255, 220, 135));
		txty.setFont(new Font("Verdana", Font.PLAIN, 20));
		txty.setColumns(10);
		txty.setCaretColor(Color.WHITE);
		txty.setBackground(new Color(36, 34, 29));
		txty.setBounds(143, 61, 81, 39);
		panel.add(txty);
		
		txtsthh = new JTextField();
		txtsthh.setHorizontalAlignment(SwingConstants.CENTER);
		txtsthh.setForeground(new Color(255, 220, 135));
		txtsthh.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtsthh.setColumns(10);
		txtsthh.setCaretColor(Color.WHITE);
		txtsthh.setBackground(new Color(36, 34, 29));
		txtsthh.setBounds(143, 109, 48, 42);
		panel.add(txtsthh);
		
		txtmthh = new JTextField();
		txtmthh.setText("15");
		txtmthh.setHorizontalAlignment(SwingConstants.CENTER);
		txtmthh.setForeground(new Color(255, 220, 135));
		txtmthh.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtmthh.setColumns(10);
		txtmthh.setCaretColor(Color.WHITE);
		txtmthh.setBackground(new Color(36, 34, 29));
		txtmthh.setBounds(143, 158, 48, 42);
		panel.add(txtmthh);
		
		txtethh = new JTextField();
		txtethh.setText("15");
		txtethh.setHorizontalAlignment(SwingConstants.CENTER);
		txtethh.setForeground(new Color(255, 220, 135));
		txtethh.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtethh.setColumns(10);
		txtethh.setCaretColor(Color.WHITE);
		txtethh.setBackground(new Color(36, 34, 29));
		txtethh.setBounds(143, 209, 48, 42);
		panel.add(txtethh);
		
		JLabel label_9 = new JLabel(":");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Dialog", Font.PLAIN, 22));
		label_9.setBounds(192, 103, 7, 49);
		panel.add(label_9);
		
		txtstmm = new JTextField();
		txtstmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtstmm.setForeground(new Color(255, 220, 135));
		txtstmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtstmm.setColumns(10);
		txtstmm.setCaretColor(Color.WHITE);
		txtstmm.setBackground(new Color(36, 34, 29));
		txtstmm.setBounds(199, 109, 48, 42);
		panel.add(txtstmm);
		
		JLabel label_11 = new JLabel(":");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Dialog", Font.PLAIN, 22));
		label_11.setBounds(192, 152, 7, 49);
		panel.add(label_11);
		
		txtmtmm = new JTextField();
		txtmtmm.setText("00");
		txtmtmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtmtmm.setForeground(new Color(255, 220, 135));
		txtmtmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtmtmm.setColumns(10);
		txtmtmm.setCaretColor(Color.WHITE);
		txtmtmm.setBackground(new Color(36, 34, 29));
		txtmtmm.setBounds(199, 158, 48, 42);
		panel.add(txtmtmm);
		
		JLabel label_13 = new JLabel(":");
		label_13.setForeground(Color.WHITE);
		label_13.setFont(new Font("Dialog", Font.PLAIN, 22));
		label_13.setBounds(192, 206, 7, 49);
		panel.add(label_13);
		
		txtetmm = new JTextField();
		txtetmm.setText("18");
		txtetmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtetmm.setForeground(new Color(255, 220, 135));
		txtetmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtetmm.setColumns(10);
		txtetmm.setCaretColor(Color.WHITE);
		txtetmm.setBackground(new Color(36, 34, 29));
		txtetmm.setBounds(201, 209, 48, 42);
		panel.add(txtetmm);
		
		txtlcount = new JTextField();
		txtlcount.setText("2");
		txtlcount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtlcount.setForeground(new Color(255, 220, 135));
		txtlcount.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtlcount.setColumns(10);
		txtlcount.setCaretColor(Color.WHITE);
		txtlcount.setBackground(new Color(36, 34, 29));
		txtlcount.setBounds(143, 257, 104, 49);
		panel.add(txtlcount);
		
		txtround = new JTextField();
		txtround.setText("3");
		txtround.setHorizontalAlignment(SwingConstants.RIGHT);
		txtround.setForeground(new Color(255, 220, 135));
		txtround.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtround.setColumns(10);
		txtround.setCaretColor(Color.WHITE);
		txtround.setBackground(new Color(36, 34, 29));
		txtround.setBounds(143, 312, 104, 49);
		panel.add(txtround);
		
		JLabel label_17 = new JLabel("%");
		label_17.setHorizontalAlignment(SwingConstants.LEFT);
		label_17.setForeground(Color.WHITE);
		label_17.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_17.setBounds(228, 8, 24, 44);
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("%");
		label_18.setHorizontalAlignment(SwingConstants.LEFT);
		label_18.setForeground(Color.WHITE);
		label_18.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_18.setBounds(226, 55, 24, 49);
		panel.add(label_18);
		
		btnsave = new JButton("SAVE");
		btnsave.setBounds(162, 522, 104, 45);
		panel.add(btnsave);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setBounds(28, 418, 238, 86);
		panel.add(panel_1);
		
		JLabel label_20 = new JLabel("BUY & SELL");
		label_20.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_20.setBackground(Color.WHITE);
		label_20.setBounds(0, 0, 0, 0);
		panel_1.add(label_20);
		
		JLabel lblTradeSwitch = new JLabel("Trade Switch");
		lblTradeSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		lblTradeSwitch.setForeground(new Color(255, 220, 135));
		lblTradeSwitch.setFont(new Font("Verdana", Font.PLAIN, 22));
		lblTradeSwitch.setBounds(40, 11, 158, 24);
		panel_1.add(lblTradeSwitch);
		
		JLabel lblon = new JLabel("ON");
		lblon.setHorizontalAlignment(SwingConstants.CENTER);
		lblon.setForeground(Color.RED);
		lblon.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblon.setBounds(40, 46, 60, 28);
		panel_1.add(lblon);
		
		JLabel lbloff = new JLabel("OFF");
		lbloff.setHorizontalAlignment(SwingConstants.CENTER);
		lbloff.setForeground(Color.GREEN);
		lbloff.setFont(new Font("Verdana", Font.PLAIN, 18));
		lbloff.setBounds(133, 46, 60, 28);
		panel_1.add(lbloff);
		
		JCheckBox chckbxTradeSwitch = new JCheckBox("");
		chckbxTradeSwitch.setBackground(new Color(51,51,51));
		chckbxTradeSwitch.setForeground(new Color(255, 220, 135));
		chckbxTradeSwitch.setFont(new Font("Verdana", Font.PLAIN, 22));
		chckbxTradeSwitch.setBounds(106, 42, 21, 32);
		panel_1.add(chckbxTradeSwitch);
		
		JLabel lblQty = new JLabel("QTY");
		lblQty.setHorizontalAlignment(SwingConstants.LEFT);
		lblQty.setForeground(Color.WHITE);
		lblQty.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblQty.setBounds(87, 365, 104, 42);
		panel.add(lblQty);
		
		txtqty = new JTextField();
		txtqty.setText("2");
		txtqty.setHorizontalAlignment(SwingConstants.RIGHT);
		txtqty.setForeground(new Color(255, 220, 135));
		txtqty.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtqty.setColumns(10);
		txtqty.setCaretColor(Color.WHITE);
		txtqty.setBackground(new Color(36, 34, 29));
		txtqty.setBounds(143, 366, 104, 41);
		panel.add(txtqty);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setBounds(23, 522, 110, 45);
		panel.add(btnDelete);
		
		JLabel lblhead = new JLabel("?");
		lblhead.setHorizontalAlignment(SwingConstants.CENTER);
		lblhead.setForeground(new Color(255, 220, 135));
		lblhead.setFont(new Font("Verdana", Font.BOLD, 22));
		lblhead.setBounds(0, -1, 297, 43);
		paneformula.getContentPane().add(lblhead);
		paneformula.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		paneformula.addKeyListener(keyhand);
	}
	
	
	// Key input handlers
	class InputHandler implements KeyListener
	{
	        public void keyTyped(KeyEvent kt)
	        {
	        }
	        
		        public void keyPressed(java.awt.event.KeyEvent evt)
		        {
		        		if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
		        		{
		        			paneformula.dispose();
		            }
		        }
	        
	        public void keyReleased(KeyEvent kr)
	        {
	        }
	  }
}
