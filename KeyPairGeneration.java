import java.math.*;
import java.security.*;
public class KeyPairGeneration {

	// I use all BigIntegers here (even though e,d probably don't need to) for consistency
	private BigInteger p; // first private large prime
	private BigInteger q; // second private large prime
	private BigInteger n; // public modulus, n=p*q
	private BigInteger m; // phi of the modulus
	private BigInteger e; // public exponent
	private BigInteger d; // private exponent
	private static final int keysize = 256;
	
	public KeyPairGeneration() {
		SecureRandom random = new SecureRandom();

		p = BigInteger.probablePrime(keysize/2, random);
		q = BigInteger.probablePrime(keysize/2, random);

		n = p.multiply(q);
		m = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));

		e = generateCoprime(m, random);
		d = e.modInverse(m);
	}


	public static BigInteger generateCoprime(BigInteger m, SecureRandom random) {
		int length = m.bitLength()-1;
		BigInteger e = BigInteger.probablePrime(length,random);
		while (!(m.gcd(e)).equals(BigInteger.ONE) ) {
			e = BigInteger.probablePrime(length,random);
		}
		return e;
	}

	public BigInteger[] generatePublicKeys() {	
		return new BigInteger[] {n, e};
	}

	public BigInteger[] generatePrivateKeys() {
		return new BigInteger[] {p, q, d};
	}




}
