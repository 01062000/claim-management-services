package com.cts.auth.cms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationRequestDTO {
	private String username;
	private String password;
}
