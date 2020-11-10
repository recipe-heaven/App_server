package no.twct.recipeheaven.search.entity;

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
