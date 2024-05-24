package com.example.application.views.main;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Second Page")
@Route("second")
public class SecondPage extends Div {

    public SecondPage() {
        setText("This is the second page.");
    }
}