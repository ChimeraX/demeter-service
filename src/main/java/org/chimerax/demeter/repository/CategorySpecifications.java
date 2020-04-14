package org.chimerax.demeter.repository;

import lombok.experimental.UtilityClass;
import org.chimerax.demeter.entity.Category;
import org.springframework.data.jpa.domain.Specification;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 7:22 PM
 */

@UtilityClass
public class CategorySpecifications {

    public static Specification<Category> byParentCategory(final long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("parentId"), id);
    }

    public static Specification<Category> isParentCategory() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("parentId"));
    }
}
