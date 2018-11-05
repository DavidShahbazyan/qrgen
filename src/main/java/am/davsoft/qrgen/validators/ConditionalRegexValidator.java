package am.davsoft.qrgen.validators;

import java.util.function.Supplier;

public class ConditionalRegexValidator extends RegexValidator {
    private final Supplier<Boolean> condition;

    public ConditionalRegexValidator(String regex, Supplier<Boolean> condition) {
        super(regex);
        this.condition = condition;
    }

    public ConditionalRegexValidator(String message, String regex, Supplier<Boolean> condition) {
        super(message, regex);
        this.condition = condition;
        setMessage(message);
    }

    @Override
    protected void eval() {
        if (condition.get()) {
            super.eval();
        } else {
            hasErrors.set(false);
        }
    }
}
