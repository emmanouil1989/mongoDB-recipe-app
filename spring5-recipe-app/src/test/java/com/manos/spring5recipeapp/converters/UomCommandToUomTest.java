package com.manos.spring5recipeapp.converters;
import com.manos.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.manos.spring5recipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UomCommandToUomTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    UomCommandToUom converter;

    @Before
    public void setUp() throws Exception {
        converter = new UomCommandToUom();

    }

    @Test
    public void testNullParamter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }

}