package org.chimerax.demeter.controller;

import lombok.AllArgsConstructor;
import org.chimerax.common.exception.NotFoundException;
import org.chimerax.demeter.api.RecipeSearch;
import org.chimerax.demeter.entity.Recipe;
import org.chimerax.demeter.repository.RecipeSpecifications;
import org.chimerax.demeter.service.RecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 6:40 PM
 */

@RestController
@AllArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<Page<RecipeSearch>> findAll(
            @RequestParam(name = "category", required = false, defaultValue = "-1") final long category,
            @RequestParam(name = "name", required = false, defaultValue = "") final String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") final int size) {
        Specification<Recipe> specification = null;
        if (category > 0) {
            specification = RecipeSpecifications.byCategory(category);
        }
        if (!name.equals("")) {
            specification = RecipeSpecifications.byName(name).and(specification);
        }
        return ResponseEntity.ok(recipeService.findAll(specification, PageRequest.of(page, size)));
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<Recipe> findById(@PathVariable final long id) {
        return ResponseEntity.of(recipeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Recipe recipe) throws NotFoundException {
        recipeService.update(recipe);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<Void> deleteById(@PathVariable final long id) throws NotFoundException {
        recipeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
