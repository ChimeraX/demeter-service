package org.chimerax.demeter.service;

import lombok.AllArgsConstructor;
import org.chimerax.demeter.entity.Category;
import org.chimerax.demeter.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 7:16 PM
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> findAll(Specification<Category> spec, Pageable pageable) {
        return categoryRepository.findAll(spec, pageable);
    }
}
