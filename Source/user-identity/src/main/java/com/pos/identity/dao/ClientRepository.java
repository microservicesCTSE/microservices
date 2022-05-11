package com.pos.identity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pos.identity.domain.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
