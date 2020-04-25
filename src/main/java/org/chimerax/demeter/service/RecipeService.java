package org.chimerax.demeter.service;

import org.chimerax.common.exception.NotFoundException;
import org.chimerax.demeter.api.RecipeSearch;
import org.chimerax.demeter.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 5:26 PM
 */

public interface RecipeService {

    Page<RecipeSearch> findAll(final Specification<Recipe> spec, final Pageable pageable);

    Optional<Recipe> findById(final Long id);

    boolean existsById(final Long id);

    void save(final Recipe recipe);

    void update(final Recipe recipe) throws NotFoundException;

    void deleteById(final Long id) throws NotFoundException;

}
