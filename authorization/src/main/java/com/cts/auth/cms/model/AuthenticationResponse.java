package com.cts.auth.cms.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Creating model class for generating authorization token when any customer logs in")

public class AuthenticationResponse {

	@ApiModelProperty(value = "member Id of the Customer doing login")
    private String memberId;
	
    @ApiModelProperty(value = "Username of the Customer doing login")
    private String username;
    
    @ApiModelProperty(value = "Authorization token of the customer")
    private String jwtAuthToken;
}
