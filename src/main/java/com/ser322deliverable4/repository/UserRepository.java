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

    /**
     * returns wrapped in Optional in case User is null
     * @param userId
     * @return
     */
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    Optional<User> findUserById(@Param("userId") Long userId);

    /**
     * returns wrapped in Optional in case User is null
     * @param userId
     * @return
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);


    /**
     * returns number of rows effected
     * @param userId
     * @param firstName
     * @param lastName
     * @return
     */
    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName WHERE u.id = :userId")
    @Modifying
    @Transactional
    int updateUserNames(@Param("userId") Long userId, @Param("firstName") String firstName, @Param("lastName") String lastName);

    /**
     * returns number of rows effected
     * @param userId
     * @return
     */
    @Query("DELETE FROM User u WHERE u.id = :userId")
    @Modifying
    @Transactional
    int deleteUserById(@Param("userId") Long userId);

}
