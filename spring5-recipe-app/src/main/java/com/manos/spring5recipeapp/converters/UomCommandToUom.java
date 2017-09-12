package com.manos.spring5recipeapp.converters;

import com.manos.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.manos.spring5recipeapp.models.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UomCommandToUom implements Converter<UnitOfMeasureCommand,UnitOfMeasure> {
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand == null){
            return null;
        }
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(unitOfMeasureCommand.getDescription());
        unitOfMeasure.setId(unitOfMeasureCommand.getId());
        return unitOfMeasure;
    }
}
