package com.errorscentral.repository;

import com.errorscentral.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users" +
            " WHERE email = :email", nativeQuery = true)
    User findByUsername(@Param("email") String email);

}
