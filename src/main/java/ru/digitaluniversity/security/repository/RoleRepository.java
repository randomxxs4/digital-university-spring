package ru.digitaluniversity.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digitaluniversity.security.entity.UserRole;


public interface RoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByRole(String role);
}
