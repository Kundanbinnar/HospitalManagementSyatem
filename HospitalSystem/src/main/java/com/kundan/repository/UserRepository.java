package com.kundan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kundan.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserNameAndPassword(String userName, String password);
}
