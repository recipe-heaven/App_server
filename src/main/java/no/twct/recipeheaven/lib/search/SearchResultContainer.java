package no.twct.recipeheaven.lib.search;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Holds all returned results from a search
 */
@Data
@NoArgsConstructor
public class SearchResultContainer {

    List<ResultItem> result;

}
