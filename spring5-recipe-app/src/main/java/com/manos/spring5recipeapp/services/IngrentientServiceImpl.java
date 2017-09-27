package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.RecipeRepository;
import com.manos.spring5recipeapp.Repositories.UnitOfMeasureRepository;
import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.converters.IngredientCommandToIngredient;
import com.manos.spring5recipeapp.converters.IngredientToIngrentientCommand;
import com.manos.spring5recipeapp.models.Ingredient;
import com.manos.spring5recipeapp.models.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class IngrentientServiceImpl implements IngrentientService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientToIngrentientCommand ingredientToIngrentientCommand;

    @Autowired
    UnitOfMeasureRepository repo;

    @Autowired
    IngredientCommandToIngredient ingredientCommandToIngredient;


    @Override
    public IngredientCommand findByRecipeIdAndIngrentientId(Long recipeId, Long ingrentientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            log.error("there is no recipeOptional with that id");
        }

        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingrentientId))
                .map(ingredient -> ingredientToIngrentientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.error("ingredient is not present with that id");
        }
        return ingredientCommandOptional.get();
    }


    @Override
    @Transactional
    public IngredientCommand saveIngredient(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(repo
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                Ingredient newIngredient = ingredientCommandToIngredient.convert(command);
                newIngredient.setRecipe(recipe);
                recipe.getIngredients().add(newIngredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> optional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            if(!ingredientOptional.isPresent()){
                optional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();

            }
            //to do check for fail
            return ingredientToIngrentientCommand.convert(
                    optional.get());
        }
    }

    @Override
    public void deleteIngredient(Long recipeId, Long ingrentientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            log.error("recipe not found: "+ recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingrentientId))
                .findFirst();
        if(ingredientOptional.isPresent()){
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setRecipe(null);
            recipe.getIngredients().remove(ingredient);
        }

        recipeRepository.save(recipe);

    }


}
