package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.barcodegenerator.api.BarCodeData;
import am.davsoft.barcodegenerator.impl.BarCodeDataEmail;
import am.davsoft.qrgen.util.ValidatorFactory;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class EmailSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldEmailAddress;

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldEmailAddress);
        txtFieldEmailAddress.setValidators(ValidatorFactory.createRequiredFieldValidator("Email address is required!"));
    }

    @Override
    public void resetForm() {
        super.resetForm();
        txtFieldEmailAddress.setText("");
    }

    @Override
    public BarCodeData getQRData() {
        return BarCodeDataEmail.newInstance().withEmail(txtFieldEmailAddress.getText());
    }
}
