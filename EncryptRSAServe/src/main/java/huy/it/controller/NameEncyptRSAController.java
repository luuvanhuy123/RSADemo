package huy.it.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import huy.it.response.NameEncyptRSAResponse;
import huy.it.response.RSAKeyGenResponse;
import huy.it.service.NameEncyptRSAService;

@RestController
@RequestMapping("/encrypt/rsa/")
public class NameEncyptRSAController {

	@Autowired
	private NameEncyptRSAService nameEncyptRSAService;

	@Autowired
	private ObjectMapper mapper;

	@PostMapping(value = "name", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public NameEncyptRSAResponse getDecodeRSAByNameEncode(@RequestBody String fullNameEncode) {
		BigInteger[] encyptCharacterList = null;
		try {
			encyptCharacterList = mapper.readValue(fullNameEncode, BigInteger[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nameEncyptRSAService.decodeAllCharterNumberToFullName(encyptCharacterList);
	}

	@PostMapping(value = "genkey")
	public RSAKeyGenResponse genRSAKey(@RequestBody String publickey) {
		return nameEncyptRSAService.genRSAKey(publickey);
	}

}
