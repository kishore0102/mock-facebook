package edu.happydev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.happydev.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findByEmail(String email);

}
