package com.ams.happys.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
//@Entity
//@Table(name = "oauth_approvals")
//@XmlRootElement
public class OauthApprovals {
	@Column(name="userId")
	private String userId;
	@Column(name="clientId")
	private String clientId;
	@Column(name="scope")
	private String scope;
	@Column(name="status")
	private String status;
	@Column(name="expiresAt")
	private Date expiresAt;
	@Column(name="lastModifiedAt")
	private Date lastModifiedAt;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}
	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	
}
