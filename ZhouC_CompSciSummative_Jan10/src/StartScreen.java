/*
 Cheukman Zhou
 This program is used for the start screen of the International Bank Account GUI program
 Version 1.0
 January 22, 2022 
*/

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StartScreen {
	private JFrame startFrame;
	private JTextField pinTextField;

	
	/**
	 * Launch the application
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.startFrame.setVisible(true);
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
	
	public StartScreen() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame
	 */
	
	private void initialize() {
		//startFrame JFrame
		startFrame = new JFrame();
		startFrame.setBounds(100, 100, 850, 500);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.getContentPane().setLayout(null);

		//titlePanel JPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 850, 140);
		titlePanel.setBackground(Color.BLUE);
		startFrame.getContentPane().add(titlePanel);
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
		startFrame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);

		//pinLabel JLabel
		JLabel pinLabel = new JLabel("Enter PIN:");
		pinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pinLabel.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		pinLabel.setBounds(345, 183, 160, 38);
		startFrame.getContentPane().add(pinLabel);

		//pinTextField JTextField
		pinTextField = new JTextField();
		pinTextField.setText("0000");
		pinTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		pinTextField.setHorizontalAlignment(SwingConstants.CENTER);
		pinTextField.setBounds(345, 233, 160, 38);
		startFrame.getContentPane().add(pinTextField);
		pinTextField.setColumns(10);

		//errorLabel JLabel
		JLabel errorLabel = new JLabel("INVALID PIN | TRY AGAIN");
		errorLabel.setForeground(Color.RED);
		errorLabel.setEnabled(true);
		errorLabel.setVisible(false);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		errorLabel.setBounds(295, 283, 260, 38);
		startFrame.getContentPane().add(errorLabel);

		//continueButton JButton
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InternationalBankAccount bank = new InternationalBankAccount(); //Creates an InternationalBankAccount object

				try {
					String pinString = pinTextField.getText(); //Stores what is entered in pinTextField into pinString
					int pin = Integer.parseInt(pinString); //Tries to convert pinString into an integer and store it into pin

					//If no pin is entered and is still 0000, then display error label
					if (pinString.equals("0000")) {
						errorLabel.setVisible(true);
					}

					//Else if pinString is equal to the pin number
					else if (pinString.equals(Integer.toString(bank.PIN))) {
						startFrame.setVisible(false); //Hides the start frame

						bank.bankFrame.setVisible(true); //Shows the home frame

						bank.readInfo(); //Reads the file to get the transaction log

						//Finds the total amount of the saved transaction log
						String amountString = bank.transactionLog.get(bank.transactionLog.size() - 1).get(3); //Gets the final total amount recorded on the transaction log
						amountString = amountString.substring(1); //Takes off the dollar sign from the total amount

						bank.totalAmount = Double.parseDouble(amountString); //Converts amountString to a double from a string and stores it into totalAmount
					}

					//Else pin is wrong
					else {
						errorLabel.setVisible(true);
					}
				}

				catch (Exception e1) {
					errorLabel.setVisible(true);
				}
			}
		});
		continueButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		continueButton.setBounds(180, 43, 230, 50);
		buttonPanel.add(continueButton);

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //Ends the program
			}
		});
		exitButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		exitButton.setBounds(440, 43, 230, 50);
		buttonPanel.add(exitButton);
	}
}
