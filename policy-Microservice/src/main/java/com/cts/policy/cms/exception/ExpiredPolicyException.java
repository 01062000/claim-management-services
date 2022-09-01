package com.cts.policy.cms.exception;

public class ExpiredPolicyException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public ExpiredPolicyException(String msg)
	{
		super(msg);
	}

}
