package org.chimerax.demeter.repository;

import lombok.experimental.UtilityClass;
import org.chimerax.demeter.entity.Review;
import org.springframework.data.jpa.domain.Specification;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 7:19 PM
 */

@UtilityClass
public class ReviewSpecifications {

    public static Specification<Review> byRecipe(final long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("recipeId"), id);
    }

    public static Specification<Review> byUser(final long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), id);
    }
}
