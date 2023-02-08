package com.artiinfosystem.net.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class JwtRequest {
	
	public JwtRequest() {
		// TODO Auto-generated constructor stub
	}

	private String username;
	private String password;
}
