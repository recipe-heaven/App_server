package no.twct.recipeheaven.lib;

import no.twct.recipeheaven.response.DataResponse;
import no.twct.recipeheaven.response.ErrorResponse;
import no.twct.recipeheaven.response.errors.ViolationErrorMessageBuilder;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;

/**
 * Provides helper method to a resource for creating responses of common types.
 * A response is ok(200) by default.
 */
public abstract class Resource {

    private Response.ResponseBuilder responseBuilder = Response.ok();

    /**
     * Creates a data response with the provided data object.
     *
     * @param data the object to include in the data response.
     */
    protected void createDataResponse(Object data) {
        responseBuilder = Response.ok(new DataResponse(data));
    }

    /**
     * Creates a data response if the provided object is not null.
     * If the object is null, creates a 404 response with the provided error message.
     *
     * @param data         the data to include in the data response. Is not included in 404 resposne
     * @param messageOn404 the message to return if the object is null
     */
    protected void createDataResponseOr404(Object data, String messageOn404) {
        if (data == null) {
            responseBuilder = Response.ok(new ErrorResponse(messageOn404)).status(Response.Status.NOT_FOUND);
        } else {
            this.createDataResponse(data);
        }
    }

    /**
     * Creates a error response with the provided object.
     *
     * @param errorData the object to include in the error response.
     */
    protected void createErrorResponse(Object errorData) {
        responseBuilder = Response.ok(new ErrorResponse(errorData));
    }

    /**
     * Create a error response with validation constraints messages from the provided exception.
     *
     * @param violationException exception to create violation messages from.
     */
    protected void createConstraintViolationResponse(ConstraintViolationException violationException) {
        var violations = new ViolationErrorMessageBuilder(violationException.getConstraintViolations()).getMessages();
        responseBuilder = Response.ok(new ErrorResponse(violations));
    }

    /**
     * Creates a 500 response
     */
    protected void serverError() {
        responseBuilder = Response.serverError();
    }

    protected Response.ResponseBuilder getResponseBuilder() {
        return this.responseBuilder;
    }

    /**
     * Builds the created response and returns it.
     */
    protected Response buildResponse() {
        return this.responseBuilder.build();
    }

}
