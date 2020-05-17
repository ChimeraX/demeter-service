package org.chimerax.demeter.api;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 10-Apr-20
 * Time: 9:35 PM
 */
@Data
@Accessors(chain = true)
public class RecipeSearch {
    private Long id;

    private String name;

    private String image;

    private int duration;

    private long categoryId;

    private int calories;

    private long favorites;

    private boolean favorite;
}
