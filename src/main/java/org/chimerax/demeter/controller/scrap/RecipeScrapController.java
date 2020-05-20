package org.chimerax.demeter.controller.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.chimerax.demeter.api.RecipeSearch;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
