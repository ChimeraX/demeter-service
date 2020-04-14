package org.chimerax.demeter.controller;

import lombok.AllArgsConstructor;
import org.chimerax.demeter.entity.Category;
import org.chimerax.demeter.repository.CategorySpecifications;
import org.chimerax.demeter.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 7:17 PM
 */
@Service
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(
            @RequestParam(name = "parent", required = false, defaultValue = "-1") long parentId,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") final int size) {
        if (parentId < 0) {
            return ResponseEntity.ok(categoryService.findAll(CategorySpecifications.isParentCategory(), PageRequest.of(page, size)));
        } else {
            return ResponseEntity.ok(categoryService.findAll(CategorySpecifications.byParentCategory(parentId), PageRequest.of(page, size)));
        }
    }

}
