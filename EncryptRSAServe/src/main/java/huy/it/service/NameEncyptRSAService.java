package huy.it.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import huy.it.response.NameEncyptRSAResponse;
import huy.it.response.RSAKeyGenResponse;
import huy.it.util.RSAUtil;
import lombok.Data;

@Service
@Data
public class NameEncyptRSAService {

	/* public key is at least a certain value input by the user */
	private static final String BLANK = "";

	/**
	 * decode rsa code to full name
	 * 
	 * @param characterNumberArr
	 * @return full name after decode
	 */
	public NameEncyptRSAResponse decodeAllCharterNumberToFullName(BigInteger[] bigB_cipherValList) {
		String fullName = NameEncyptRSAService.BLANK;
		for (BigInteger bigB_cipherVal : bigB_cipherValList) {
			fullName += RSAUtil.decodeRSA(bigB_cipherVal);
		}
		return new NameEncyptRSAResponse(fullName);
	}

	public RSAKeyGenResponse genRSAKey(String publickey) {
		return RSAUtil.genRSAKey(publickey);
	}

}
