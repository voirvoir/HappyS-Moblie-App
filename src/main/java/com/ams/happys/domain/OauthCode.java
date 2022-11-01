package com.ams.happys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
//@Entity
//@Table(name = "oauth_code")
//@XmlRootElement
public class OauthCode {
	private String code;
	@Column(name = "authentication", nullable = true,length=16777125)
	private byte[] authentication;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public byte[] getAuthentication() {
		return authentication;
	}
	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}
	
}
