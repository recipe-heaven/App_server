package no.twct.recipeheaven.search.entity;

import lombok.Data;
import no.twct.recipeheaven.entity.DisplayableEntity;

/**
 * A result item has a type and a result {@link Result}.
 * It is a wrapper class for filtering the type when
 * it is parsed on the frontend, as a result can be many types.
 */
@Data
public class ResultItem {
    String type;
    DisplayableEntity data;

    public ResultItem(String type, DisplayableEntity data) {
        this.setType(type);
        this.setData(data);
    }
}
