// not used in our final code

//import java.math.*;
//import java.security.*;
//public class BlindSignature {
//
//	static BigInteger r; // blinding factor
//	public static void main(String[] args) {
//
//		SecureRandom random = new SecureRandom();
//		r = KeyPairGeneration.generateCoprime(KeyPairGeneration.n, random); // must be relatively prime to public modulus
//		BigInteger message = BigInteger.valueOf(123456789); // hashed message
//		BigInteger blindedMessage = blind(message);
//		BigInteger signedMessage = sign(blindedMessage);
//		System.out.println("The message is " + message);
//		System.out.println("The blinding factor is " + r);
//		System.out.println("The blinded message is " + blindedMessage);
//		System.out.println("The signed message is " + signedMessage);
//		}
//
//	/**
//	 * Blinds a given message with a randomly chosen blinding factor
//	 * @param message is a BigInteger, which can be created by hashing a meaningful message
//	 * @return blinded message
//	 */
//	public static BigInteger blind(BigInteger message) {
//		BigInteger blinded = (message.multiply(r.modPow(KeyPairGeneration.e, KeyPairGeneration.n))).mod(KeyPairGeneration.n); // Alice takes r, a random blinding factor coprime to n, and computes mr^e (mod n)
//		return blinded;
//	}
//
//	/**
//	 * Computes the final signature, m^d (mod n)
//	 * @param blindedMessage (see above)
//	 * @return signature
//	 */
//	public static BigInteger sign(BigInteger blindedMessage) {
//		BigInteger tSig = blindedMessage.modPow(KeyPairGeneration.d, KeyPairGeneration.n); // Bob takes the blinded m' and computes s'=(m')^d (mod n)
//		BigInteger sig = tSig.multiply(r.modInverse(KeyPairGeneration.n)).mod(KeyPairGeneration.n); // Alice computes s'r^-1 (mod n), which is the signature
//		return sig; // this is m^d (mod n), where m is Alice's original message
//	}
//
//
//}
