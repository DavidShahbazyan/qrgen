package am.davsoft.qrgen.validators;

import com.jfoenix.validation.NumberValidator;

import java.util.function.Supplier;

public class ConditionalNumberValidator extends NumberValidator {
    private final Supplier<Boolean> condition;

    public ConditionalNumberValidator(Supplier<Boolean> condition) {
        super();
        this.condition = condition;
    }

    public ConditionalNumberValidator(String message, Supplier<Boolean> condition) {
        super(message);
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
