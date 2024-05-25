package com.example.application.views.main;

import com.example.application.models.Journal;
import com.example.application.services.JournalService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("journals")
public class JournalsView extends VerticalLayout {

    private final Button addNewJournal;

    public JournalsView() {
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("My Journals");
        addNewJournal = new Button("+ Create New", event -> createNewJournal());
        addNewJournal.getStyle().set("background-color", "#65493C");
        addNewJournal.addClassName("white-text");

        add(header, addNewJournal);
        add(createButtonWrapper(addNewJournal));
        getElement().executeJs("document.body.style.backgroundColor = '#D2B48C';");

        displayJournals();
    }

    private void createNewJournal() {
        UI.getCurrent().navigate("/journals/" + (JournalService.getJournals().size() + 1));
    }

    private void displayJournals() {
        removeAll(); // Clear existing content
        H1 header = new H1("My Journals");
        add(header);
        List<Journal> journals = JournalService.getJournals();
        for (Journal journal : journals) {
            Div journalCard = createJournalCard(journal);
            add(journalCard);
        }
        add(createButtonWrapper(addNewJournal));
    }

    private Div createJournalCard(Journal journal) {
        Div card = new Div();
        card.getStyle().set("background-color", "#65493C");
        card.getStyle().set("color", "white");
        card.getStyle().set("padding", "10px");
        card.getStyle().set("margin", "10px 0");
        card.getStyle().set("border-radius", "10px");
        card.getStyle().set("display", "flex");
        card.getStyle().set("justify-content", "space-between");
        card.getStyle().set("align-items", "center");
        card.getStyle().set("width", "90%");

        // Journal title
        Div title = new Div();
        title.setText(journal.getName());
        card.add(title);

        // Placeholder for additional options
        Button options = new Button("...");
        options.getStyle().set("background", "none");
        options.getStyle().set("color", "white");

        Dialog confirmDialog = new Dialog();
        Button confirmButton = new Button("Delete");
        confirmButton.getStyle().set("background-color", "red");
        confirmButton.getStyle().set("color", "white");
        confirmButton.addClickListener(e -> {
            JournalService.deleteJournal(journal);
            displayJournals();
            confirmDialog.close();
        });

        Button cancelButton = new Button("Cancel", e -> confirmDialog.close());

        confirmDialog.add(new H1("Are you sure you want to delete this journal?"), confirmButton, cancelButton);

        options.addClickListener(e -> confirmDialog.open());

        card.add(options);

        return card;
    }

    private Div createButtonWrapper(Button button) {
        Div buttonWrapper = new Div(button);
        buttonWrapper.getStyle().set("position", "absolute");
        buttonWrapper.getStyle().set("bottom", "20px");
        buttonWrapper.getStyle().set("left", "50%");
        buttonWrapper.getStyle().set("transform", "translateX(-50%)");
        return buttonWrapper;
    }
}
