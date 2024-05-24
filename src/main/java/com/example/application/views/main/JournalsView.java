package com.example.application.views.main;

import com.example.application.models.Journal;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;
@Route("journals")
public class JournalsView extends VerticalLayout {

    private final Grid<Journal> grid;
    private final Button addNewJournal;
    private final List<Journal> journals;
    private int journalCounter = 1;


    public JournalsView() {
        setAlignItems(Alignment.CENTER);
        journals = new ArrayList<>();
        grid = new Grid<>(Journal.class);
        H1 header = new H1("My Journals");
        addNewJournal = new Button("+ Create New", event -> createNewJournal());
        addNewJournal.getStyle().set("background-color", "#65493C");
        addNewJournal.addClassName("white-text");

        add(header, addNewJournal);
        add(createButtonWrapper(addNewJournal));
        getElement().executeJs("document.body.style.backgroundColor = '#D2B48C';");
    }
    private void createNewJournal() {
        String newJournalId = String.valueOf(journalCounter);
        UI.getCurrent().navigate("/journals/" + newJournalId);
        journalCounter++; // Inkrementiraj brojač
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