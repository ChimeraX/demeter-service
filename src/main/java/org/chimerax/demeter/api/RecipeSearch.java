package org.chimerax.demeter.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.chimerax.demeter.entity.Category;
import org.chimerax.demeter.entity.QuantifiedIngredient;
import org.chimerax.demeter.entity.RecipeStep;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    private String description;

    private String image;

    private int duration;

    private long categoryId;
}
