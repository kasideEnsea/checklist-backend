package ru.vsu.cs.checklist.dao;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.cs.checklist.entity.Role;

import java.util.List;

public interface RoleDao extends CrudRepository<Role, Integer> {
    List<Role> findAllByUserId(int userId);

    List<Role> findAllByRoleNameOrderById(String roleName);

    Role findByUserIdAndRoleName(int userId, String roleName);
}

