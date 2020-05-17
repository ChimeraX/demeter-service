package org.chimerax.demeter.repository;

import org.chimerax.common.repository.ChimeraXRepository;
import org.chimerax.demeter.entity.Review;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 23-Apr-20
 * Time: 10:57 PM
 */
public interface ReviewRepository extends ChimeraXRepository<Long, Review> {

    @Async
    CompletableFuture<Boolean> existsByRecipeIdAndUsername(final long recipeId, final String username);

    @Async
    CompletableFuture<Long> countAllByRecipe_Id(final long recipeId);
}
