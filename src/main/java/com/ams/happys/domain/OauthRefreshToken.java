package com.ams.happys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

//@Entity
//@Table(name="oauth_refresh_token")
//@XmlRootElement
public class OauthRefreshToken {
//	[token_id] [varchar](256) NULL,
//	[token] [varbinary](max) NULL,
//	[authentication] [varbinary](max) NULL
	
	@Column(name="token_id")
	private String tokenId;
	@Column(name = "token", nullable = true,length=16777125)
	private byte[] token;
	
	
}
