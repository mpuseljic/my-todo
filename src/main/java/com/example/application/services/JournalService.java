package com.example.application.services;

import com.example.application.models.Journal;

import java.util.ArrayList;
import java.util.List;

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

    public static Journal getJournalById(int id) {
        return journals.stream()
                .filter(journal -> journal.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
