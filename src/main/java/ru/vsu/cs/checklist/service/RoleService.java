package ru.vsu.cs.checklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.checklist.dao.UserDao;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final UserDao userDao;

    public List<String> getAllRoles(int userId) {
        String role = userDao.getById(userId).getRole();
        if (role == null) {
            return Collections.emptyList();
        } else {
            return Collections.singletonList(role);
        }
    }
}
