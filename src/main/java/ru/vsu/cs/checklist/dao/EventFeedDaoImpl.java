package ru.vsu.cs.checklist.dao;

import org.springframework.stereotype.Service;
import ru.vsu.cs.checklist.entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class EventFeedDaoImpl implements EventFeedDao {
    public static final int MAX_RESULTS = 50;
    private static final String BASIC_QUERY = "SELECT e FROM Event e ORDER BY e.id DESC";
    private static final String BASIC_COUNT_QUERY = "SELECT COUNT(e) FROM Event e";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> getFeed(int pageNo) {
        TypedQuery<Event> query = entityManager.createQuery(BASIC_QUERY, Event.class);
        query.setFirstResult(pageNo * MAX_RESULTS);
        query.setMaxResults(MAX_RESULTS);
        return query.getResultList();
    }

    @Override
    public int getFeedPagesCount() {
        TypedQuery<Long> query = entityManager.createQuery(BASIC_COUNT_QUERY, Long.class);
        return getPagesCount(query.getSingleResult());
    }

    private int getPagesCount(long itemsCount) {
        return (int) itemsCount / MAX_RESULTS + (itemsCount % MAX_RESULTS > 0 ? 1 : 0);
    }

}
