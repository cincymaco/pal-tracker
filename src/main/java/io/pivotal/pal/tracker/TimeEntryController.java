package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;



    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntryCreated = timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity<TimeEntry>(timeEntryCreated, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        HttpStatus httpStatus = null;
        if (timeEntry != null){
           httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<TimeEntry>(timeEntry, httpStatus);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = timeEntryRepository.list();

        return new ResponseEntity<List<TimeEntry>>(timeEntries, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntryUpdated = timeEntryRepository.update(timeEntryId, expected);
        HttpStatus httpStatus = null;
        if (timeEntryUpdated != null){
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<TimeEntry>(timeEntryUpdated, httpStatus);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}
