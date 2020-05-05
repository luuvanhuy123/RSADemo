package huy.it.util;

import java.math.BigInteger;
import java.util.Random;

import huy.it.response.RSAKeyGenResponse;

public class RSAUtil {
	private static BigInteger bigB_prvKey;

	private static BigInteger bigB_n;

	public static RSAKeyGenResponse genRSAKey(String strPublicKey) {

		/* Creating two random number generators, each with a different seed */
		Random rand1 = new Random(System.currentTimeMillis());
		Random rand2 = new Random(System.currentTimeMillis() * 10);

		int pubKey = Integer.parseInt(strPublicKey); /* public key is at least a certain value input by the user */

		/* returns a BigInteger that is not prime with probability less than 2^(-100) */
		BigInteger bigB_p = BigInteger.probablePrime(32, rand1);
		BigInteger bigB_q = BigInteger.probablePrime(32, rand2);
		BigInteger bigB_n = bigB_p.multiply(bigB_q);

		BigInteger bigB_p_1 = bigB_p.subtract(new BigInteger("1")); // p-1
		BigInteger bigB_q_1 = bigB_q.subtract(new BigInteger("1")); // q-1
		BigInteger bigB_p_1_q_1 = bigB_p_1.multiply(bigB_q_1); // (p-1)*(q-1)

		// generating the correct public key

		while (true) {
			BigInteger BigB_GCD = bigB_p_1_q_1.gcd(new BigInteger("" + pubKey));

			if (BigB_GCD.equals(BigInteger.ONE)) {
				break;
			}
			pubKey++;
		}

		BigInteger bigB_pubKey = new BigInteger("" + pubKey);
		BigInteger bigB_prvKey = bigB_pubKey.modInverse(bigB_p_1_q_1);

		System.out.println(" public key : " + bigB_pubKey + " , " + bigB_n);
		System.out.println(" private key: " + bigB_prvKey + " , " + bigB_n);
		// store private key and n value for serve
		RSAUtil.bigB_prvKey = bigB_prvKey;
		RSAUtil.bigB_n = bigB_n;
		// set response public key and n value for client
		RSAKeyGenResponse response = new RSAKeyGenResponse();
		response.setClientPublicKey(bigB_pubKey.toString());
		response.setN_value(bigB_n.toString());
		return response;
	}

	public static Character decodeRSA(BigInteger bigB_cipherVal) {
		if (RSAUtil.bigB_prvKey == null || RSAUtil.bigB_n == null) {
			return null;
		}
		BigInteger bigB_plainVal = bigB_cipherVal.modPow(RSAUtil.bigB_prvKey, RSAUtil.bigB_n);
		return (char) bigB_plainVal.intValue();
	}
}
