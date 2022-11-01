package com.ams.happys.domain;

import javax.persistence.PostRemove;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.joda.time.LocalDateTime;
import org.springframework.security.core.userdetails.UserDetails;

import com.ams.happys.utils.CommonUtils;
import com.ams.happys.utils.SecurityUtils;

public class EntityAuditListener {

	@PostRemove
	public void postRemove(AuditableEntity auditableEntity) {
		//System.out.println("postRemove:"+auditableEntity.toString());
		
		LocalDateTime now = LocalDateTime.now();
		auditableEntity.setModifyDate(now);
		//User user = SecurityUtils.getCurrentUser();
		Object principal = SecurityUtils.getPrincipal();
		if (CommonUtils.isNotNull(principal)) {
			//auditableEntity.setModifiedBy(principal.toString());
			if(principal instanceof  UserDetails) {
				UserDetails user = (UserDetails)principal;
				System.out.println("After Remove " +auditableEntity.toString()+" by"+user.getUsername());
			}
			else {
				System.out.println("After Remove " +auditableEntity.toString()+" by"+principal);
			}			
		}
	}
	@PrePersist
	public void beforePersit(AuditableEntity auditableEntity) {

		 LocalDateTime now = LocalDateTime.now();

		auditableEntity.setCreateDate(now);
		auditableEntity.setModifyDate(now);

		//User user = SecurityUtils.getCurrentUser();
		Object principal = SecurityUtils.getPrincipal();
		if (CommonUtils.isNotNull(principal)) {
			if(principal instanceof  UserDetails) {
				UserDetails user = (UserDetails)principal;
				auditableEntity.setCreatedBy(user.getUsername());
				auditableEntity.setModifiedBy(user.getUsername());				
			}
			else {
				auditableEntity.setCreatedBy(principal.toString());
				auditableEntity.setModifiedBy(principal.toString());
			}

		} else {
			auditableEntity.setCreatedBy("admin");
		}
	}

	@PreUpdate
	public void beforeMerge(AuditableEntity auditableEntity) {
		LocalDateTime now = LocalDateTime.now();
		auditableEntity.setModifyDate(now);
		//User user = SecurityUtils.getCurrentUser();
		Object principal = SecurityUtils.getPrincipal();
		if (CommonUtils.isNotNull(principal)) {
			//auditableEntity.setModifiedBy(principal.toString());
			if(principal instanceof  UserDetails) {
				UserDetails user = (UserDetails)principal;
				auditableEntity.setCreatedBy(user.getUsername());
				auditableEntity.setModifiedBy(user.getUsername());				
			}
			else {
				auditableEntity.setCreatedBy(principal.toString());
				auditableEntity.setModifiedBy(principal.toString());
			}			
		}
	}
//	@PreRemove
//	public void beforeRemove(AuditableEntity auditableEntity) {
//		LocalDateTime now = LocalDateTime.now();
//		auditableEntity.setModifyDate(now);
//		//User user = SecurityUtils.getCurrentUser();
//		Object principal = SecurityUtils.getPrincipal();
//		if (CommonUtils.isNotNull(principal)) {
//			//auditableEntity.setModifiedBy(principal.toString());
//			if(principal instanceof  UserDetails) {
//				UserDetails user = (UserDetails)principal;
//				System.out.println("Remove " +auditableEntity.toString()+" by"+user.getUsername());
//			}
//			else {
//				System.out.println("Remove " +auditableEntity.toString()+" by"+principal);
//			}			
//		}
//	}
}
