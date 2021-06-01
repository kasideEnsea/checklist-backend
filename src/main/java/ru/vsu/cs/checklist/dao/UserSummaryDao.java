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
                "SELECT DATE(created) as date, count(*) as count " +
                        "FROM event e " +
                        "WHERE e.user_id = :userId " +
                        "and e.type = 'done' " +
                        "and DATE(e.created) - current_date <= 7 " +
                        "group by date");
        query.setParameter("userId", userId);
        query.setMaxResults(7);
        return query.getResultStream();
    }

}
