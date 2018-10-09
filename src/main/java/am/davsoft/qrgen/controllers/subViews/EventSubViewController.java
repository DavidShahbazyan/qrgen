package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgen.util.ValidatorFactory;
import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataEvent;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class EventSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldTitle, txtFieldLocation;
    @FXML private JFXCheckBox checkBoxAllDayEvent;
    @FXML private JFXDatePicker datePickerStartDate, datePickerEndDate;
    @FXML private JFXTimePicker timePickerStartDate, timePickerEndDate;
    @FXML private JFXTextArea txtAreaDescription;
    @FXML private HBox hBoxStartDate, hBoxEndDate;

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldTitle, txtFieldLocation, txtAreaDescription, datePickerStartDate, datePickerEndDate);
        checkBoxAllDayEvent.setSelected(true);
        datePickerStartDate.setValue(LocalDate.now());
        datePickerEndDate.setValue(LocalDate.now());
        timePickerStartDate.setValue(LocalTime.of(9, 0));
        timePickerEndDate.setValue(LocalTime.of(18, 0));
        timePickerStartDate.visibleProperty().bind(checkBoxAllDayEvent.selectedProperty().not());
        timePickerStartDate.managedProperty().bind(checkBoxAllDayEvent.selectedProperty().not());
        timePickerEndDate.visibleProperty().bind(checkBoxAllDayEvent.selectedProperty().not());
        timePickerEndDate.managedProperty().bind(checkBoxAllDayEvent.selectedProperty().not());
        txtFieldTitle.setValidators(ValidatorFactory.createRequiredFieldValidator("Title is required!"));
        txtFieldLocation.setValidators(ValidatorFactory.createRequiredFieldValidator("Location is required!"));
        txtAreaDescription.setValidators(ValidatorFactory.createRequiredFieldValidator("Description is required!"));
        datePickerStartDate.setValidators(ValidatorFactory.createRequiredFieldValidator("Start date is required!"));
        datePickerEndDate.setValidators(ValidatorFactory.createRequiredFieldValidator("End date is required!"));
    }

    @Override
    public void resetForm() {
        txtFieldTitle.setText("");
        txtFieldLocation.setText("");
        checkBoxAllDayEvent.setSelected(true);
        datePickerStartDate.setValue(LocalDate.now());
        datePickerEndDate.setValue(LocalDate.now());
        timePickerStartDate.setValue(LocalTime.of(9, 0));
        timePickerEndDate.setValue(LocalTime.of(18, 0));
        txtAreaDescription.setText("");
    }

    @Override
    public QRData getQRData() {
        QRDataEvent qrDataEvent = new QRDataEvent()
                .setTitle(txtFieldTitle.getText())
                .setLocation(txtFieldLocation.getText())
                .setAllDayEvent(checkBoxAllDayEvent.isSelected())
                .setDescription(txtAreaDescription.getText());
        if (checkBoxAllDayEvent.isSelected()) {
            qrDataEvent.setStartDate(datePickerStartDate.getValue().atTime(LocalTime.MIN))
                    .setEndDate(datePickerEndDate.getValue().atTime(LocalTime.MAX));
        } else {
            qrDataEvent.setStartDate(datePickerStartDate.getValue().atTime(timePickerStartDate.getValue()))
                    .setEndDate(datePickerEndDate.getValue().atTime(timePickerEndDate.getValue()));
        }
        return qrDataEvent;
    }
}
