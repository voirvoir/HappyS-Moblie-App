package com.ams.happys.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ams.happys.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select new User(u.id, u.username, u.email, u.accountNonLocked) from User u where " + " u.username like ?1 "
			+ " OR u.email like ?1" + " OR u.person.displayName like ?1 " + " OR ?1='' ")
	Page<User> searchByPageBasicInfo(Pageable pageable, String userName);

	@Query("select new User(u.id, u.username, u.email, u.accountNonLocked) from User u")
	Page<User> findByPageBasicInfo(Pageable pageable);

	@Query("select u from User u left join fetch u.roles where u.username = ?1")
	User findByUsername(String username);

	@Query("select u from User u left join fetch u.roles left join fetch u.person where u.username = ?1")
	User findByUsernameAndPerson(String username);

	@Query(value = "select u from User u where u.email=:email")
	User findByEmail(@Param("email") String email);

	@Query("select u from User u left join fetch u.roles where u.id = ?1")
	Optional<User> findById(Long id);
}
