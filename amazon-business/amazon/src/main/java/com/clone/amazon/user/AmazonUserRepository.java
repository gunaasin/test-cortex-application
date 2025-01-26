package com.clone.amazon.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AmazonUserRepository extends JpaRepository<AmazonUser,Integer> {

    @Query("SELECT u FROM AmazonUser u WHERE LOWER(u.email) = LOWER(:email)")
     AmazonUser findByEmail(@Param("email") String email);
}
