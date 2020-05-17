package org.chimerax.demeter.service.category;

import org.chimerax.demeter.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 7:11 PM
 */
public interface CategoryService {

    Page<Category> findAll(final Pageable pageable);

    Page<Category> findAll(final Specification<Category> spec, final Pageable pageable);

}
