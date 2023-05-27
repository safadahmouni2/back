package com.thinktank.pts.qaservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByEnabledTrue();

	Optional<User> findByUsernameAndEnabledIsTrue(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query("select u from User u where u.firstName = :fn")
	User findByUserFirstName(String fn);

}
