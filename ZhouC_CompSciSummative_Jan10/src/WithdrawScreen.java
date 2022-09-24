/*
 Cheukman Zhou
 This program is used for the withdraw screen of the International Bank Account GUI program
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
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class WithdrawScreen {
	InternationalBankAccount bank = new InternationalBankAccount(); //Creates an InternationalBankAccount object
	
	
	JFrame withdrawFrame;
	private JTextField amountTextField;

	
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
	
	public WithdrawScreen() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame
	 */
	
	private void initialize() {
		//withdrawFrame JFrame
		withdrawFrame = new JFrame();
		withdrawFrame.setBounds(100, 100, 850, 500);
		withdrawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		withdrawFrame.getContentPane().setLayout(null);
		
		//titlePanel JPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 850, 140);
		titlePanel.setBackground(Color.BLUE);
		withdrawFrame.getContentPane().add(titlePanel);
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
		withdrawFrame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		//dateLabel JLabel
		JLabel dateLabel = new JLabel("Select Date:");
		dateLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		dateLabel.setBounds(270, 162, 119, 25);
		withdrawFrame.getContentPane().add(dateLabel);
		
		//monthComboBox JComboBox
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthComboBox.setBounds(401, 162, 72, 27);
		withdrawFrame.getContentPane().add(monthComboBox);
		
		//slashLabel JLabel
		JLabel slashLabel = new JLabel("/");
		slashLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		slashLabel.setBounds(485, 162, 18, 25);
		withdrawFrame.getContentPane().add(slashLabel);
		
		//dayComboBox JComboBox
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayComboBox.setBounds(507, 162, 72, 27);
		withdrawFrame.getContentPane().add(dayComboBox);
		
		//amountLabel JLabel
		JLabel amountLabel = new JLabel("Amount: $");
		amountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		amountLabel.setBounds(270, 230, 107, 25);
		withdrawFrame.getContentPane().add(amountLabel);
		
		//amountTextField JTextField
		amountTextField = new JTextField();
		amountTextField.setText("0.00");
		amountTextField.setHorizontalAlignment(SwingConstants.CENTER);
		amountTextField.setBounds(377, 224, 202, 35);
		withdrawFrame.getContentPane().add(amountTextField);
		amountTextField.setColumns(10);
		
		//errorLabel JLabel
		JLabel errorLabel = new JLabel("INVALID WITHDRAW | TRY AGAIN");
		errorLabel.setForeground(Color.RED);
		errorLabel.setEnabled(true);
		errorLabel.setVisible(false);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		errorLabel.setBounds(295, 283, 260, 38);
		withdrawFrame.getContentPane().add(errorLabel);
		
		//withdrawButton JButton
		JButton withdrawButton = new JButton("Withdraw");
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String amountString = amountTextField.getText(); //Stores what is entered into amountTextField into amountString
					double amount = Double.parseDouble(amountString); //Tries to convert amountString to double from string and store it into amount
					double checkDebt = bank.totalAmount - amount; //Checks if the withdraw entered goes beyond what you have in the account
					
					//If no amount is entered and it still contains 0.00
					if (amountString.equals("0.00")) {
						errorLabel.setVisible(true); //Show error label
					}
					
					//Else if there is not enough money in the account to make the withdraw
					else if (checkDebt < 0) {
						errorLabel.setVisible(true); //Show error label
					}
					
					//Else an amount is entered
					else {
						ArrayList<String> withdraw = new ArrayList<>(); //Creates an ArrayList to store the info of the withdraw
						
						bank.totalAmount = bank.totalAmount - amount; //Updates the total amount
						
						withdraw.add("WITHDRAW"); //Adds the type withdraw to the ArrayList
						withdraw.add(monthComboBox.getSelectedItem() + "/" + dayComboBox.getSelectedItem()); //Adds the date to the ArrayList
						withdraw.add("- $" + Double.toString(amount)); //Adds the amount being withdrawn to the ArrayList
						withdraw.add("$" + Double.toString(bank.totalAmount)); //Adds the total amount to the ArrayList
						
						bank.transactionLog.add(withdraw); //Adds the ArrayList to the transaction log
						
						withdrawFrame.setVisible(false); //Hides the withdraw screen
						
						bank.bankFrame.setVisible(true); //Shows the home screen
					}
				}
				
				catch (Exception e1) {
					errorLabel.setVisible(true);
				}
			}
		});
		withdrawButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		withdrawButton.setBounds(440, 43, 230, 50);
		buttonPanel.add(withdrawButton);
		
		//backButton JButton
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawFrame.setVisible(false); //Hides withdraw screen
				
				bank.bankFrame.setVisible(true); //Shows home screen
			}
		});
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		backButton.setBounds(180, 43, 230, 50);
		buttonPanel.add(backButton);
	}
}
