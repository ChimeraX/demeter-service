package org.chimerax.demeter.service;

import lombok.AllArgsConstructor;
import org.chimerax.commonservice.api.exception.NotFoundException;
import org.chimerax.demeter.api.RecipeMapper;
import org.chimerax.demeter.api.RecipeSearch;
import org.chimerax.demeter.entity.Recipe;
import org.chimerax.demeter.repository.RecipeRepository;
import org.chimerax.demeter.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 5:30 PM
 */
@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private ReviewRepository reviewRepository;
    private RecipeRepository recipeRepository;
    private RecipeMapper recipeMapper;

    @Override
    public Page<RecipeSearch> findAll(Specification<Recipe> spec, Pageable pageable) {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return recipeRepository.findAll(spec, pageable)
                .map(recipe -> map(recipe, username));
    }

    private RecipeSearch map(final Recipe recipe, final String username) {
        final RecipeSearch recipeSearch = recipeMapper.mapRecipeToSearch(recipe);
        reviewRepository.countAllByRecipe_Id(recipe.getId()).thenAccept(recipeSearch::setFavorites);
        reviewRepository.existsByRecipeIdAndUsername(recipe.getId(), username).thenAccept(recipeSearch::setFavorite);
        return recipeSearch;
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return recipeRepository.existsById(id);
    }

    @Override
    public void save(final Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public void update(final Recipe recipe) throws NotFoundException {
        recipeRepository.save(recipe);
    }

    @Override
    public void deleteById(final Long id) throws NotFoundException {
        recipeRepository.deleteById(id);
    }
}
