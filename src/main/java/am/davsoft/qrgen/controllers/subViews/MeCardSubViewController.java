package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgen.util.ValidatorFactory;
import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataMeCard;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class MeCardSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldName, txtFieldCompany, txtFieldPhone, txtFieldWebsite, txtFieldEmail, txtFieldAddress, txtFieldNote;

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldName, txtFieldPhone);
        txtFieldName.setValidators(ValidatorFactory.createRequiredFieldValidator("Name is required!"));
        txtFieldPhone.setValidators(ValidatorFactory.createRequiredFieldValidator("Phone is required!"));
    }

    @Override
    public void resetForm() {
        txtFieldName.setText("");
        txtFieldCompany.setText("");
        txtFieldPhone.setText("");
        txtFieldWebsite.setText("");
        txtFieldEmail.setText("");
        txtFieldAddress.setText("");
        txtFieldNote.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataMeCard()
                .setName(txtFieldName.getText())
                .setCompany(txtFieldCompany.getText())
                .setPhone(txtFieldPhone.getText())
                .setWebsite(txtFieldWebsite.getText())
                .setEmail(txtFieldEmail.getText())
                .setAddress(txtFieldAddress.getText())
                .setNote(txtFieldNote.getText());
    }
}
