import java.util.*;
import java.math.BigInteger;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Bank extends JFrame {
	private JTextField usernameField;
	private JButton register;
	private JButton login;
	private JLabel loginMessage;
	private JPasswordField passwordField;
	private JButton registerEnter;
	private JButton loginEnter;
	private JLabel invalidUserPass;
	private JLabel welcome;
	private JLabel balance;
	private JButton printDirectory;
	private List<Account> directory;
	private JButton logout;
	private JButton sendmoney;
	private JTextField amount;
	private JTextField recipient;
	private JButton confirmtransaction;
	private List<JLabel> accountlist;
	private JButton deleteaccount;
	private Account currUser;

	public Bank() {
		super("Banking Simulation");
		setLayout(new FlowLayout());
		directory = new ArrayList<>();

		loginMessage = new JLabel();
		loginMessage.setText("Choose a username (enter on the left) and a password (enter on the right)");
		loginMessage.setVisible(false);
		add(loginMessage);

		register = new JButton();
		register.setText("Click here to register an account");
		register.setSize(200, 200);
		add(register);

		login = new JButton();
		login.setText("Click here to log in");
		login.setSize(200, 200);
		add(login);

		usernameField = new JTextField();
		usernameField.setVisible(false);
		add(usernameField);

		passwordField = new JPasswordField();
		passwordField.setVisible(false);
		add(passwordField);

		invalidUserPass = new JLabel();
		invalidUserPass.setText("Invalid User/Pass");
		invalidUserPass.setVisible(false);
		add(invalidUserPass);

		registerEnter = new JButton();
		registerEnter.setText("Enter");
		registerEnter.setVisible(false);
		add(registerEnter);

		loginEnter = new JButton();
		loginEnter.setText("Enter");
		loginEnter.setVisible(false);
		add(loginEnter);

		welcome = new JLabel();
		welcome.setText("");
		welcome.setVisible(false);
		add(welcome);

		balance = new JLabel();
		balance.setText("");
		balance.setVisible(false);
		add(balance);
		
		accountlist = new ArrayList<>();

		printDirectory = new JButton();
		printDirectory.setText("Print Directory");
		printDirectory.setVisible(true);
		add(printDirectory);

		sendmoney = new JButton();
		sendmoney.setText("Send Money");
		sendmoney.setVisible(false);
		add(sendmoney);

		amount = new JTextField();
		amount.setVisible(false);
		add(amount);

		recipient = new JTextField();
		recipient.setVisible(false);
		add(recipient);

		confirmtransaction = new JButton();
		confirmtransaction.setText("Confirm Transaction");
		confirmtransaction.setVisible(false);
		add(confirmtransaction);
		
		deleteaccount = new JButton();
		deleteaccount.setText("Delete Account");
		deleteaccount.setVisible(false);
		add(deleteaccount);

		logout = new JButton();
		logout.setText("");
		logout.setVisible(false);
		add(logout);

		/**
		 * Opens up the register menu by showing the usernameField and passwordFields
		 */
		register.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				register.setVisible(false);
				login.setVisible(false);
				usernameField.setText("(Username):                  ");
				usernameField.setVisible(true);
				passwordField.setText("mypass               ");
				passwordField.setVisible(true);
				loginMessage.setVisible(true);
				registerEnter.setVisible(true);
				logout.setText("back");
				logout.setVisible(true);
			}

		});

		/**
		 * Confirms registration while preventing duplicate usernames
		 */
		registerEnter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				printDirectory.setVisible(true);
				boolean validUser = true;
				KeyPairGeneration kp = new KeyPairGeneration();
				for (Account a : directory) {
					if (a.getUser().equals(usernameField.getText())) {
						validUser = false;
						break;
					}
				}

				if(validUser) {
					directory.add(new Account(usernameField.getText(), passwordField.getPassword(), kp.generatePublicKeys(), kp.generatePrivateKeys()));
					register.setVisible(true);
					login.setVisible(true);
					usernameField.setVisible(false);
					passwordField.setVisible(false);
					loginMessage.setVisible(false);
					registerEnter.setVisible(false);
					logout.setVisible(false);
					invalidUserPass.setVisible(false);

				}
				else {
					invalidUserPass.setVisible(true);
					invalidUserPass.setText("Invalid Username");
					usernameField.setText("(Username):                  ");
					passwordField.setText("mypass               ");
				}
			}
		});

		/**
		 * Opens up login menu with usernameField and passwordFields
		 */
		login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				register.setVisible(false);
				login.setVisible(false);
				loginMessage.setText("Enter your username (on the left) and your password (on the right)");
				usernameField.setText("(Username):                  ");
				usernameField.setVisible(true);
				passwordField.setText("mypass               ");
				passwordField.setVisible(true);
				loginMessage.setVisible(true);
				loginEnter.setVisible(true);
				logout.setText("back");
				logout.setVisible(true);
			}

		});

		/**
		 * Verifies login information and enters account if verified.
		 */
		loginEnter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String user = usernameField.getText();
				char[] pass = passwordField.getPassword();
				boolean exists = false;
				for (Account a : directory)
				{
					boolean match = user.equals(a.getUser()) && Arrays.equals(pass, a.getPass());
					if (match)
					{
						currUser = a;
						usernameField.setVisible(false);
						passwordField.setVisible(false);
						loginMessage.setVisible(false);
						loginEnter.setVisible(false);
						invalidUserPass.setVisible(false);
						welcome.setText("Welcome " + currUser.getUser() + "!");
						balance.setText("Your balance is " + a.getBalance());
						balance.setVisible(true);
						welcome.setVisible(true);
						sendmoney.setVisible(true);
						deleteaccount.setVisible(true);
						logout.setText("logout");
						logout.setVisible(true);
						exists = true;
						break;
					}
				}
				if (!exists)
				{
					usernameField.setText("(Username):                  ");
					passwordField.setText("mypass               ");
					invalidUserPass.setText("Invalid User/Pass");
					invalidUserPass.setVisible(true);
				}

			}
		});
		
		/**
		 * A button within a user's account menu. Allows the user (already logged in) to delete one's account.
		 */
		deleteaccount.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				directory.remove(currUser);
				register.setVisible(true);
				login.setVisible(true);
				usernameField.setVisible(false);
				passwordField.setVisible(false);
				loginMessage.setVisible(false);
				registerEnter.setVisible(false);
				logout.setVisible(false);
				loginEnter.setVisible(false);
				sendmoney.setVisible(false);
				invalidUserPass.setVisible(false);
				balance.setVisible(false);
				welcome.setVisible(false);
				deleteaccount.setVisible(false);
			}

		});

		/**
		 * Logs out user and leaves account menu.
		 */
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				register.setVisible(true);
				login.setVisible(true);
				usernameField.setVisible(false);
				passwordField.setVisible(false);
				loginMessage.setVisible(false);
				registerEnter.setVisible(false);
				logout.setVisible(false);
				loginEnter.setVisible(false);
				sendmoney.setVisible(false);
				invalidUserPass.setVisible(false);
				balance.setVisible(false);
				welcome.setVisible(false);
				deleteaccount.setVisible(false);
			}
		});

		/**
		 * Opens up transaction menu with the amount and recipient windows.
		 */
		sendmoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				welcome.setVisible(false);
				sendmoney.setVisible(false);
				amount.setText("amount:          ");
				amount.setVisible(true);
				recipient.setText("recipient name:        "); // have to enter recipient letter for letter (case sensitive)
				recipient.setVisible(true);
				confirmtransaction.setVisible(true);
			}
		});

		/**
		 * Verifies a transaction by checking that the recipient exists, and making the appropriate changes to the sender and recipient balances.
		 */
		confirmtransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Integer am = Integer.parseInt(amount.getText());
				String recip = recipient.getText(); 
				amount.setVisible(false);
				recipient.setVisible(false);
				confirmtransaction.setVisible(false);
				/**
				 * Here the bank checks and validates the signature against the amount
				 * For the purposes of this simulation, the Bank/clients are always honest
				 */
				boolean verified = false;
				Account sender = directory.get(0); // initialize
				BigInteger signature = BigInteger.ONE;
				for(Account a : directory) {
					if(a.getUser().equals(usernameField.getText())) {
						sender = a;
						signature = a.signMessage(am.intValue());
						BigInteger plainAmount = signature.modPow(a.getPubKey()[1], a.getPubKey()[0]); 
						if(plainAmount.longValue() == am) {
							verified = true;
						}
						break;
					}
				}
				Account receiver = directory.get(0); // initialize
				for(Account a : directory) {
					if(a.getUser().equals(recip)) {
						receiver = a;
						break;
					}
				}
				// transfer money 
				if(verified) {
					sender.updateBalance(sender.getBalance() - am);
					receiver.updateBalance(receiver.getBalance() + am);
					balance.setText("Your balance is " + currUser.getBalance());
				}





			}
		});

		/**
		 * Loops through the accounts in directory and prints their username and public key.
		 */
		printDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for (JLabel L : accountlist)
				{
					L.setVisible(false);
					remove(L);
				}
				for (Account a : directory) {
					JLabel L = new JLabel();
					add(L);
					L.setText("Username: " + a.getUser() + ", n: " + a.getPubKey()[0] + ", e: " + a.getPubKey()[1]);
					L.setVisible(true);
					accountlist.add(L);
				}


			}


		});
	}

}