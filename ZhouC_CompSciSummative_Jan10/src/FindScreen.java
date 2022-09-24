/*
 Cheukman Zhou
 This program is used for the find screen of the International Bank Account GUI program
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
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class FindScreen {
	InternationalBankAccount bank = new InternationalBankAccount(); //Creates an InternationalBankAccount object
	public static String foundTransactions; //Found Transactions
	

	JFrame findFrame;

	
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
	
	public FindScreen() {
		initialize();
	}

	
	/*
	 Method: linearSearch
	 Description: The Method will search for a value in the 2D ArrayList using the linear search method
	 Parameters:
	 	searchLog represents transaction log
	 	findDate represents the date wanting to be found
	 Returns:
	 	found stores transactions that have the same date as the date that is wanting to be found
	*/
	
	public static String linearSearch(ArrayList<ArrayList<String>> searchLog, String findDate) {
		String found = "";
		
		//Goes through the transaction log and finds any transactions that have the same date as the date wanting to be found and stores it into found
		for (int i = 0; i < searchLog.size(); i++) {
			if (findDate.equals(searchLog.get(i).get(1))) {
				for (String k : searchLog.get(i)) {
					found = found + " | " + k;
				}
				
				found = found + " | \n";
			}
		}

		if (found.length() == 0) { //If nothing in found
			found = "Not Found"; //Not found is stored into found
		}

		return found; //Returns found
	}
	
	
	/**
	 * Initialize the contents of the frame
	 */
	
	private void initialize() {
		//findFrame JFrame
		findFrame = new JFrame();
		findFrame.setBounds(100, 100, 850, 500);
		findFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		findFrame.getContentPane().setLayout(null);

		//titlePanel JPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 850, 140);
		titlePanel.setBackground(Color.BLUE);
		findFrame.getContentPane().add(titlePanel);
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
		findFrame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);

		//findDateLabel JLabel
		JLabel findDateLabel = new JLabel("Select a Transaction Date to Find:");
		findDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		findDateLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		findDateLabel.setBounds(165, 180, 520, 25);
		findFrame.getContentPane().add(findDateLabel);

		//dateLabel JLabel
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		dateLabel.setBounds(300, 240, 61, 25);
		findFrame.getContentPane().add(dateLabel);

		//monthComboBox JComboBox
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		monthComboBox.setBounds(373, 240, 72, 27);
		findFrame.getContentPane().add(monthComboBox);

		//slashLabel JLabel
		JLabel slashLabel = new JLabel("/");
		slashLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		slashLabel.setBounds(457, 240, 18, 25);
		findFrame.getContentPane().add(slashLabel);

		//dayComboBox JComboBox
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		dayComboBox.setBounds(479, 240, 72, 27);
		findFrame.getContentPane().add(dayComboBox);

		//findButton JButton
		JButton findButton = new JButton("Find");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String findDate = monthComboBox.getSelectedItem() + "/" + dayComboBox.getSelectedItem(); //Stores the date wanting to be found in findDate
				
				foundTransactions = linearSearch(bank.transactionLog, findDate); //Searches for the transactions with the same date and stores it within foundTransactions
				
				FindDisplayScreen findDisplay = new FindDisplayScreen(); //Creates a FindDisplayScreen object
				
				findFrame.setVisible(false); //Hides the find screen

				findDisplay.findDisplayFrame.setVisible(true); //Shows the find display screen
			}
		});
		findButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		findButton.setBounds(440, 43, 230, 50);
		buttonPanel.add(findButton);

		//backButton JButton
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findFrame.setVisible(false); //Hides the find screen

				bank.bankFrame.setVisible(true); //Shows the home screen
			}
		});
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		backButton.setBounds(180, 43, 230, 50);
		buttonPanel.add(backButton);
	}
}
