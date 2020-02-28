package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.barcodegenerator.api.BarCodeData;
import am.davsoft.barcodegenerator.helper.WiFiEncryptionType;
import am.davsoft.barcodegenerator.impl.BarCodeDataWiFiNetwork;
import am.davsoft.qrgen.util.ValidatorFactory;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

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
    public BarCodeData getQRData() {
        return BarCodeDataWiFiNetwork.newInstance()
                .withSsid(txtFieldSSID.getText())
                .withType(comboBoxAuthType.getValue())
                .withPass(passFieldPassword.getText())
                .withHidden(checkBoxHiddenNetwork.isSelected());
    }
}
