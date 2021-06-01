package ru.vsu.cs.checklist.dao;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.cs.checklist.entity.Event;
import ru.vsu.cs.checklist.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface TaskDao extends CrudRepository<Task, Integer> {

    List<Task> findAllByUserIdAndDeletedOrderByDeadline(int userId, boolean deleted);


    Optional<Task> findByIdAndUserId(int id, int userId);

    boolean existsByIdAndUserId(int id, int userId);
}
