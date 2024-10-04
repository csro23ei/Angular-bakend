package com.AngularJournal.AngularJournal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public ResponseEntity<Journal> saveJournal(Journal journal) {
        mongoTemplate.save(journal);
        return ResponseEntity.ok(journal);
    }

    public List<Journal> getJournalsByUserId(String userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Journal.class);
    }

    public ResponseEntity<String> deleteJournal(String journalId) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").is(journalId)), Journal.class);
        return ResponseEntity.ok("Journal borttagen.");
    }

}