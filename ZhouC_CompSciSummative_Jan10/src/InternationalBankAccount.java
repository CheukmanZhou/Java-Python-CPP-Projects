/*
 Cheukman Zhou
 This program is used for the home screen of the International Bank Account GUI program
 Version 1.0
 January 23, 2022
*/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InternationalBankAccount {
	final int PIN = 1234; //PIN Number
	public static final String fileName = "SavedTransactionLog.txt"; //File Name
	
	public static ArrayList<ArrayList<String>> transactionLog = new ArrayList<>(); //Transaction Log
	public static double totalAmount; //Total Amount
	
	
	JFrame bankFrame;

	
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
	
	public InternationalBankAccount() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame
	 */
	
	private void initialize() {
		//bankFrame JFrame
		bankFrame = new JFrame();
		bankFrame.setBounds(100, 100, 850, 500);
		bankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bankFrame.getContentPane().setLayout(null);
		
		//titlePanel JPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 850, 140);
		titlePanel.setBackground(Color.BLUE);
		bankFrame.getContentPane().add(titlePanel);
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
		bankFrame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		//exitButton JButton
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToFile(); //Saves the transaction log to the file
				
				System.exit(0); //Ends the program
			}
		});
		exitButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		exitButton.setBounds(440, 43, 230, 50);
		buttonPanel.add(exitButton);
		
		//displayLogButton JButton
		JButton displayLogButton = new JButton("Display Log");
		displayLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayScreen display = new DisplayScreen(); //Creates a DisplayScreen object
				
				bankFrame.setVisible(false); //Hides the home screen
				
				display.displayFrame.setVisible(true); //Shows the display screen
			}
		});
		displayLogButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		displayLogButton.setBounds(180, 43, 230, 50);
		buttonPanel.add(displayLogButton);
		
		//depositButton JButton
		JButton depositButton = new JButton("Deposit");
		depositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositScreen deposit = new DepositScreen(); //Creates a DepositScreen object
				
				bankFrame.setVisible(false); //Hides the home screen
				
				deposit.depositFrame.setVisible(true); //Shows the deposit screen
			}
		});
		depositButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		depositButton.setBounds(180, 176, 230, 50);
		bankFrame.getContentPane().add(depositButton);
		
		//withdrawButton JButton
		JButton withdrawButton = new JButton("Withdraw");
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithdrawScreen withdraw = new WithdrawScreen(); //Creates a WithdrawScreen object
				
				bankFrame.setVisible(false); //Hides the home screen
				
				withdraw.withdrawFrame.setVisible(true); //Shows the withdraw screen
			}
		});
		withdrawButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		withdrawButton.setBounds(180, 250, 230, 50);
		bankFrame.getContentPane().add(withdrawButton);
		
		//sortLogButton JButton
		JButton sortLogButton = new JButton("Sort Log");
		sortLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortScreen sort = new SortScreen(); //Creates a SortScreen object
				
				bankFrame.setVisible(false); //Hides the home screen
				
				sort.sortFrame.setVisible(true); //Shows the sort screen
			}
		});
		sortLogButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		sortLogButton.setBounds(440, 176, 230, 50);
		bankFrame.getContentPane().add(sortLogButton);
		
		//findButton JButton
		JButton findButton = new JButton("Find");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindScreen find = new FindScreen(); //Creates a FindScreen object
				
				bankFrame.setVisible(false); //Hides the home screen
				
				find.findFrame.setVisible(true); //Shows the find screen
			}
		});
		findButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		findButton.setBounds(440, 250, 230, 50);
		bankFrame.getContentPane().add(findButton);
	}
	
	
	/*
	 Method: saveToFile
	 Description: The Method saves the transaction log to a file
	 Parameters: None
	 Returns: None
	*/
	
	public static void saveToFile() {
		PrintWriter print = null; //Creates a PrintWriter object

		try {
			print = new PrintWriter(new BufferedWriter(new FileWriter(fileName))); //Creates a writer for the file so that it can write text into that file
		}
		
		catch (IOException iox) {
			System.out.println("Problem writing " + fileName);
		}
		
		//Prints the transaction log into the file
		for (ArrayList<String> i : transactionLog) {
			String transaction = "";
			
			//Stores all the properties of the transaction in proper format before sending it into the file
			for (String k : i) {
				transaction = transaction + k + ";";
			}
			
			transaction = transaction.substring(0, (transaction.length() - 1)); //Gets rid of the last ";" at the end of transaction
			
			print.println(transaction); //Prints transaction into the file
		}
		
		print.close(); //Closes the writer
	}
	
	
	/*
	 Method: readInfo
	 Description: The Method reads the transaction log from the file and puts it back into the 2D ArrayList
	 Parameters: None
	 Returns: None
	*/
	
	public static void readInfo() {
		String line; //Stores what is being read from the file
		String delimiter = ";"; //Stores what the delimiter is
		
		try {
			BufferedReader read = new BufferedReader(new FileReader(fileName)); //Creates a reader for the file so that it can read text from that file

			line = read.readLine(); //Reads the first line of the file and stores it into line
			
			while (line != null) { //While not end of file
				ArrayList<String> transaction = new ArrayList<>(Arrays.asList(line.split(delimiter))); //Creates an ArrayList to store the transaction's properties
				
				transactionLog.add(transaction);
				
				line = read.readLine(); //Reads the next line in the file
			}
			
			read.close(); //Closes the reader
		}
		
		catch (IOException iox) {
			System.out.println("Problem reading " + fileName);
		}
	}
}
