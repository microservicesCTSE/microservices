package com.pos.identity.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pos.identity.domain.UserLoginDetails;

/**
 * @author Thilina
 *
 */
@Repository
public interface UserLoginDetailsRepository extends CrudRepository<UserLoginDetails, Long> {

	UserLoginDetails findTopByUsernameOrderByUserLoginDetailsIdDesc(String username);

	Long countByUsernameAndIsSuccessFalse(String username);

	Page<UserLoginDetails> findAllByOrderByLoginDateDescInvalidLoginCountDesc(Pageable paging);
}
