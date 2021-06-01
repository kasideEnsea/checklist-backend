package ru.vsu.cs.checklist.dao;

import ru.vsu.cs.checklist.entity.Event;

import java.util.List;

public interface EventFeedDao {
    List<Event> getFeed(int pageNo);

    int getFeedPagesCount();
}
