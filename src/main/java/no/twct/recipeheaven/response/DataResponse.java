package no.twct.recipeheaven.response;

import javax.json.bind.annotation.JsonbProperty;

public class DataResponse extends AbstractResponse {

    private Object data = null;

    public DataResponse() {
    }

    public DataResponse(Object data) {
        this.data = data;
    }

    @Override
    @JsonbProperty("data")
    public Object getResponse() {
        return data;
    }
}
