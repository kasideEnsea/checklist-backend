package ru.vsu.cs.checklist.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.stream.Stream;

@Repository
public class UserSummaryDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Stream<Object[]> getSummary(int userId) {
        Query query = entityManager.createNativeQuery(
                "SELECT DATE(e.created) as date, sum(t.task_rate) as rate, count(*) as count " +
                        "FROM event e, task t " +
                        "WHERE e.user_id = :userId " +
                        "and e.type = 'done' " +
                        "and DATE(e.created) - current_date <= 7 " +
                        "and e.task_id = t.id " +
                        "group by date");
        query.setParameter("userId", userId);
        return query.getResultStream(); //(LinkedList<TasksAndDatesDto>)query.getResultStream().collect(Collectors.toCollection(LinkedList::new));
    }

//    public UserSummaryDto getSummary(int userId) {
//        LinkedList<TasksAndDatesDto> tasksAndDates = getTasksAndDatesSummary(userId);
//        LinkedHashMap<Date, <UserSummaryDto> userSummaryDtoList = new LinkedList<>();
//        for (:
//             ) {
//
//        }
//        for (TasksAndDatesDto taskAndDate: tasksAndDates
//             ) {
//            us
//        }
//        Query query = entityManager.createNativeQuery(
//                "SELECT DATE(created) as date, count(*) as count " +
//                        "FROM event e " +
//                        "WHERE e.user_id = :userId " +
//                        "and e.type = 'done' " +
//                        "and DATE(e.created) - current_date <= 7 " +
//                        "group by date");
//        query.setParameter("userId", userId);
//        query.setMaxResults(7);
//        return query.getResultStream();
//    }

}
