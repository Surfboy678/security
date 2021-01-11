package com.brodacki.security.repository;

import com.brodacki.security.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

  VerificationToken findByValue(String value);
}
