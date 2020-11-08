package no.twct.recipeheaven.lib.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuSearchResult extends Result {
    List<String> days;
}
