package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * Base class for all search results.
 */
@Data
@NoArgsConstructor
public abstract class Result {
    String name;
    BigInteger id;
}
