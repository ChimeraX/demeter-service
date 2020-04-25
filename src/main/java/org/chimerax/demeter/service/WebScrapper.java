package org.chimerax.demeter.service;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.val;
import org.chimerax.demeter.entity.Ingredient;
import org.chimerax.demeter.entity.QuantifiedIngredient;
import org.chimerax.demeter.entity.Recipe;
import org.chimerax.demeter.entity.RecipeStep;
import org.chimerax.demeter.repository.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 23-Apr-20
 * Time: 11:42 PM
 */
@Component
@AllArgsConstructor
public class WebScrapper {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private QuantifiedIngredientRepository quantifiedIngredientRepository;
    private RecipeStepRepository recipeStepRepository;
    private CategoryRepository categoryRepository;

    public void scrap(final String url, final String category) throws Exception {
        final long categoryId = categoryRepository.findByName(category).getId();
        Document document = Jsoup.connect(url).get();

        String name = name(document);
        String image = image(document);
        int calories = calories(document);
        int duration = duration(document);
        val ingredients = ingredients(document);
        val steps = steps(document);
        Recipe recipe = new Recipe()
                .setName(name)
                .setImage(image)
                .setCalories(calories)
                .setDuration(duration)
                .setCategoryId(categoryId);

        final Recipe afterSave = recipeRepository.save(recipe);

        final long recipeId = recipe.getId();
        steps.forEach(step -> step.setRecipeId(recipeId));
        recipeStepRepository.saveAll(steps);
        ingredients.forEach(qi -> {
            final Ingredient ingredient = ingredientRepository.save(qi.getIngredient());
            qi
                    .setRecipe(afterSave)
                    .setRecipeId(afterSave.getId())
                    .setIngredient(ingredient);
            quantifiedIngredientRepository.save(qi);
        });
    }

    private String image(final Document document) {
        Element element = document.getElementsByClass("content-lede-image-wrap").first();
        Element image = element.getElementsByTag("img").first();
        return image.attr("data-src");
    }

    private Set<RecipeStep> steps(final Document document) {
        Element element = document.getElementsByClass("direction-lists").first();
        Elements elements = element.getElementsByTag("li");
        Set<RecipeStep> steps = new HashSet<>();
        for (int i = 0; i < elements.size(); i++) {
            steps.add(new RecipeStep().setOrder(i).setDescription(elements.get(i).text()));
        }
        return steps;
    }

    private String name(final Document document) {
        return document.getElementsByClass("content-hed recipe-hed").text();
    }

    private Set<QuantifiedIngredient> ingredients(final Document document) {
        Elements elements = document.getElementsByClass("ingredient-item");
        Set<QuantifiedIngredient> ingredients = new HashSet<>();
        for (Element element : elements) {
            String amount = element.getElementsByClass("ingredient-amount").text();
            if (amount.equals("")) {
                amount = "0 piece";
            }
            val amountBits = amount.split(" ");
            if (amountBits[0].contains("/")) {
                amountBits[0] = "1";
            }
            int quantity = toInt(amountBits[0]);
            String unit = amountBits.length > 1 ? amountBits[1] : "piece";
            String name = element.getElementsByClass("ingredient-description").text();
            QuantifiedIngredient ingredient = new QuantifiedIngredient()
                    .setIngredient(new Ingredient().setName(name))
                    .setQuantity(quantity)
                    .setUnit(unit);
            ingredients.add(ingredient);
        }
        return ingredients;
    }


    private int duration(final Document document) {
        String totalTime = document.getElementsByClass("total-time-amount").text();
        val totalTimeBits = totalTime.split(" ");
        return toInt(totalTimeBits[0]) * 60 + toInt(totalTimeBits[2]);
    }

    private int calories(final Document document) {
        final String calories = document.getElementsByClass("cal-per-serv-amount").text();
        if (calories.equals("")) {
            return 0;
        }
        return toInt(calories);
    }

    private int toInt(final String s) {
        return Integer.parseInt(s);
    }
}
