package no.twct.recipeheaven.response;

import javax.json.bind.annotation.JsonbProperty;

public class ErrorResponse extends AbstractResponse {

    private Object error = null;

    public ErrorResponse(Object error) {
        this.error = error;
    }

    @Override
    @JsonbProperty("error")
    public Object getResponse() {
        return this.error;
    }
}
