package com.example.application.views.main;

import com.example.application.models.Journal;
import com.example.application.services.JournalService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route("journals/:id")
public class NewJournalView extends Div {

    public NewJournalView() {

        getStyle().set("background-color", "#D2B48C");
        getStyle().set("height", "100vh");
        getStyle().set("margin", "0");

        H1 header = new H1("Write new journal");
        header.getStyle().set("color", "#000000");

        Div frame = new Div(header);
        frame.getStyle().set("background-color", "#65493C");
        frame.getStyle().set("padding", "20px");
        frame.getStyle().set("border-radius", "10px");
        frame.getStyle().set("color", "#5D5D5D");
        frame.getStyle().set("text-align", "center");
        frame.getStyle().set("width", "100%");
        frame.getStyle().set("box-sizing", "border-box");


        VerticalLayout container = new VerticalLayout(frame);
        container.setWidthFull();
        container.setAlignItems(FlexComponent.Alignment.CENTER);
        container.getStyle().set("padding-top", "20px");


        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setPlaceholder("Select date");
        datePicker.getStyle().set("font-family", "Inria Serif");
        datePicker.getStyle().set("color", "#D9D9D9A");
        datePicker.getStyle().set("background-color", "#1A39601A");
        datePicker.getStyle().set("border", "2px solid #5D5D5D");
        datePicker.getStyle().set("border-radius", "10px");
        datePicker.getStyle().set("height", "60px");
        datePicker.getStyle().set("font-size", "16px");
        datePicker.getStyle().set("padding", "0 10px");
        datePicker.setWidth("100%");
        datePicker.getStyle().set("color", "#5D5D5D");

        Icon calendarIcon = new Icon(VaadinIcon.CALENDAR);
        calendarIcon.getStyle().set("color", "#5D5D5D");

        HorizontalLayout datePickerLayout = new HorizontalLayout(calendarIcon, datePicker);
        datePickerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        datePickerLayout.setWidth("100%");
        datePickerLayout.expand(datePicker);

        container.add(datePickerLayout);

        TextField titleField = new TextField();
        titleField.setPlaceholder("TITLE");
        titleField.getStyle().set("font-family", "Irish Grover");
        titleField.getStyle().set("color", "#000000");
        titleField.getStyle().set("background-color", "#1A39601A");
        titleField.getStyle().set("border", "2px solid #5D5D5D");
        titleField.getStyle().set("border-radius", "10px");
        titleField.getStyle().set("height", "60px");
        titleField.getStyle().set("font-size", "16px");
        titleField.getStyle().set("padding", "0 10px");
        titleField.setWidth("100%");


        Icon editIcon = new Icon(VaadinIcon.PENCIL);
        editIcon.getStyle().set("color", "#5D5D5D");

        HorizontalLayout titleLayout = new HorizontalLayout(editIcon, titleField);
        titleLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        titleLayout.setWidth("100%");
        titleLayout.expand(titleField);

        container.add(titleLayout);

        TextArea journalEntryTextArea = new TextArea();
        journalEntryTextArea.setPlaceholder("Journal Entry");
        journalEntryTextArea.getStyle().set("font-family", "Irish Grover");
        journalEntryTextArea.getStyle().set("color", "#D9D9D9A");
        journalEntryTextArea.getStyle().set("background-color", "#1A39601A");
        journalEntryTextArea.getStyle().set("border-radius", "10px");
        journalEntryTextArea.getStyle().set("height", "200px");
        journalEntryTextArea.getStyle().set("font-size", "16px");
        journalEntryTextArea.getStyle().set("padding", "10px");
        journalEntryTextArea.setWidth("100%");

        container.add(journalEntryTextArea);

        Button saveButton = new Button("SAVE");
        saveButton.getStyle().set("background-color", "#319104");
        saveButton.getStyle().set("color", "#FFFFFF");
        saveButton.getStyle().set("border-radius", "10px");
        saveButton.getStyle().set("padding", "10px 20px");
        saveButton.getStyle().set("font-size", "16px");

        Dialog dialog = new Dialog();
        dialog.add(new H1("Journal saved successfully"));
        dialog.setWidth("300px");

        saveButton.addClickListener(event -> {
            Journal newJournal = new Journal();
            newJournal.setName(titleField.getValue());
            newJournal.setDate(datePicker.getValue());
            newJournal.setDescription(journalEntryTextArea.getValue());
            JournalService.addJournal(newJournal);

            dialog.open();
            getUI().ifPresent(ui -> ui.navigate("journals"));
        });

        container.add(saveButton);

        add(container);
    }
}
