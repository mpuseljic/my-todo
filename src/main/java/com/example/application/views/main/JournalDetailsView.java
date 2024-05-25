package com.example.application.views.main;

import com.example.application.models.Journal;
import com.example.application.services.JournalService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route("journal-details")
public class JournalDetailsView extends VerticalLayout implements HasUrlParameter<Integer> {

    private Journal journal;

    public JournalDetailsView() {
        // Initialize layout
        setAlignItems(Alignment.CENTER);
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        // Fetch journal details based on the parameter (journal ID)
        Optional<Journal> optionalJournal = JournalService.getJournalById(parameter);

        if (optionalJournal.isPresent()) {
            journal = optionalJournal.get();
            // Display journal details
            displayJournalDetails();
        } else {
            // Journal not found
            Div notFoundDiv = new Div();
            notFoundDiv.setText("Journal not found.");
            add(notFoundDiv);
        }
    }

    private void displayJournalDetails() {
        // Clear existing content
        removeAll();

        // Create header
        H1 header = new H1("Journal Details");
        add(header);

        // Display journal details
        // Display journal date
        Div dateDiv = new Div();
        dateDiv.setText("Date: " + journal.getDate().toString());
        add(dateDiv);

        // Display journal title
        Div titleDiv = new Div();
        titleDiv.setText("Title: " + journal.getName());
        add(titleDiv);

        // Display journal entry
        Div entryDiv = new Div();
        entryDiv.setText("Entry: " + journal.getDescription());
        add(entryDiv);

        // Add edit button
        Button editButton = new Button("Edit");
        editButton.addClickListener(event -> {
            // Redirect to edit page with journal ID as parameter
            UI.getCurrent().navigate("journals/" + journal.getId());
        });
        add(editButton);
    }
}
