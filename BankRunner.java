import javax.swing.JFrame;
public class BankRunner {

	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bank.setSize(500, 500);
		bank.setVisible(true);
	}

}
