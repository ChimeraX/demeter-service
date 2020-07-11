package org.chimerax.demeter.repository;

import org.chimerax.common.repository.ChimeraXRepository;
import org.chimerax.demeter.entity.Review;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 23-Apr-20
 * Time: 10:57 PM
 */
public interface ReviewRepository extends ChimeraXRepository<Long, Review> {

    @Async
    default CompletableFuture<Boolean> existsByRecipeIdAndUsername(final long recipeId, final String username) {
        return existsByRecipeIdAndUsernameAndFavorite(recipeId, username, true);
    }

    @Async
    CompletableFuture<Boolean> existsByRecipeIdAndUsernameAndFavorite(final long recipeId,
                                                                      final String username,
                                                                      final boolean favorite);

    Optional<Review> findByRecipeIdAndUsername(final long recipeId, final String username);

    @Async
    default CompletableFuture<Long> countAllByRecipeId(final long recipeId) {
        return countAllByRecipeIdAndFavorite(recipeId, true);
    }

    @Async
    CompletableFuture<Long> countAllByRecipeIdAndFavorite(final long recipeId,
                                                          final boolean favorite);
}
