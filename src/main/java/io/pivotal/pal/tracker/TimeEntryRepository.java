package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface TimeEntryRepository {
    TimeEntry create(TimeEntry timeEntry);
    TimeEntry find(Long timeEntryId);
    TimeEntry update(Long timeEntryId, TimeEntry timeEntry);
    void delete(Long timeEntryId);
    List<TimeEntry> list();
}
