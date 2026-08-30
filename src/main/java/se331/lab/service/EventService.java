package se331.lab.service;

import se331.lab.entity.Event;
import java.util.List;

public interface EventService {
    Integer getEventSize();
    List<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
}