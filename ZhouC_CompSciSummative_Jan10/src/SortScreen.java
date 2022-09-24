/*
 Cheukman Zhou
 This program is used for the sort screen of the International Bank Account GUI program
 Version 1.0
 January 23, 2022
*/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class SortScreen {
	InternationalBankAccount bank = new InternationalBankAccount(); //Creates an InternationalBankAccount object

	
	JFrame sortFrame;

	
	/**
	 * Launch the application
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternationalBankAccount window = new InternationalBankAccount();
					window.bankFrame.setVisible(true);
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
	
	public SortScreen() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame
	 */
	
	private void initialize() {
		//sortFrame JFrame
		sortFrame = new JFrame();
		sortFrame.setBounds(100, 100, 850, 500);
		sortFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sortFrame.getContentPane().setLayout(null);

		//titlePanel JPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 850, 140);
		titlePanel.setBackground(Color.BLUE);
		sortFrame.getContentPane().add(titlePanel);
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
		sortFrame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);

		//confirmLabel JLabel
		JLabel confirmLabel = new JLabel("Confirm to Sort Transaction Log:");
		confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		confirmLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		confirmLabel.setBounds(165, 216, 520, 25);
		sortFrame.getContentPane().add(confirmLabel);

		//sortButton JButton
		JButton sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubbleSort(bank.transactionLog, bank.transactionLog.size()); //Sorts the transaction log using bubble sort
				
				sortFrame.setVisible(false); //Hides the sort screen

				bank.bankFrame.setVisible(true); //Shows the home screen
			}
		});
		sortButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		sortButton.setBounds(440, 43, 230, 50);
		buttonPanel.add(sortButton);

		//backButton JButton
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortFrame.setVisible(false); //Hides the sort screen

				bank.bankFrame.setVisible(true); //Shows the home screen
			}
		});
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		backButton.setBounds(180, 43, 230, 50);
		buttonPanel.add(backButton);
	}

	/*
	 Method: bubbleSort
	 Description: The Method will sort the transaction log using bubble sort
	 Parameters:
	 	sortedLog represents transaction log
	 	length represents the size of the transaction log
	 Returns: None
	*/
	
	public static void bubbleSort(ArrayList<ArrayList<String>> sortedLog, int length) {
		//Base case
		if (length == 1)
			return;

		//One pass of bubble sort. After this pass, the oldest transaction is moved to the end
		for (int i = 0; i < length - 1; i++) {
			//Splits the dates being compared and converts them into integers
			String date1String = sortedLog.get(i).get(1);
			ArrayList<String> date1AL = new ArrayList<>(Arrays.asList(date1String.split("/")));
			date1String = date1AL.get(0) + date1AL.get(1);
			int date1 = Integer.parseInt(date1String);
			
			String date2String = sortedLog.get(i + 1).get(1);
			ArrayList<String> date2AL = new ArrayList<>(Arrays.asList(date2String.split("/")));
			date2String = date2AL.get(0) + date2AL.get(1);
			int date2 = Integer.parseInt(date2String);
			
			//If date1 is greater than date2
			if (date1 > date2) {
				//Swap date1 and date2
				Collections.swap(sortedLog, i, i + 1);
			}
		}
		
		//Oldest transaction is fixed, recur for the remaining 2D ArrayList
		bubbleSort(sortedLog, length - 1);
	}
}
