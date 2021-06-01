package ru.vsu.cs.checklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.checklist.converter.EventMapper;
import ru.vsu.cs.checklist.dto.EventDto;
import ru.vsu.cs.checklist.dto.EventPageDto;
import ru.vsu.cs.checklist.entity.Event;
import ru.vsu.cs.checklist.exception.WebException;
import ru.vsu.cs.checklist.service.EventService;

import java.util.List;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable int id) {
        Event event = eventService.findById(id)
                .orElseThrow(() -> new WebException("Event not found", HttpStatus.NOT_FOUND));
        return EventMapper.INSTANCE.toDto(event);
    }

    @GetMapping("/")
    public EventPageDto getAll(@RequestParam(defaultValue = "0") int page) {
        List<Event> events = eventService.getFeed(page);
        int count = eventService.getFeedPagesCount();
        List<EventDto> list = EventMapper.INSTANCE.toDtoList(events);
        return new EventPageDto(page, count, list);
    }

    @PreAuthorize("hasAnyAuthority('Admin','Moderator')")
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id) {
        eventService.delete(id);
    }
}

