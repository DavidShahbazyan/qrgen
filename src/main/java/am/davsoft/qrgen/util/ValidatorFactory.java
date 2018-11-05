package am.davsoft.qrgen.util;

import am.davsoft.qrgen.validators.ConditionalNumberValidator;
import am.davsoft.qrgen.validators.ConditionalRegexValidator;
import am.davsoft.qrgen.validators.ConditionalRequiredFieldValidator;
import am.davsoft.qrgen.validators.RegexValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.util.function.Supplier;

public final class ValidatorFactory {
    private static final String EM1 = "1em";
    private static final String ERROR = "error";
    private static final GlyphIcon REQUIRED_VALIDATOR_ICON = GlyphsBuilder.create(FontAwesomeIconView.class)
            .glyph(FontAwesomeIcon.WARNING)
            .size(EM1)
            .styleClass(ERROR)
            .build();
    private static final String PHONE_NUMBER_REGEX = "^\\+?\\d+$";

    public static RequiredFieldValidator createRequiredFieldValidator(String message) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(message);
        validator.setIcon(REQUIRED_VALIDATOR_ICON);
        return validator;
    }

    public static ConditionalRequiredFieldValidator createConditionalRequiredFieldValidator(Supplier<Boolean> condition, String message) {
        ConditionalRequiredFieldValidator validator = new ConditionalRequiredFieldValidator(condition);
        validator.setMessage(message);
        validator.setIcon(REQUIRED_VALIDATOR_ICON);
        return validator;
    }

    public static NumberValidator createNumberValidator(String message) {
        NumberValidator validator = new NumberValidator(message);
        validator.setIcon(REQUIRED_VALIDATOR_ICON);
        return validator;
    }

    public static ConditionalNumberValidator createConditionalNumberValidator(Supplier<Boolean> condition, String message) {
        ConditionalNumberValidator validator = new ConditionalNumberValidator(condition);
        validator.setMessage(message);
        validator.setIcon(REQUIRED_VALIDATOR_ICON);
        return validator;
    }

    public static RegexValidator createPhoneNumberValidator(String message) {
        RegexValidator validator = new RegexValidator(message, PHONE_NUMBER_REGEX);
        validator.setIcon(REQUIRED_VALIDATOR_ICON);
        return validator;
    }

    public static ConditionalRegexValidator createConditionalPhoneNumberValidator(Supplier<Boolean> condition, String message) {
        ConditionalRegexValidator validator = new ConditionalRegexValidator(message, PHONE_NUMBER_REGEX, condition);
        validator.setIcon(REQUIRED_VALIDATOR_ICON);
        return validator;
    }
}
