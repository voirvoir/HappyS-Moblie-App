package com.ams.happys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ams.happys.domain.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
