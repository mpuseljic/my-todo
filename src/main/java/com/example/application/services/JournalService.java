package com.example.application.services;

import com.example.application.models.Journal;

import java.util.ArrayList;
import java.util.List;

public class JournalService {
    private static final List<Journal> journals = new ArrayList<>();

    public static List<Journal> getJournals() {
        return journals;
    }

    public static void addJournal(Journal journal) {
        journals.add(journal);
    }
}
