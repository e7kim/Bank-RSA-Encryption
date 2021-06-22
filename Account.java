//test

import java.math.BigInteger;

public class Account {
	
	private String username; // Account's username
	private char[] password; // Account's password stored using a char array
	private BigInteger[] pubKey; // Account's public key pair stored in a BigInteger array
	private BigInteger[] privKey; // Account's private key pair stored in a BigInteger array
	private long balance;
	
	public Account(String user, char[] pass, BigInteger[] pubKey, BigInteger[] privKey) {
		username = user;
		password = pass;
		this.pubKey = pubKey;
		this.privKey = privKey;
		balance = 1000000; // for purposes of this simulation, each user starts out with 1 mil dollars (balance can go into negatives)
	}
	
	public String getUser() {
		return username;
	}
	
	public char[] getPass() {
		return password;
	}
	
	public BigInteger[] getPubKey() {
		return pubKey;
	}
	
	public BigInteger[] getPrivKey() {
		return privKey;
	}
	
	public long getBalance() {
		return balance;
	}
	
	public void updateBalance(long newBalance) {
		balance = newBalance;
	}
	
	public BigInteger signMessage(long amount) {
		BigInteger am = BigInteger.valueOf(amount); // create BigInteger variable with value amount
		BigInteger d = privKey[2];
		BigInteger m = pubKey[0];
		return (am.modPow(d, m));
	}
}