package com.example.reliableevents.repository;

import com.example.reliableevents.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
