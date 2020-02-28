package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.barcodegenerator.api.BarCodeData;
import am.davsoft.barcodegenerator.impl.BarCodeDataPhoneNumber;
import am.davsoft.qrgen.util.ValidatorFactory;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class PhoneNumberSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldPhoneNumber;

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldPhoneNumber);
        txtFieldPhoneNumber.setValidators(ValidatorFactory.createRequiredFieldValidator("Phone number is required!"));
    }

    @Override
    public void resetForm() {
        super.resetForm();
        txtFieldPhoneNumber.setText("");
    }

    @Override
    public BarCodeData getQRData() {
        return BarCodeDataPhoneNumber.newInstance().withPhone(txtFieldPhoneNumber.getText());
    }
}
