package no.twct.recipeheaven.search.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Result {
    String name;
    String title = "test";
}
