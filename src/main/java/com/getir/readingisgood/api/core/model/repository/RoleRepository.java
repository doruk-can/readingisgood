package com.getir.readingisgood.api.core.model.repository;

import com.getir.readingisgood.api.core.model.domain.ERole;
import com.getir.readingisgood.api.core.model.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
