/*
 Cheukman Zhou
 This program is used for the find display screen of the International Bank Account GUI program
 Version 1.0
 January 23, 2022
*/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class FindDisplayScreen {
	InternationalBankAccount bank = new InternationalBankAccount(); //Creates an InternationalBankAccount object
	
	
	JFrame findDisplayFrame;

	
	/**
	 * Launch the application
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayScreen window = new DisplayScreen();
					window.displayFrame.setVisible(true);
				}
				
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application
	 */
	
	public FindDisplayScreen() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame
	 */
	
	private void initialize() {
		//findDisplayFrame JFrame
		findDisplayFrame = new JFrame();
		findDisplayFrame.setBounds(100, 100, 850, 500);
		findDisplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		findDisplayFrame.getContentPane().setLayout(null);
		
		//titlePanel JPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 850, 140);
		titlePanel.setBackground(Color.BLUE);
		findDisplayFrame.getContentPane().add(titlePanel);
		titlePanel.setLayout(null);
		
		//titleLabel JLabel
		JLabel titleLabel = new JLabel("THE BANK OF RZB");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 40));
		titleLabel.setBounds(190, 40, 470, 55);
		titlePanel.add(titleLabel);
		
		//buttonPanel JPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 338, 850, 140);
		buttonPanel.setBackground(Color.DARK_GRAY);
		buttonPanel.setForeground(Color.BLACK);
		findDisplayFrame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		//backButton JButton
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findDisplayFrame.setVisible(false); //Hides the find display screen
				
				bank.bankFrame.setVisible(true); //Shows the home screen
			}
		});
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		backButton.setBounds(310, 43, 230, 50);
		buttonPanel.add(backButton);
		
		//bodyPanel JPanel
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBounds(0, 140, 850, 198);
		findDisplayFrame.getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);
		
		//findTransactionLogTextArea JTextArea
		JTextArea findTransactionLogTextArea = new JTextArea();
		findTransactionLogTextArea.setWrapStyleWord(true);
		findTransactionLogTextArea.setEditable(false);
		findTransactionLogTextArea.setBounds(90, 160, 670, 156);
		
		//scroll JScrollPane
		//Allows for the TextArea to have a scroll bar if needed
		JScrollPane scroll = new JScrollPane(findTransactionLogTextArea);
		scroll.setBounds(90, 21, 670, 156);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	    FindScreen find = new FindScreen(); //Creates a FindScreen object
		
		findTransactionLogTextArea.setText(" | Type | Date | Amount ($) | Total Amount ($) | \n" + find.foundTransactions); //Adds the heading and the found transactions to the TextArea
	    
		bodyPanel.add(scroll);
	}
}
