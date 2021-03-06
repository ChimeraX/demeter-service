package org.chimerax.demeter.service.recipe;

import lombok.AllArgsConstructor;
import lombok.val;
import org.chimerax.common.exception.NotFoundException;
import org.chimerax.demeter.api.RecipeMapper;
import org.chimerax.demeter.api.RecipeSearch;
import org.chimerax.demeter.entity.Recipe;
import org.chimerax.demeter.entity.Review;
import org.chimerax.demeter.repository.RecipeRepository;
import org.chimerax.demeter.repository.ReviewRepository;
import org.chimerax.demeter.service.oauth.OAuthService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private OAuthService oAuthService;
    private RecipeMapper recipeMapper;

    @Override
    public Page<RecipeSearch> findAll(Specification<Recipe> spec, Pageable pageable) {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        val recipes = recipeRepository.findAll(spec, pageable);
        val users = recipes.getContent().stream().map(Recipe::getCreator).collect(Collectors.toSet());
        // oAuthService.load(users);
        return recipes.map(recipe -> map(recipe, username));
    }

    private RecipeSearch map(final Recipe recipe, final String username) {
        final RecipeSearch recipeSearch = recipeMapper.mapRecipeToSearch(recipe);
        reviewRepository.countAllByRecipeId(recipe.getId()).thenAccept(recipeSearch::setFavorites);
        reviewRepository.existsByRecipeIdAndUsername(recipe.getId(), username)
                .thenAccept(recipeSearch::setFavorite);
        // recipeSearch.setCreator(oAuthService.getForUsername(username));
        return recipeSearch;
    }

    @Override
    public Optional<Recipe> findById(final long id) {
        return recipeRepository.findById(id);
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
    public void toggleFavorite(final long id) {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        val optional = reviewRepository.findByRecipeIdAndUsername(id, username);
        final Review review = optional.orElse(new Review().setRecipeId(id).setUsername(username));
        review.toggleFavorite();
        reviewRepository.save(review);
    }

    @Override
    public void deleteById(final long id) throws NotFoundException {
        recipeRepository.deleteById(id);
    }
}
