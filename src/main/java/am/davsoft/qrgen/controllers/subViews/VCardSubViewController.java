package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.barcodegenerator.api.BarCodeData;
import am.davsoft.barcodegenerator.helper.PhoneNumberType;
import am.davsoft.barcodegenerator.impl.BarCodeDataVCard;
import am.davsoft.barcodegenerator.model.ContactName;
import am.davsoft.barcodegenerator.model.PhoneNumber;
import am.davsoft.qrgen.util.ValidatorFactory;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class VCardSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldFirstName, txtFieldMiddleName, txtFieldLastName, txtFieldCompany, txtFieldTitle, txtFieldPhone, txtFieldWebsite, txtFieldEmail, txtFieldAddress, txtFieldNote;

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldFirstName, txtFieldMiddleName, txtFieldLastName, txtFieldCompany, txtFieldTitle, txtFieldPhone, txtFieldEmail);
        txtFieldFirstName.setValidators(ValidatorFactory.createRequiredFieldValidator("First Name is required!"));
        txtFieldMiddleName.setValidators(ValidatorFactory.createRequiredFieldValidator("Middle Name is required!"));
        txtFieldLastName.setValidators(ValidatorFactory.createRequiredFieldValidator("Last Name is required!"));
        txtFieldCompany.setValidators(ValidatorFactory.createRequiredFieldValidator("Company is required!"));
        txtFieldTitle.setValidators(ValidatorFactory.createRequiredFieldValidator("Title is required!"));
        txtFieldPhone.setValidators(ValidatorFactory.createRequiredFieldValidator("Phone is required!"));
        txtFieldEmail.setValidators(ValidatorFactory.createRequiredFieldValidator("Email is required!"));
    }

    @Override
    public void resetForm() {
        super.resetForm();
        txtFieldFirstName.setText("");
        txtFieldMiddleName.setText("");
        txtFieldLastName.setText("");
        txtFieldCompany.setText("");
        txtFieldTitle.setText("");
        txtFieldPhone.setText("");
        txtFieldWebsite.setText("");
        txtFieldEmail.setText("");
        txtFieldAddress.setText("");
        txtFieldNote.setText("");
    }

    @Override
    public BarCodeData getQRData() {
        return BarCodeDataVCard.newInstance()
                .withContactName(ContactName.newInstance()
                        .withFirstName(txtFieldFirstName.getText())
                        .withMiddleName(txtFieldMiddleName.getText())
                        .withLastName(txtFieldLastName.getText())
                )
                .withCompany(txtFieldCompany.getText())
                .withTitle(txtFieldTitle.getText())
                .withPhoneNumber(PhoneNumber.newInstance().withType(PhoneNumberType.CELL).withNumber(txtFieldPhone.getText()))
                .withWebsite(txtFieldWebsite.getText())
                .withEmail(txtFieldEmail.getText())
                .withAddress(txtFieldAddress.getText())
                .withNote(txtFieldNote.getText());
    }
}
