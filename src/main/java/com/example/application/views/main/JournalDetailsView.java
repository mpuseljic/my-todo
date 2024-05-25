package com.example.application.views.main;

import com.example.application.models.Journal;
import com.example.application.services.JournalService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
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
        }  else {
            // Journal not found
            Div notFoundDiv = new Div();
            notFoundDiv.setText("Journal not found.");
            getElement().executeJs("document.body.style.backgroundColor = '#D2B48C';");
            notFoundDiv.getStyle().set("color", "#65493C"); // Postavite boju teksta na crvenu
            notFoundDiv.getStyle().set("font-size", "32px"); // Postavite veličinu fonta na 20px
            notFoundDiv.getStyle().set("font-weight", "bold"); // Postavite debljinu fonta na bold
            notFoundDiv.getStyle().set("margin-top", "250px"); // Dodajte gornji margina od 20px
            notFoundDiv.getStyle().set("padding", "20px"); // Dodajte padding od 20px
            notFoundDiv.getStyle().set("border", "3px solid #65493C"); // Dodajte okvir od 2px debljine sa bojom teksta
            notFoundDiv.getStyle().set("border-radius", "5px"); // Dodajte zaobljeni rub okvira
            add(notFoundDiv);

            Anchor goToJournalsLink = new Anchor("/journals", "Go to ");
            Span journalsText = new Span("My Journals");
            journalsText.getStyle().set("text-decoration", "underline"); // Podcrtavanje teksta
            goToJournalsLink.getStyle().set("color", "#65493C"); // Postavite boju teksta na crvenu
            goToJournalsLink.getStyle().set("font-size", "16px"); // Postavite veličinu fonta na 16px
            goToJournalsLink.getStyle().set("font-weight", "bold"); // Postavite debljinu fonta na bold
            goToJournalsLink.add(journalsText);
            add(goToJournalsLink);
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
        TextField dateField = new TextField();
        dateField.setLabel("Date");
        dateField.setValue(journal.getDate().toString());
        dateField.setReadOnly(true);
        dateField.getStyle().set("font-family", "Inria Serif"); // Set font
        dateField.getStyle().set("color", "#D9D9D9A"); // Set text color
        dateField.getStyle().set("background-color", "#1A39601A"); // White background color
        dateField.getStyle().set("border", "2px solid #5D5D5D"); // Thicker border
        dateField.getStyle().set("border-radius", "10px"); // More rounded corners
        dateField.getStyle().set("height", "60px"); // Increase height of the text area
        dateField.getStyle().set("font-size", "16px"); // Increase font size inside the text area
        dateField.getStyle().set("padding", "0 10px"); // Adjust padding
        dateField.setWidth("100%"); // Make the input field full width
        add(dateField);

        // Display journal title
        TextField titleField = new TextField();
        titleField.setLabel("Title");
        titleField.setValue(journal.getName());
        titleField.setReadOnly(true);
        titleField.getStyle().set("font-family", "Irish Grover"); // Set font
        titleField.getStyle().set("color", "#000000"); // Set text color
        titleField.getStyle().set("background-color", "#1A39601A"); // White background color
        titleField.getStyle().set("border", "2px solid #5D5D5D"); // Thicker border
        titleField.getStyle().set("border-radius", "10px"); // More rounded corners
        titleField.getStyle().set("height", "60px"); // Increase height of the text area
        titleField.getStyle().set("font-size", "16px"); // Increase font size inside the text area
        titleField.getStyle().set("padding", "0 10px"); // Adjust padding
        titleField.setWidth("100%"); // Make the input field full width
        add(titleField);

        // Display journal entry
        TextArea entryArea = new TextArea();
        entryArea.setLabel("Entry");
        entryArea.setValue(journal.getDescription());
        entryArea.setReadOnly(true);
        entryArea.getStyle().set("font-family", "Irish Grover"); // Set font
        entryArea.getStyle().set("color", "#D9D9D9A"); // Set text color
        entryArea.getStyle().set("background-color", "#1A39601A"); // White background color
        entryArea.getStyle().set("border", "2px solid #5D5D5D"); // Thicker border
        entryArea.getStyle().set("border-radius", "10px"); // More rounded corners
        entryArea.getStyle().set("height", "200px"); // Increase height of the text area
        entryArea.getStyle().set("font-size", "16px"); // Increase font size inside the text area
        entryArea.getStyle().set("padding", "10px"); // Adjust padding
        entryArea.setWidth("100%"); // Make the input field full width
        add(entryArea);

        Button backButton = new Button("Back to Journals");
        backButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY); // Postavite primarnu temu gumba
        backButton.getStyle().set("background-color", "#65493C"); // Postavite pozadinsku boju gumba
        backButton.getStyle().set("color", "#FFFFFF"); // Postavite boju teksta na bijelu
        backButton.addClickListener(event -> {
            // Redirect to journals page
            UI.getCurrent().navigate("journals");
        });
        add(backButton);
    }
}
