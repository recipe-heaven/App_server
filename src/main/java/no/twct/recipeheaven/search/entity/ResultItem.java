package no.twct.recipeheaven.search.entity;

import lombok.Data;

/**
 * A result item has a type and a result {@link Result}.
 * It is a wrapper class for filtering the type when
 * it is parsed on the frontend, as a result can be many types.
 */
@Data
public class ResultItem {
    String type;
    Result data;

    public ResultItem(String type, Result data) {
        this.setType(type);
        this.setData(data);
    }
}
