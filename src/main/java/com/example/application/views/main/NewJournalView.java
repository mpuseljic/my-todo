package com.example.application.views.main;

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
import com.vaadin.flow.router.RouterLink;

import java.time.LocalDate;

@Route("journals/:id")
public class NewJournalView extends Div {

    public NewJournalView() {
        // Set the background color of the entire page
        getStyle().set("background-color", "#D2B48C");
        getStyle().set("height", "100vh");
        getStyle().set("margin", "0");

        // Create header
        H1 header = new H1("Write new journal");
        header.getStyle().set("color", "#000000"); // Set text color to black

        // Apply the dark brown frame style
        Div frame = new Div(header);
        frame.getStyle().set("background-color", "#65493C"); // Dark brown color
        frame.getStyle().set("padding", "20px"); // Padding inside the frame
        frame.getStyle().set("border-radius", "10px"); // Optional: rounded corners
        frame.getStyle().set("color", "#5D5D5D"); // Text color
        frame.getStyle().set("text-align", "center"); // Center text horizontally
        frame.getStyle().set("width", "100%"); // Full width
        frame.getStyle().set("box-sizing", "border-box"); // Include padding in the width calculation

        // Create a container for the frame
        VerticalLayout container = new VerticalLayout(frame);
        container.setWidthFull();
        container.setAlignItems(FlexComponent.Alignment.CENTER);
        container.getStyle().set("padding-top", "20px"); // Top padding to position the frame near the top

        // Create the input field with today's date
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now()); // Set today's date
        datePicker.setPlaceholder("Select date");
        datePicker.getStyle().set("font-family", "Inria Serif"); // Set font
        datePicker.getStyle().set("color", "#D9D9D9a"); // Set text color
        datePicker.getStyle().set("background-color", "#1A39601A"); // White background color
        datePicker.getStyle().set("border", "2px solid #5D5D5D"); // Thicker border
        datePicker.getStyle().set("border-radius", "10px"); // More rounded corners
        datePicker.getStyle().set("height", "60px"); // Increase height of the date picker
        datePicker.getStyle().set("font-size", "16px"); // Increase font size inside the date picker
        datePicker.getStyle().set("padding", "0 10px"); // Adjust padding
        datePicker.getElement().getStyle().set("width", "100%"); // Make the input field full width
        datePicker.getStyle().set("color", "#5D5D5D"); // Set text color inside the input field


        datePicker.getElement().executeJs(
                "setTimeout(() => {" +
                        "   const datePickerOverlay = this.shadowRoot.querySelector('vaadin-date-picker-overlay');" +
                        "   datePickerOverlay.style.boxShadow = 'none';" +
                        "   const innerBorder = this.shadowRoot.querySelector('vaadin-text-field').shadowRoot.querySelector('[part=\"input-field\"]');" +
                        "   innerBorder.style.border = 'none';" +
                        "}, 0);"
        );

        // Add a calendar icon to the input field
        Icon calendarIcon = new Icon(VaadinIcon.CALENDAR);
        calendarIcon.getStyle().set("color", "#5D5D5D");

        HorizontalLayout datePickerLayout = new HorizontalLayout(calendarIcon, datePicker);
        datePickerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        datePickerLayout.setWidth("100%");
        datePickerLayout.expand(datePicker);

        // Add the date picker below the dark brown section
        container.add(datePickerLayout);

        // Create the title input field
        TextField titleField = new TextField();
        titleField.setPlaceholder("TITLE");
        titleField.getStyle().set("font-family", "Irish Grover"); // Set font
        titleField.getStyle().set("color", "#000000"); // Set text color
        titleField.getStyle().set("background-color", "#1A39601A"); // White background color
        titleField.getStyle().set("border", "2px solid #5D5D5D"); // Thicker border
        titleField.getStyle().set("border-radius", "10px"); // More rounded corners
        titleField.getStyle().set("height", "60px"); // Increase height of the title field
        titleField.getStyle().set("font-size", "16px"); // Increase font size inside the title field
        titleField.getStyle().set("padding", "0 10px"); // Adjust padding
        titleField.getStyle().set("width", "100%"); // Make the input field full width

// Add an edit icon to the title field
        Icon editIcon = new Icon(VaadinIcon.PENCIL);
        editIcon.getStyle().set("color", "#5D5D5D");

        HorizontalLayout titleLayout = new HorizontalLayout(editIcon, titleField);
        titleLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        titleLayout.setWidth("100%");
        titleLayout.expand(titleField);

// Add the title field below the date picker
        container.add(titleLayout);

        // Create the text area for the journal entry
        TextArea journalEntryTextArea = new TextArea();
        journalEntryTextArea.setPlaceholder("Journal Entry");
        journalEntryTextArea.getStyle().set("font-family", "Irish Grover"); // Set font
        journalEntryTextArea.getStyle().set("color", "#000000"); // Set text color
        journalEntryTextArea.getStyle().set("background-color", "#1A39601A"); // White background color
        journalEntryTextArea.getStyle().set("border", "2px solid #5D5D5D"); // Thicker border
        journalEntryTextArea.getStyle().set("border-radius", "10px"); // More rounded corners
        journalEntryTextArea.getStyle().set("height", "200px"); // Increase height of the text area
        journalEntryTextArea.getStyle().set("font-size", "16px"); // Increase font size inside the text area
        journalEntryTextArea.getStyle().set("padding", "10px"); // Adjust padding
        journalEntryTextArea.getStyle().set("width", "100%"); // Make the input field full width

// Add the text area below the title field
        container.add(journalEntryTextArea);

// Create the button for saving the journal
        Button saveButton = new Button("SAVE");
        saveButton.getStyle().set("background-color", "#319104"); // Set background color
        saveButton.getStyle().set("color", "#FFFFFF"); // Set text color
        saveButton.getStyle().set("border-radius", "10px"); // Rounded corners
        saveButton.getStyle().set("padding", "10px 20px"); // Padding
        saveButton.getStyle().set("font-size", "16px"); // Font size

        // Set the action to show a dialog
        Dialog dialog = new Dialog();
        dialog.add(new H1("Journal saved successfully"));
        dialog.setWidth("300px");

        saveButton.addClickListener(event -> {
            dialog.open();
            // Redirect to 'journals' route after a short delay
            getUI().ifPresent(ui -> ui.navigate("journals"));
        });

        // Add the button to the container
        container.add(saveButton);

        // Add the container to the view
        add(container);


    }
}
