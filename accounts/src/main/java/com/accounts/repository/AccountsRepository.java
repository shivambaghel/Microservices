package com.accounts.repository;

import com.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository  extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional  //  as data getting modified, in case of failure rollback logic can trigger in case of failure
    @Modifying  // it will tell to JPA , that these method going to modify the data
    void deleteByCustomerId(Long customerId);
}
