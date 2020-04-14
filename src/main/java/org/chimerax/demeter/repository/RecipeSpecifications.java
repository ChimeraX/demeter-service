package org.chimerax.demeter.repository;

import lombok.experimental.UtilityClass;
import org.chimerax.demeter.entity.Recipe;
import org.springframework.data.jpa.domain.Specification;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 7:19 PM
 */

@UtilityClass
public class RecipeSpecifications {

    public static Specification<Recipe> byName(final String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Recipe> byCategory(final long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryId"), id);
    }
}
