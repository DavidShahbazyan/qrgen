package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgen.util.ValidatorFactory;
import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.helper.WiFiEncryptionType;
import am.davsoft.qrgenerator.impl.QRDataWiFiNetwork;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class WiFiNetworkSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldSSID;
    @FXML private JFXPasswordField passFieldPassword;
    @FXML private JFXCheckBox checkBoxHiddenNetwork;
    @FXML private JFXComboBox<WiFiEncryptionType> comboBoxAuthType;

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldSSID, passFieldPassword);
        comboBoxAuthType.setItems(FXCollections.observableArrayList(WiFiEncryptionType.values()));
        comboBoxAuthType.getSelectionModel().select(WiFiEncryptionType.WPA_WPA2);
        passFieldPassword.visibleProperty().bind(Bindings.notEqual(comboBoxAuthType.getSelectionModel().selectedItemProperty(), WiFiEncryptionType.NONE));
        txtFieldSSID.setValidators(ValidatorFactory.createRequiredFieldValidator("SSID is required!"));
        passFieldPassword.setValidators(ValidatorFactory.createConditionalRequiredFieldValidator(() -> passFieldPassword.isVisible(), "Password is required!"));
    }

    @Override
    public void resetForm() {
        super.resetForm();
        txtFieldSSID.setText("");
        passFieldPassword.setText("");
        checkBoxHiddenNetwork.setSelected(false);
        comboBoxAuthType.getSelectionModel().select(WiFiEncryptionType.WPA_WPA2);
    }

    @Override
    public QRData getQRData() {
        return new QRDataWiFiNetwork()
                .setSsid(txtFieldSSID.getText())
                .setType(comboBoxAuthType.getValue())
                .setPass(passFieldPassword.getText())
                .setHidden(checkBoxHiddenNetwork.isSelected());
    }
}
