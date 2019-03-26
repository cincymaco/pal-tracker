package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private final Map<Long, TimeEntry> timeEntryMap = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong(0L);

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long timeEntryId = getNextId();
        TimeEntry timeEntryCreated = new TimeEntry(timeEntryId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntryMap.put(timeEntryId, timeEntryCreated);
        return timeEntryCreated;
    }

    private Long getNextId() {
        return counter.incrementAndGet();
    }

    @Override
    public TimeEntry find(Long timeEntryId) {
        return timeEntryMap.get(timeEntryId);
    }

    @Override
    public TimeEntry update(Long timeEntryId, TimeEntry timeEntry) {
        if (this.find(timeEntryId) != null) {
            TimeEntry timeEntryUpdated = new TimeEntry(timeEntryId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
            timeEntryMap.put(timeEntryId, timeEntryUpdated);
            return timeEntryUpdated;
        }
        return null;
    }

    @Override
    public void delete(Long timeEntryId) {
        timeEntryMap.remove(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries = new ArrayList<>(timeEntryMap.values());

        return timeEntries;
    }
}
