package com.example.application.views.main;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;

@Route("journals/:id")
public class NewJournalView extends Div {

    public NewJournalView() {
        H1 header = new H1("Write new journal");
        add(header);
    }
}

