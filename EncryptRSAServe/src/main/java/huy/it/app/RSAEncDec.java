package huy.it.app;

import java.math.BigInteger;

class RSAEncDec {

	public static void main(String[] args) {
		BigInteger bigB_pubKey = new BigInteger("37");
		BigInteger bigB_prvKey = new BigInteger("8332627540828844845");
		BigInteger bigB_n = new BigInteger("12846134132650319099");
		for (String s : "huy".split("")) {
			int asciiVal = (int) s.charAt(0);

			/*
			 * encrypting an ASCII integer value using the public key and decrypting the
			 * cipher value using the private key and extracting the ASCII value back
			 */

			BigInteger bigB_val = new BigInteger("" + asciiVal);
			BigInteger bigB_cipherVal = bigB_val.modPow(bigB_pubKey, bigB_n);

			System.out.println("ciphertext: " + bigB_cipherVal);

			BigInteger bigB_plainVal = bigB_cipherVal.modPow(bigB_prvKey, bigB_n);
			int plainVal = bigB_plainVal.intValue();

			System.out.println("plaintext:  " + (char) plainVal);
		}

	}
}