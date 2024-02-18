package com.ser322deliverable4.repository;

import com.ser322deliverable4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("INSERT INTO User(email, firstName, lastName) VALUES (:email, :firstName, :lastName)")
    @Modifying
    @Transactional
    void insertNewUser(
            @Param("email") String email,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName
    );

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    Optional<User> findUserById(@Param("userId") Long userId);

    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName WHERE u.id = :userId")
    @Modifying
    @Transactional
    int updateUserNames(@Param("userId") Long userId, @Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("DELETE FROM User u WHERE u.id = :userId")
    @Modifying
    @Transactional
    int deleteUserById(@Param("userId") Long userId);

}
