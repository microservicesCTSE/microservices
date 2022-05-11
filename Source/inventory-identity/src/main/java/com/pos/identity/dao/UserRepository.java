package com.pos.identity.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pos.identity.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String userName);
    User findByUsernameAndIsActiveIsTrueAndIsApprovedTrueAndIsLockedFalseAndIsVerifiedTrueOrUsernameAndIsActiveIsTrueAndIsApprovedTrueAndIsLockedNullAndIsVerifiedTrue(String userName,String username);
    Optional<User> findById(Long id);
}
