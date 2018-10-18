package am.davsoft.qrgen.validators;

import com.jfoenix.validation.RequiredFieldValidator;

import java.util.function.Supplier;

public class ConditionalRequiredFieldValidator extends RequiredFieldValidator {
    private final Supplier<Boolean> condition;

    public ConditionalRequiredFieldValidator(Supplier<Boolean> condition) {
        this.condition = condition;
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
