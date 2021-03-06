package org.chimerax.demeter.repository;

import org.chimerax.common.repository.ChimeraXRepository;
import org.chimerax.demeter.entity.Category;

import java.util.Set;

/**
 * Author: Silviu-Mihnea
 * Date: 04-Apr-20
 * Time: 3:28 PM
 */
public interface CategoryRepository extends ChimeraXRepository<Long, Category> {

    Category findByName(final String name);

    Set<Category> findAllByParent_Id(final long parent_id);
}
