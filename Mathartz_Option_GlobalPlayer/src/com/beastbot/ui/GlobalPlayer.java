package com.beastbot.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class GlobalPlayer {

	private JFrame Gplayer;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtpl1;
	private JTextField txtpl1qty;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlobalPlayer window = new GlobalPlayer();
					window.Gplayer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GlobalPlayer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Gplayer = new JFrame();
		Gplayer.setBounds(100, 100, 589, 488);
		Gplayer.setTitle("GLOBAL PLAYER");
		Gplayer.getContentPane().setLayout(null);
		Gplayer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Gplayer.getContentPane().setBackground(new Color(51, 51, 51));
		
		JLabel lblPlayers = new JLabel("PLAYER'S");
		lblPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayers.setForeground(new Color(255, 220, 135));
		lblPlayers.setFont(new Font("Verdana", Font.BOLD, 18));
		lblPlayers.setBounds(0, 0, 573, 30);
		Gplayer.getContentPane().add(lblPlayers);
		
		JLabel label = new JLabel("SYMBOL");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Verdana", Font.PLAIN, 16));
		label.setBounds(27, 39, 82, 49);
		Gplayer.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setText("NIFTY");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(255, 220, 135));
		textField.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setCaretColor(Color.WHITE);
		textField.setBackground(new Color(36, 34, 29));
		textField.setBounds(118, 47, 182, 30);
		Gplayer.getContentPane().add(textField);
		
		JLabel label_1 = new JLabel("DATE");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Verdana", Font.PLAIN, 16));
		label_1.setBounds(330, 53, 71, 21);
		Gplayer.getContentPane().add(label_1);
		
		textField_1 = new JTextField("");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(new Color(255, 220, 135));
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setCaretColor(Color.WHITE);
		textField_1.setBackground(new Color(36, 34, 29));
		textField_1.setBounds(396, 45, 50, 30);
		Gplayer.getContentPane().add(textField_1);
		
		textField_2 = new JTextField((String) null);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(new Color(255, 220, 135));
		textField_2.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setCaretColor(Color.WHITE);
		textField_2.setBackground(new Color(36, 34, 29));
		textField_2.setBounds(448, 45, 50, 30);
		Gplayer.getContentPane().add(textField_2);
		
		textField_3 = new JTextField((String) null);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(new Color(255, 220, 135));
		textField_3.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setCaretColor(Color.WHITE);
		textField_3.setBackground(new Color(36, 34, 29));
		textField_3.setBounds(501, 45, 50, 30);
		Gplayer.getContentPane().add(textField_3);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(10, 93, 553, 3);
		Gplayer.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(288, 107, 10, 284);
		Gplayer.getContentPane().add(separator_1);
		
		JLabel lblCall = new JLabel("CALL");
		lblCall.setHorizontalAlignment(SwingConstants.CENTER);
		lblCall.setForeground(new Color(255, 220, 135));
		lblCall.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblCall.setBounds(0, 105, 286, 30);
		Gplayer.getContentPane().add(lblCall);
		
		JLabel lblPut = new JLabel("PUT");
		lblPut.setHorizontalAlignment(SwingConstants.CENTER);
		lblPut.setForeground(new Color(255, 220, 135));
		lblPut.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblPut.setBounds(287, 105, 286, 30);
		Gplayer.getContentPane().add(lblPut);
		
		txtpl1 = new JTextField();
		txtpl1.setEnabled(false);
		txtpl1.setHorizontalAlignment(SwingConstants.CENTER);
		txtpl1.setForeground(new Color(255, 220, 135));
		txtpl1.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtpl1.setColumns(10);
		txtpl1.setCaretColor(Color.WHITE);
		txtpl1.setBackground(new Color(36, 34, 29));
		txtpl1.setBounds(40, 146, 108, 32);
		Gplayer.getContentPane().add(txtpl1);
		
		txtpl1qty = new JTextField("");
		txtpl1qty.setHorizontalAlignment(SwingConstants.CENTER);
		txtpl1qty.setForeground(new Color(255, 220, 135));
		txtpl1qty.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtpl1qty.setEnabled(false);
		txtpl1qty.setColumns(10);
		txtpl1qty.setCaretColor(Color.WHITE);
		txtpl1qty.setBackground(new Color(36, 34, 29));
		txtpl1qty.setBounds(158, 146, 50, 32);
		Gplayer.getContentPane().add(txtpl1qty);
		
		JCheckBox chktradepl1 = new JCheckBox("");
		chktradepl1.setForeground(new Color(255, 220, 135));
		chktradepl1.setFont(new Font("Verdana", Font.PLAIN, 22));
		chktradepl1.setBackground(new Color(51, 51, 51));
		chktradepl1.setBounds(214, 146, 21, 32);
		Gplayer.getContentPane().add(chktradepl1);
		
		JLabel lbloffpl1 = new JLabel("*");
		lbloffpl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbloffpl1.setForeground(Color.GREEN);
		lbloffpl1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lbloffpl1.setBounds(241, 152, 26, 26);
		Gplayer.getContentPane().add(lbloffpl1);
		
		JLabel lblonpl1 = new JLabel("*");
		lblonpl1.setHorizontalAlignment(SwingConstants.CENTER);
		lblonpl1.setForeground(Color.RED);
		lblonpl1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblonpl1.setBounds(241, 152, 26, 26);
		Gplayer.getContentPane().add(lblonpl1);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setForeground(new Color(255, 220, 135));
		textField_6.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_6.setColumns(10);
		textField_6.setCaretColor(Color.WHITE);
		textField_6.setBackground(new Color(36, 34, 29));
		textField_6.setBounds(40, 185, 108, 32);
		Gplayer.getContentPane().add(textField_6);
		
		textField_7 = new JTextField("");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setForeground(new Color(255, 220, 135));
		textField_7.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		textField_7.setCaretColor(Color.WHITE);
		textField_7.setBackground(new Color(36, 34, 29));
		textField_7.setBounds(158, 185, 50, 32);
		Gplayer.getContentPane().add(textField_7);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setForeground(new Color(255, 220, 135));
		checkBox_1.setFont(new Font("Verdana", Font.PLAIN, 22));
		checkBox_1.setBackground(new Color(51, 51, 51));
		checkBox_1.setBounds(214, 185, 21, 32);
		Gplayer.getContentPane().add(checkBox_1);
		
		JLabel label_4 = new JLabel("*");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.GREEN);
		label_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_4.setBounds(241, 191, 26, 26);
		Gplayer.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_5.setBounds(241, 191, 26, 26);
		Gplayer.getContentPane().add(label_5);
		
		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setForeground(new Color(255, 220, 135));
		textField_8.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_8.setColumns(10);
		textField_8.setCaretColor(Color.WHITE);
		textField_8.setBackground(new Color(36, 34, 29));
		textField_8.setBounds(40, 222, 108, 32);
		Gplayer.getContentPane().add(textField_8);
		
		textField_9 = new JTextField("");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setForeground(new Color(255, 220, 135));
		textField_9.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_9.setEnabled(false);
		textField_9.setColumns(10);
		textField_9.setCaretColor(Color.WHITE);
		textField_9.setBackground(new Color(36, 34, 29));
		textField_9.setBounds(158, 222, 50, 32);
		Gplayer.getContentPane().add(textField_9);
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.setForeground(new Color(255, 220, 135));
		checkBox_2.setFont(new Font("Verdana", Font.PLAIN, 22));
		checkBox_2.setBackground(new Color(51, 51, 51));
		checkBox_2.setBounds(214, 222, 21, 32);
		Gplayer.getContentPane().add(checkBox_2);
		
		JLabel label_6 = new JLabel("*");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.GREEN);
		label_6.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_6.setBounds(241, 228, 26, 26);
		Gplayer.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("*");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_7.setBounds(241, 228, 26, 26);
		Gplayer.getContentPane().add(label_7);
		
		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setForeground(new Color(255, 220, 135));
		textField_10.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_10.setColumns(10);
		textField_10.setCaretColor(Color.WHITE);
		textField_10.setBackground(new Color(36, 34, 29));
		textField_10.setBounds(40, 261, 108, 32);
		Gplayer.getContentPane().add(textField_10);
		
		textField_11 = new JTextField("");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setForeground(new Color(255, 220, 135));
		textField_11.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_11.setEnabled(false);
		textField_11.setColumns(10);
		textField_11.setCaretColor(Color.WHITE);
		textField_11.setBackground(new Color(36, 34, 29));
		textField_11.setBounds(158, 261, 50, 32);
		Gplayer.getContentPane().add(textField_11);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.setForeground(new Color(255, 220, 135));
		checkBox_3.setFont(new Font("Verdana", Font.PLAIN, 22));
		checkBox_3.setBackground(new Color(51, 51, 51));
		checkBox_3.setBounds(214, 261, 21, 32);
		Gplayer.getContentPane().add(checkBox_3);
		
		JLabel label_8 = new JLabel("*");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(Color.GREEN);
		label_8.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_8.setBounds(241, 267, 26, 26);
		Gplayer.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("*");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_9.setBounds(241, 267, 26, 26);
		Gplayer.getContentPane().add(label_9);
		
		textField_12 = new JTextField();
		textField_12.setEnabled(false);
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setForeground(new Color(255, 220, 135));
		textField_12.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_12.setColumns(10);
		textField_12.setCaretColor(Color.WHITE);
		textField_12.setBackground(new Color(36, 34, 29));
		textField_12.setBounds(40, 300, 108, 32);
		Gplayer.getContentPane().add(textField_12);
		
		textField_13 = new JTextField("");
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setForeground(new Color(255, 220, 135));
		textField_13.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_13.setEnabled(false);
		textField_13.setColumns(10);
		textField_13.setCaretColor(Color.WHITE);
		textField_13.setBackground(new Color(36, 34, 29));
		textField_13.setBounds(158, 300, 50, 32);
		Gplayer.getContentPane().add(textField_13);
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.setForeground(new Color(255, 220, 135));
		checkBox_4.setFont(new Font("Verdana", Font.PLAIN, 22));
		checkBox_4.setBackground(new Color(51, 51, 51));
		checkBox_4.setBounds(214, 300, 21, 32);
		Gplayer.getContentPane().add(checkBox_4);
		
		JLabel label_10 = new JLabel("*");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(Color.GREEN);
		label_10.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_10.setBounds(241, 306, 26, 26);
		Gplayer.getContentPane().add(label_10);
		
		JLabel label_11 = new JLabel("*");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setForeground(Color.RED);
		label_11.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_11.setBounds(241, 306, 26, 26);
		Gplayer.getContentPane().add(label_11);
		
		textField_14 = new JTextField();
		textField_14.setEnabled(false);
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setForeground(new Color(255, 220, 135));
		textField_14.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_14.setColumns(10);
		textField_14.setCaretColor(Color.WHITE);
		textField_14.setBackground(new Color(36, 34, 29));
		textField_14.setBounds(319, 146, 108, 32);
		Gplayer.getContentPane().add(textField_14);
		
		textField_15 = new JTextField("");
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setForeground(new Color(255, 220, 135));
		textField_15.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_15.setEnabled(false);
		textField_15.setColumns(10);
		textField_15.setCaretColor(Color.WHITE);
		textField_15.setBackground(new Color(36, 34, 29));
		textField_15.setBounds(437, 146, 50, 32);
		Gplayer.getContentPane().add(textField_15);
		
		JCheckBox checkBox_5 = new JCheckBox("");
		checkBox_5.setForeground(new Color(255, 220, 135));
		checkBox_5.setFont(new Font("Verdana", Font.PLAIN, 22));
		checkBox_5.setBackground(new Color(51, 51, 51));
		checkBox_5.setBounds(493, 146, 21, 32);
		Gplayer.getContentPane().add(checkBox_5);
		
		JLabel label_12 = new JLabel("*");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setForeground(Color.GREEN);
		label_12.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_12.setBounds(520, 152, 26, 26);
		Gplayer.getContentPane().add(label_12);
		
		JLabel label_13 = new JLabel("*");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setForeground(Color.RED);
		label_13.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_13.setBounds(520, 152, 26, 26);
		Gplayer.getContentPane().add(label_13);
		
		textField_16 = new JTextField();
		textField_16.setEnabled(false);
		textField_16.setHorizontalAlignment(SwingConstants.CENTER);
		textField_16.setForeground(new Color(255, 220, 135));
		textField_16.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_16.setColumns(10);
		textField_16.setCaretColor(Color.WHITE);
		textField_16.setBackground(new Color(36, 34, 29));
		textField_16.setBounds(319, 185, 108, 32);
		Gplayer.getContentPane().add(textField_16);
		
		textField_17 = new JTextField("");
		textField_17.setHorizontalAlignment(SwingConstants.CENTER);
		textField_17.setForeground(new Color(255, 220, 135));
		textField_17.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_17.setEnabled(false);
		textField_17.setColumns(10);
		textField_17.setCaretColor(Color.WHITE);
		textField_17.setBackground(new Color(36, 34, 29));
		textField_17.setBounds(437, 185, 50, 32);
		Gplayer.getContentPane().add(textField_17);
		
		JCheckBox checkBox_6 = new JCheckBox("");
		checkBox_6.setForeground(new Color(255, 220, 135));
		checkBox_6.setFont(new Font("Verdana", Font.PLAIN, 22));
		checkBox_6.setBackground(new Color(51, 51, 51));
		checkBox_6.setBounds(493, 185, 21, 32);
		Gplayer.getContentPane().add(checkBox_6);
		
		JLabel label_14 = new JLabel("*");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setForeground(Color.GREEN);
		label_14.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_14.setBounds(520, 191, 26, 26);
		Gplayer.getContentPane().add(label_14);
		
		JLabel label_15 = new JLabel("*");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setForeground(Color.RED);
		label_15.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_15.setBounds(520, 191, 26, 26);
		Gplayer.getContentPane().add(label_15);
		
		textField_18 = new JTextField();
		textField_18.setEnabled(false);
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setForeground(new Color(255, 220, 135));
		textField_18.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_18.setColumns(10);
		textField_18.setCaretColor(Color.WHITE);
		textField_18.setBackground(new Color(36, 34, 29));
		textField_18.setBounds(319, 222, 108, 32);
		Gplayer.getContentPane().add(textField_18);
		
		textField_19 = new JTextField("");
		textField_19.setHorizontalAlignment(SwingConstants.CENTER);
		textField_19.setForeground(new Color(255, 220, 135));
		textField_19.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_19.setEnabled(false);
		textField_19.setColumns(10);
		textField_19.setCaretColor(Color.WHITE);
		textField_19.setBackground(new Color(36, 34, 29));
		textField_19.setBounds(437, 222, 50, 32);
		Gplayer.getContentPane().add(textField_19);
		
		JCheckBox checkBox_7 = new JCheckBox("");
		checkBox_7.setForeground(new Color(255, 220, 135));
		checkBox_7.setFont(new Font("Verdana", Font.PLAIN, 22));
		checkBox_7.setBackground(new Color(51, 51, 51));
		checkBox_7.setBounds(493, 222, 21, 32);
		Gplayer.getContentPane().add(checkBox_7);
		
		JLabel label_16 = new JLabel("*");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setForeground(Color.GREEN);
		label_16.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_16.setBounds(520, 228, 26, 26);
		Gplayer.getContentPane().add(label_16);
		
		JLabel label_17 = new JLabel("*");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setForeground(Color.RED);
		label_17.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_17.setBounds(520, 228, 26, 26);
		Gplayer.getContentPane().add(label_17);
		
		textField_20 = new JTextField();
		textField_20.setEnabled(false);
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setForeground(new Color(255, 220, 135));
		textField_20.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_20.setColumns(10);
		textField_20.setCaretColor(Color.WHITE);
		textField_20.setBackground(new Color(36, 34, 29));
		textField_20.setBounds(319, 261, 108, 32);
		Gplayer.getContentPane().add(textField_20);
		
		textField_21 = new JTextField("");
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setForeground(new Color(255, 220, 135));
		textField_21.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_21.setEnabled(false);
		textField_21.setColumns(10);
		textField_21.setCaretColor(Color.WHITE);
		textField_21.setBackground(new Color(36, 34, 29));
		textField_21.setBounds(437, 261, 50, 32);
		Gplayer.getContentPane().add(textField_21);
		
		JCheckBox checkBox_8 = new JCheckBox("");
		checkBox_8.setForeground(new Color(255, 220, 135));
		checkBox_8.setFont(new Font("Verdana", Font.PLAIN, 22));
		checkBox_8.setBackground(new Color(51, 51, 51));
		checkBox_8.setBounds(493, 261, 21, 32);
		Gplayer.getContentPane().add(checkBox_8);
		
		JLabel label_18 = new JLabel("*");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setForeground(Color.GREEN);
		label_18.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_18.setBounds(520, 267, 26, 26);
		Gplayer.getContentPane().add(label_18);
		
		JLabel label_19 = new JLabel("*");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setForeground(Color.RED);
		label_19.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_19.setBounds(520, 267, 26, 26);
		Gplayer.getContentPane().add(label_19);
		
		textField_22 = new JTextField();
		textField_22.setEnabled(false);
		textField_22.setHorizontalAlignment(SwingConstants.CENTER);
		textField_22.setForeground(new Color(255, 220, 135));
		textField_22.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_22.setColumns(10);
		textField_22.setCaretColor(Color.WHITE);
		textField_22.setBackground(new Color(36, 34, 29));
		textField_22.setBounds(319, 300, 108, 32);
		Gplayer.getContentPane().add(textField_22);
		
		textField_23 = new JTextField("");
		textField_23.setHorizontalAlignment(SwingConstants.CENTER);
		textField_23.setForeground(new Color(255, 220, 135));
		textField_23.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_23.setEnabled(false);
		textField_23.setColumns(10);
		textField_23.setCaretColor(Color.WHITE);
		textField_23.setBackground(new Color(36, 34, 29));
		textField_23.setBounds(437, 300, 50, 32);
		Gplayer.getContentPane().add(textField_23);
		
		JCheckBox checkBox_9 = new JCheckBox("");
		checkBox_9.setForeground(new Color(255, 220, 135));
		checkBox_9.setFont(new Font("Verdana", Font.PLAIN, 22));
		checkBox_9.setBackground(new Color(51, 51, 51));
		checkBox_9.setBounds(493, 300, 21, 32);
		Gplayer.getContentPane().add(checkBox_9);
		
		JLabel label_20 = new JLabel("*");
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setForeground(Color.GREEN);
		label_20.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_20.setBounds(520, 306, 26, 26);
		Gplayer.getContentPane().add(label_20);
		
		JLabel label_21 = new JLabel("*");
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setForeground(Color.RED);
		label_21.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_21.setBounds(520, 306, 26, 26);
		Gplayer.getContentPane().add(label_21);
		
		textField_24 = new JTextField();
		textField_24.setHorizontalAlignment(SwingConstants.CENTER);
		textField_24.setForeground(new Color(255, 220, 135));
		textField_24.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_24.setColumns(10);
		textField_24.setCaretColor(Color.WHITE);
		textField_24.setBackground(new Color(36, 34, 29));
		textField_24.setBounds(100, 343, 108, 32);
		Gplayer.getContentPane().add(textField_24);
		
		textField_25 = new JTextField();
		textField_25.setHorizontalAlignment(SwingConstants.CENTER);
		textField_25.setForeground(new Color(255, 220, 135));
		textField_25.setFont(new Font("Verdana", Font.PLAIN, 20));
		textField_25.setColumns(10);
		textField_25.setCaretColor(Color.WHITE);
		textField_25.setBackground(new Color(36, 34, 29));
		textField_25.setBounds(379, 343, 108, 32);
		Gplayer.getContentPane().add(textField_25);
		
		JButton button = new JButton("SAVE");
		button.setBounds(149, 402, 278, 30);
		Gplayer.getContentPane().add(button);
		Gplayer.setVisible(true);
		Gplayer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
