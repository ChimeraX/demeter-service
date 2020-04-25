package org.chimerax.demeter.repository;

import org.chimerax.commonservice.api.repository.ChimeraXRepository;
import org.chimerax.demeter.entity.Category;

/**
 * Author: Silviu-Mihnea
 * Date: 04-Apr-20
 * Time: 3:28 PM
 */
public interface CategoryRepository extends ChimeraXRepository<Long, Category> {

    Category findByName(final String name);
}
