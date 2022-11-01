package com.ams.happys.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ams.happys.domain.PersonAddress;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, UUID> {

}
