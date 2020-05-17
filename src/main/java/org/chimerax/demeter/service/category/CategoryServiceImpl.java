package org.chimerax.demeter.service.category;

import lombok.AllArgsConstructor;
import org.chimerax.demeter.entity.Category;
import org.chimerax.demeter.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 7:16 PM
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private final Map<Long, Set<Category>> mapping = new HashMap<>();

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> findAll(Specification<Category> spec, Pageable pageable) {
        return categoryRepository.findAll(spec, pageable);
    }

    @Override
    public Set<Category> findAllChildren(final long parent_id) {
        return _findAllChildren(parent_id);
    }

    private Set<Category> _findAllChildren(final long parent_id) {
        final Set<Category> mapped = mapping.get(parent_id);
        if (mapped != null) {
            return mapped;
        } else {
            final Set<Category> result = new HashSet<>();
            final Set<Category> directChildren = categoryRepository.findAllByParent_Id(parent_id);
            for (final Category directChild : directChildren) {
                result.addAll(_findAllChildren(directChild.getId()));
            }
            return result;
        }
    }

}
