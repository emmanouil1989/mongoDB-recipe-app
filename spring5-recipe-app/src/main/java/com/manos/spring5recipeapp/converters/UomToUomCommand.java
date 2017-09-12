package com.manos.spring5recipeapp.converters;

import com.manos.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.manos.spring5recipeapp.models.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UomToUomCommand implements Converter<UnitOfMeasure,UnitOfMeasureCommand> {
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure == null){
            return null;
        }
        final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setDescription(unitOfMeasure.getDescription());
        unitOfMeasureCommand.setId(unitOfMeasure.getId());
        return unitOfMeasureCommand;
    }
}
