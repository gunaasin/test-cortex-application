package com.clone.amazon.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmazonUserRepository extends JpaRepository<AmazonUser,Integer> {
}
