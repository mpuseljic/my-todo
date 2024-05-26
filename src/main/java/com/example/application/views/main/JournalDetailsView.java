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
        setAlignItems(Alignment.CENTER);
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        Optional<Journal> optionalJournal = JournalService.getJournalById(parameter);

        if (optionalJournal.isPresent()) {
            journal = optionalJournal.get();
            displayJournalDetails();
        }  else {
            Div notFoundDiv = new Div();
            notFoundDiv.setText("Journal not found.");
            getElement().executeJs("document.body.style.backgroundColor = '#D2B48C';");
            notFoundDiv.getStyle().set("color", "#65493C");
            notFoundDiv.getStyle().set("font-size", "32px");
            notFoundDiv.getStyle().set("font-weight", "bold");
            notFoundDiv.getStyle().set("margin-top", "250px");
            notFoundDiv.getStyle().set("padding", "20px");
            notFoundDiv.getStyle().set("border", "3px solid #65493C");
            notFoundDiv.getStyle().set("border-radius", "5px");
            add(notFoundDiv);

            Anchor goToJournalsLink = new Anchor("/journals", "Go to ");
            Span journalsText = new Span("My Journals");
            journalsText.getStyle().set("text-decoration", "underline");
            goToJournalsLink.getStyle().set("color", "#65493C");
            goToJournalsLink.getStyle().set("font-size", "16px");
            goToJournalsLink.getStyle().set("font-weight", "bold");
            goToJournalsLink.add(journalsText);
            add(goToJournalsLink);
        }
    }


    private void displayJournalDetails() {

        removeAll();

        H1 header = new H1("Journal Details");
        add(header);



        TextField dateField = new TextField();
        dateField.setLabel("Date");
        dateField.setValue(journal.getDate().toString());
        dateField.setReadOnly(true);
        dateField.getStyle().set("font-family", "Inria Serif");
        dateField.getStyle().set("color", "#D9D9D9A");
        dateField.getStyle().set("background-color", "#1A39601A");
        dateField.getStyle().set("border", "2px solid #5D5D5D");
        dateField.getStyle().set("border-radius", "10px");
        dateField.getStyle().set("height", "60px");
        dateField.getStyle().set("font-size", "16px");
        dateField.getStyle().set("padding", "0 10px");
        dateField.setWidth("100%");
        add(dateField);


        TextField titleField = new TextField();
        titleField.setLabel("Title");
        titleField.setValue(journal.getName());
        titleField.setReadOnly(true);
        titleField.getStyle().set("font-family", "Irish Grover");
        titleField.getStyle().set("color", "#000000");
        titleField.getStyle().set("background-color", "#1A39601A");
        titleField.getStyle().set("border", "2px solid #5D5D5D");
        titleField.getStyle().set("border-radius", "10px");
        titleField.getStyle().set("height", "60px");
        titleField.getStyle().set("font-size", "16px");
        titleField.getStyle().set("padding", "0 10px");
        titleField.setWidth("100%");
        add(titleField);

        TextArea entryArea = new TextArea();
        entryArea.setLabel("Entry");
        entryArea.setValue(journal.getDescription());
        entryArea.setReadOnly(true);
        entryArea.getStyle().set("font-family", "Irish Grover");
        entryArea.getStyle().set("color", "#D9D9D9A");
        entryArea.getStyle().set("background-color", "#1A39601A");
        entryArea.getStyle().set("border", "2px solid #5D5D5D");
        entryArea.getStyle().set("border-radius", "10px");
        entryArea.getStyle().set("height", "200px");
        entryArea.getStyle().set("font-size", "16px");
        entryArea.getStyle().set("padding", "10px");
        entryArea.setWidth("100%");
        add(entryArea);

        Button backButton = new Button("Back to Journals");
        backButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        backButton.getStyle().set("background-color", "#65493C");
        backButton.getStyle().set("color", "#FFFFFF");
        backButton.addClickListener(event -> {

            UI.getCurrent().navigate("journals");
        });
        add(backButton);
    }
}
