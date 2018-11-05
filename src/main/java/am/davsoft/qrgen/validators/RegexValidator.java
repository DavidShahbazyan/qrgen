package am.davsoft.qrgen.validators;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

public class RegexValidator extends ValidatorBase {
    private final String regex;

    public RegexValidator(String regex) {
        this.regex = regex;
    }

    public RegexValidator(String message, String regex) {
        this.regex = regex;
        setMessage(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void eval() {
        if (srcControl.get() instanceof TextInputControl) {
            evalTextInputField();
        }
    }

    private void evalTextInputField() {
        TextInputControl textField = (TextInputControl) srcControl.get();
        String text = textField.getText();
        try {
            hasErrors.set(false);
            if (!text.isEmpty())
                hasErrors.set(!text.matches(regex));
        } catch (Exception e) {
            hasErrors.set(true);
        }
    }

}
