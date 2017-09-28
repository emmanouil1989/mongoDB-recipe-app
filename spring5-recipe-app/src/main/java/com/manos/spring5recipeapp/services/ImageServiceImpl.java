package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.RecipeRepository;
import com.manos.spring5recipeapp.models.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    public RecipeRepository recipeRepository;

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try {
            Byte[] bytes = new Byte[file.getBytes().length];

            int i = 0;
            for(byte b: file.getBytes()){
                bytes[i++]=b;
            }
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

            Recipe recipe = recipeOptional.get();

            recipe.setImage(bytes);

            recipeRepository.save(recipe);
            log.info("image has been persisted");

        }catch (IOException e) {
            log.error("not persist image");
            e.printStackTrace();
        }

    }
}
