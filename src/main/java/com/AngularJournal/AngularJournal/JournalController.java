package com.AngularJournal.AngularJournal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
@CrossOrigin("*") // Tillåt alla ursprung för CORS
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping("/save")
    public ResponseEntity<Journal> saveJournal(@RequestBody Journal journal, @RequestHeader("userId") String userId) {
        journal.setUserId(userId);
        return journalService.saveJournal(journal);
    }

    @GetMapping("/entries")
    public ResponseEntity<List<Journal>> getAllEntries(@RequestHeader("userId") String userId) {
        List<Journal> journals = journalService.getJournalsByUserId(userId);
        return ResponseEntity.ok(journals);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJournal(@PathVariable String id, @RequestHeader("userId") String userId) {
        return journalService.deleteJournal(id);
    }

}