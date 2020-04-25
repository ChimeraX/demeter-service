package org.chimerax.demeter.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.chimerax.demeter.api.RecipeSearch;
import org.chimerax.demeter.service.WebScrapper;
import org.chimerax.demeter.service.RecipeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 06-Apr-20
 * Time: 6:40 PM
 */

@RestController
@AllArgsConstructor
@RequestMapping("/scrap")
public class RecipeScrapController {

    private WebScrapper scrapper;

    @Data
    public static class RecipeScrap {
        String url;
        String category;
    }

    @PostMapping
    public ResponseEntity<Page<RecipeSearch>> save(@RequestBody RecipeScrap recipeScrap) throws Exception {
        scrapper.scrap(recipeScrap.url, recipeScrap.category);
        return ResponseEntity.ok().build();
    }

}
