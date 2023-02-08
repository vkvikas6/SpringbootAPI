package com.artiinfosystem.net.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artiinfosystem.net.model.JwtUser;

public interface UserRepository extends JpaRepository<JwtUser, Long> {

	JwtUser findByUserName(String userName);
}
