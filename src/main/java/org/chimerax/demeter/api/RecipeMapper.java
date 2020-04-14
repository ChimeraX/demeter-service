package org.chimerax.demeter.api;

import org.chimerax.demeter.entity.Recipe;
import org.springframework.stereotype.Component;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 10-Apr-20
 * Time: 9:38 PM
 */
@Component
public class RecipeMapper {

    public RecipeSearch mapRecipeToSearch(final Recipe recipe) {
        return new RecipeSearch()
                .setId(recipe.getId())
                .setName(recipe.getName())
                .setDescription(recipe.getDescription())
                .setImage(recipe.getImage())
                .setDuration(recipe.getDuration())
                .setCategoryId(recipe.getCategoryId());
    }
}
