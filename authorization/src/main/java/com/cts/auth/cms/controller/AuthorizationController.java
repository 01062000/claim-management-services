package com.cts.auth.cms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.auth.cms.model.AuthenticationRequestDTO;
import com.cts.auth.cms.exceptions.LoginException;
import com.cts.auth.cms.model.AuthenticationRequest;
import com.cts.auth.cms.model.AuthenticationResponse;
import com.cts.auth.cms.repository.AuthRequestRepo;
import com.cts.auth.cms.service.AppUserDetailsService;
import com.cts.auth.cms.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Login and Validation endpoints for Authorization Service")
@CrossOrigin
public class AuthorizationController {

	@Autowired
	private AppUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private AuthRequestRepo aRepo;
	
	@PostMapping("/login")
	@ApiOperation(value = "customerLogin", notes = "takes customer credentials and generates the unique JWT for each customer", httpMethod = "POST", response = ResponseEntity.class)
	public ResponseEntity<Object> createAuthorizationToken(
			@ApiParam(name = "customerLoginCredentials", value = "Login credentials of the Customer") @RequestBody AuthenticationRequestDTO authenticationRequestDTO)
			throws LoginException {
		
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		Optional<AuthenticationRequest> findByUserName = aRepo.findByUsername(authenticationRequestDTO.getUsername());
		String userId = findByUserName.get().getUserId();
		authenticationResponse.setMemberId(userId);
		authenticationRequest.setUsername(authenticationRequestDTO.getUsername());
		authenticationRequest.setPassword(authenticationRequestDTO.getPassword());
		
		log.info("BEGIN - [login(customerLoginCredentials)]");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		log.debug("{}", userDetails);
		
		if (userDetails.getPassword().equals(authenticationRequest.getPassword())) {
			log.info("END - [login(customerLoginCredentials)]");
			return new ResponseEntity<>(new AuthenticationResponse(authenticationResponse.getMemberId(), authenticationRequest.getUsername(),
					jwtTokenUtil.generateToken(userDetails)), HttpStatus.OK);
		} 
		
		log.info("END - [login(customerLoginCredentials)]");
		throw new LoginException("Invalid Username or Password");
	}
	

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "tokenValidation", notes = "returns boolean after validating JWT", httpMethod = "GET", response = ResponseEntity.class)
	public Boolean authorizeTheRequest(
			@ApiParam(name = "Authorization", value = "JWT generated for current customer present") @RequestHeader(name = "Authorization") String tokenDup) {
		
		log.info("BEGIN - [validatingAuthorizationToken(JWT-token)]");
		String token = tokenDup.substring(7);
		try {
			UserDetails user = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token));
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, user))) {
				log.debug("Token matched is Valid");
				log.info("Token matched is Valid");
				log.info("END - validate()");
				return true;
			} else {
				throw new LoginException("Invalid Token");
			}
		} catch (Exception e) {
			log.debug("Invalid token - Bad Credentials Exception");
			log.info("END Exception - validatingAuthorizationToken()");
			return false;
		}
	}
}
