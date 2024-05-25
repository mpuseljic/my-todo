package com.example.application.services;

import com.example.application.models.Journal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class JournalService {
    private static List<Journal> journals = new ArrayList<>();
    private static int nextId = 1;

    public static List<Journal> getJournals() {
        return new ArrayList<>(journals);
    }

    public static void addJournal(Journal journal) {
        journal.setId(nextId++);
        journals.add(journal);
    }
    public static Optional<Journal> getJournalById(int id) {
        return journals.stream()
                .filter(journal -> journal.getId() == id)
                .findFirst();
    }
        public static void deleteJournal(Journal journal) {
            journals.remove(journal);
        }
}
