package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.UnitOfMeasureRepository;
import com.manos.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.manos.spring5recipeapp.converters.UomToUomCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    UomToUomCommand uomToUomCommand;

    /**
     * method to use stream and go through the iterator of uoms and split them and converter them to uomCommand and add them to a collection which then convert it
     * to a set.
     * @return
     */
    @Override
    public Set<UnitOfMeasureCommand> findAll() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(uomToUomCommand::convert)
                .collect(Collectors.toSet());
    }
}
