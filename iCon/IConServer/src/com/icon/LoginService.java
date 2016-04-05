package com.icon;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/login")
public class LoginService {

	@GET
	@Path("{user}/{pwd}/{otp}")
	@Produces(MediaType.TEXT_PLAIN)
	public String validate(@PathParam("user") String user, @PathParam("pwd") String pwd, @PathParam("otp") String otp){
		User user1 = new User();
		user1.setUserId("Client1");
		user1.setPassword("abc123");
		user1.setSecretCode("ABCDEFGHIJ");
		
		long callTime = System.currentTimeMillis() / 1000 / 30;
		
		
		
		try {
			if (user.equals(user1.getUserId()) && pwd.equals(user1.getPassword()) 
					&& TOTPGenerator.validateCode(new Long(otp), user1.getSecretCode().getBytes(), callTime)
					) {
				return "true";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
	}
}




class User {
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecretCode() {
		return SecretCode;
	}
	public void setSecretCode(String secretCode) {
		SecretCode = secretCode;
	}
	private String password;
	private String SecretCode;
	
}

class Response {
	private String status;
	private String message;
	
	Response(String status, String message){
		this.status=status;
		this.message=message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}