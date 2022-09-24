/*
 Cheukman Zhou
 This program is used for the deposit screen of the International Bank Account GUI program
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

public class DepositScreen {
	InternationalBankAccount bank = new InternationalBankAccount(); //Creates and InternationalBankAccount object
	
	
	JFrame depositFrame;
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
	
	public DepositScreen() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame
	 */
	
	private void initialize() {
		//depositFrame JFrame
		depositFrame = new JFrame();
		depositFrame.setBounds(100, 100, 850, 500);
		depositFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		depositFrame.getContentPane().setLayout(null);
		
		//titlePanel JPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 850, 140);
		titlePanel.setBackground(Color.BLUE);
		depositFrame.getContentPane().add(titlePanel);
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
		depositFrame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		//dateLabel JLabel
		JLabel dateLabel = new JLabel("Select Date:");
		dateLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		dateLabel.setBounds(80, 162, 119, 25);
		depositFrame.getContentPane().add(dateLabel);
		
		//monthComboBox JComboBox
		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthComboBox.setBounds(211, 162, 72, 27);
		depositFrame.getContentPane().add(monthComboBox);
		
		//slashLabel JLabel
		JLabel slashLabel = new JLabel("/");
		slashLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		slashLabel.setBounds(295, 162, 18, 25);
		depositFrame.getContentPane().add(slashLabel);
		
		//dayComboBox JComboBox
		JComboBox dayComboBox = new JComboBox();
		dayComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayComboBox.setBounds(317, 162, 72, 27);
		depositFrame.getContentPane().add(dayComboBox);
		
		//amountLabel JLabel
		JLabel amountLabel = new JLabel("Amount: $");
		amountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		amountLabel.setBounds(80, 244, 119, 25);
		depositFrame.getContentPane().add(amountLabel);
		
		//amountTextField JTextField
		amountTextField = new JTextField();
		amountTextField.setText("0.00");
		amountTextField.setHorizontalAlignment(SwingConstants.CENTER);
		amountTextField.setBounds(187, 238, 202, 35);
		depositFrame.getContentPane().add(amountTextField);
		amountTextField.setColumns(10);
		
		//currencyLabel JLabel
		JLabel currencyLabel = new JLabel("Currency:");
		currencyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		currencyLabel.setBounds(440, 162, 119, 25);
		depositFrame.getContentPane().add(currencyLabel);
		
		//currencyComboBox JComboBox
		JComboBox currencyComboBox = new JComboBox();
		currencyComboBox.setModel(new DefaultComboBoxModel(new String[] {"Canadian Dollar", "US Dollar", "Renminbi"}));
		currencyComboBox.setBounds(440, 212, 340, 27);
		depositFrame.getContentPane().add(currencyComboBox);
		
		//errorLabel JLabel
		JLabel errorLabel = new JLabel("INVALID DEPOSIT | TRY AGAIN");
		errorLabel.setForeground(Color.RED);
		errorLabel.setEnabled(true);
		errorLabel.setVisible(false);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		errorLabel.setBounds(295, 283, 260, 38);
		depositFrame.getContentPane().add(errorLabel);
		
		//depositButton JButton
		JButton depositButton = new JButton("Deposit");
		depositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String amountString = amountTextField.getText(); //Stores what is entered into amountTextField into amountString
					double am = Double.parseDouble(amountString); //Tries to convert amountString to double from string and store it into am
					
					//If no amount is entered and it still contains 0.00
					if (amountString.equals("0.00")) {
						errorLabel.setVisible(true); //Show error label
					}
					
					//Else an amount is entered
					else {
						double amount;
						
						//If amount is in Canadian Dollar
						if (currencyComboBox.getSelectedItem().equals("Canadian Dollar")) {
							MoneyConvertCADToCAD money = new MoneyConvertCADToCAD(Double.parseDouble(amountString)); //Creates a MoneyConvertCADToCAD object
							
							amount = money.convert(); //Converts the amount from Canadian Dollar to Canadian Dollar and store it back into amount
						}
						
						//Else if amount is in US Dollar
						else if (currencyComboBox.getSelectedItem().equals("US Dollar")) {
							MoneyConvertUSDToCAD money = new MoneyConvertUSDToCAD(Double.parseDouble(amountString)); //Creates a MoneyConvertUSDToCAD object
							
							amount = money.convert(); //Converts the amount from US Dollar to Canadian Dollar and store it back into amount
						}
						
						//Else amount is in Renminbi
						else {
							MoneyConvertRenminbiToCAD money = new MoneyConvertRenminbiToCAD(Double.parseDouble(amountString)); //Creates a MoneyConvertRenminbiToCAD object
							
							amount = money.convert(); //Converts the amount from Renminbi to Canadian Dollar and store it back into amount
						}
						
						ArrayList<String> deposit = new ArrayList<>(); //Creates an ArrayList to store the info of the deposit
						
						bank.totalAmount = bank.totalAmount + amount; //Updates the total amount
						
						deposit.add("DEPOSIT"); //Adds the type deposit to the ArrayList
						deposit.add(monthComboBox.getSelectedItem() + "/" + dayComboBox.getSelectedItem()); //Adds the date to the ArrayList
						deposit.add("+ $" + Double.toString(amount)); //Adds the amount being deposited to the ArrayList
						deposit.add("$" + Double.toString(bank.totalAmount)); //Adds the total amount to the ArrayList
						
						bank.transactionLog.add(deposit); //Adds the ArrayList to the transaction log
						
						depositFrame.setVisible(false); //Hides the deposit screen
						
						bank.bankFrame.setVisible(true); //Shows the home screen
					}
				}
				
				catch (Exception e1) {
					errorLabel.setVisible(true);
				}
			}
		});
		depositButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		depositButton.setBounds(440, 43, 230, 50);
		buttonPanel.add(depositButton);
		
		//backButton JButton
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositFrame.setVisible(false); //Hides the deposit screen
				
				bank.bankFrame.setVisible(true); //Shows the home screen
			}
		});
		backButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		backButton.setBounds(180, 43, 230, 50);
		buttonPanel.add(backButton);
	}
}
