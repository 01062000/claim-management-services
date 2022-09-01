package com.cts.auth.cms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.auth.cms.model.AuthenticationRequestDTO;
import com.cts.auth.cms.exceptions.LoginException;
import com.cts.auth.cms.repository.AuthRequestRepo;
import com.cts.auth.cms.service.AppUserDetailsService;
import com.cts.auth.cms.util.JwtUtil;

import io.jsonwebtoken.SignatureException;

@SpringBootTest
class AuthorizationControllerTest {

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private AppUserDetailsService appUserDetailsService;

	@Mock
	private AuthRequestRepo authRequestRepo;

	@InjectMocks
	private AuthorizationController authenticationController;

	@Test
	void testValidLogin() throws LoginException {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("adyasha1", "adyasha1");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword(),
				new ArrayList<>());

		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);
		when(jwtUtil.generateToken(userDetails)).thenReturn("dummy-token");

		//AuthenticationResponse authenticationResponse = authenticationController.createAuthorizationToken(authenticationRequestDTO);
		//assertEquals(HttpStatus.OK, authenticationResponse);
	}
	
	@Test
	void testInvalidLogin() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("adyasha1", "abhishek2");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), "adyasha1", new ArrayList<>());
		
		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);
		//Exception exception = Assertions.assertThrows(LoginException.class, () -> authenticationController.createAuthorizationToken(authenticationRequestDTO));
		//assertEquals("Invalid Username or Password", exception.getMessage());
	}

	@Test
	void testValidToken() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("adyasha1", "adyasha1");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword(),
				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(true);
		when(jwtUtil.extractUsername("token")).thenReturn("adyasha1");
		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);

		Boolean validity = authenticationController.authorizeTheRequest("Bearer token");
		assertTrue(validity.toString().contains("true"));
	}

	@Test
	void testInvalidToken() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("adyasha1", "adyasha1");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword(),
				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(false);
		when(jwtUtil.extractUsername("token")).thenReturn("adyasha1");
		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);

		Boolean validity = authenticationController.authorizeTheRequest("Bearer token");
		assertEquals(true, validity.toString().contains("false"));
	}

	@Test
	void testInvalidTokenWhenSignatureInvalid() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("adyasha1", "adyasha1");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword(),
				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenThrow(SignatureException.class);
		when(jwtUtil.extractUsername("token")).thenReturn("adyasha1");
		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);

		Boolean validity = authenticationController.authorizeTheRequest("Bearer token");
		assertEquals(true, validity.toString().contains("false"));
	}
}
