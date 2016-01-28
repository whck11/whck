package com.whck.service.email;

public interface EmailService {

	void sendCode(String email, String code) throws Exception;

}
