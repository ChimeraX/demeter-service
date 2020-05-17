package org.chimerax.demeter.repository;

import org.chimerax.common.repository.ChimeraXRepository;
import org.chimerax.demeter.entity.QuantifiedIngredient;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-Apr-20
 * Time: 12:45 AM
 */
public interface QuantifiedIngredientRepository extends ChimeraXRepository<Long, QuantifiedIngredient> {

    long countAllByIngredient_Id(long ingredient_id);
}
