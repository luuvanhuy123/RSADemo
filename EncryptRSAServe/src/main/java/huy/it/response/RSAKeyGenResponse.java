package huy.it.response;

import lombok.Data;

@Data
public class RSAKeyGenResponse {
	private String n_value;
	private String clientPublicKey;
}
