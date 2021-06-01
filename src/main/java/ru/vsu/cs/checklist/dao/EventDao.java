package ru.vsu.cs.checklist.dao;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.cs.checklist.entity.Event;

public interface EventDao extends CrudRepository<Event, Integer> {

}
