package com.manos.spring5recipeapp.services;

import com.manos.spring5recipeapp.Repositories.UnitOfMeasureRepository;
import com.manos.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.manos.spring5recipeapp.converters.UomToUomCommand;
import com.manos.spring5recipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureServiceImpl  impl;

    @Mock
    private UnitOfMeasureRepository repo;
    private UomToUomCommand converter = new UomToUomCommand();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        impl = new UnitOfMeasureServiceImpl();
        impl.unitOfMeasureRepository=repo;
        impl.uomToUomCommand=converter;
    }

    @Test
    public void findAll() throws Exception {

        ArrayList<UnitOfMeasure> list = new ArrayList<>();
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(1L);
        uom.setDescription("malaka");
        list.add(uom);
        when(repo.findAll()).thenReturn(list);
        Set<UnitOfMeasureCommand> unitOfMeasureCommandSet = impl.findAll();

        assertNotNull(unitOfMeasureCommandSet);
        assertEquals(unitOfMeasureCommandSet.size(),1);
    }

}