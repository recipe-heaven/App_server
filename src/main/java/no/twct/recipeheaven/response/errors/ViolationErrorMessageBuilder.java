package no.twct.recipeheaven.response.errors;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Helper class for mapping constrain violations to a list of error messages.
 */
public class ViolationErrorMessageBuilder {

    List<ErrorMessage> violationMessages = new ArrayList<ErrorMessage>();

    public ViolationErrorMessageBuilder(Set<ConstraintViolation<?>> violations) {
        this.buildErrorMessages(violations);
    }

    /**
     * Extracts the Field and message for each violation and encapsulate them in a ErrorMessage.
     *
     * @param violations constraint violations to get error messages from
     */
    private void buildErrorMessages(Set<ConstraintViolation<?>> violations) {
        for (var violation : violations) {
            violationMessages.add(new ErrorMessage(violation.getPropertyPath().toString() + " " + violation.getMessage()));
        }
    }

    public List<ErrorMessage> getMessages() {
        return violationMessages;
    }
}
