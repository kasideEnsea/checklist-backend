package ru.vsu.cs.checklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.checklist.dao.UserDao;
import ru.vsu.cs.checklist.dao.UserSummaryDao;
import ru.vsu.cs.checklist.dto.UserSummaryDto;
import ru.vsu.cs.checklist.entity.User;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserSummaryDao userSummaryDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    public List<User> findUsersByIdList(List<Integer> ids) {
        return userDao.findAllByIdIn(ids);
    }

    public List<UserSummaryDto> getUserSummary(int userId) {
        return userSummaryDao.getSummary(userId).map(objects -> {
            Date date = (Date) objects[0];
            int count = ((BigInteger) objects[1]).intValue();
            return new UserSummaryDto(date, count);
        }).collect(Collectors.toList());
    }
}
