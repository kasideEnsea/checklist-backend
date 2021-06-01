package ru.vsu.cs.checklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.checklist.dao.EventDao;
import ru.vsu.cs.checklist.dao.EventFeedDao;
import ru.vsu.cs.checklist.entity.Event;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventDao eventDao;
    private final EventFeedDao eventFeedDao;

    public List<Event> getFeed(int page) {
        return eventFeedDao.getFeed(page);
    }

    public int getFeedPagesCount() {
        return eventFeedDao.getFeedPagesCount();
    }

    public Optional<Event> findById(int id) {
        return eventDao.findById(id);
    }

    public void addNew(Event event) {
        event.setId(null);
        eventDao.save(event);
    }

    public void delete(int id) {
        eventDao.deleteById(id);
    }

}

