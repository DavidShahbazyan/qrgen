package am.davsoft.qrgen.controllers.subViews;

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
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        checkBoxAllDayEvent.setSelected(true);
        datePickerStartDate.setValue(LocalDate.now());
        datePickerEndDate.setValue(LocalDate.now());
        timePickerStartDate.setValue(LocalTime.of(9, 0));
        timePickerEndDate.setValue(LocalTime.of(18, 0));
        hBoxStartDate.visibleProperty().bind(checkBoxAllDayEvent.selectedProperty().not());
        hBoxStartDate.managedProperty().bind(checkBoxAllDayEvent.selectedProperty().not());
        hBoxEndDate.visibleProperty().bind(checkBoxAllDayEvent.selectedProperty().not());
        hBoxEndDate.managedProperty().bind(checkBoxAllDayEvent.selectedProperty().not());
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
                .setAllDayEvent(checkBoxAllDayEvent.isSelected())
//                .setStartDate(datePickerStartDate.getValue().atTime(timePickerStartDate.getValue()))
//                .setEndDate(datePickerEndDate.getValue().atTime(timePickerEndDate.getValue()))
                .setDescription(txtAreaDescription.getText());
        if (!checkBoxAllDayEvent.isSelected()) {
            qrDataEvent.setStartDate(datePickerStartDate.getValue().atTime(timePickerStartDate.getValue()))
                    .setEndDate(datePickerEndDate.getValue().atTime(timePickerEndDate.getValue()));
        }
        return qrDataEvent;
    }
}