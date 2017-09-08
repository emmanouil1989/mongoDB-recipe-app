package com.manos.spring5recipeapp.models;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void init(){

        category = new Category();
    }


    @Test
    public void getDescription() throws Exception {
        category.setDescription("MANOS");
        assertEquals(category.getDescription(),"MANOS");
    }


    @Test
    public void setId() throws Exception {
        Long idvalue= 4L;
        category.setId(idvalue);
        assertEquals(idvalue,category.getId());
    }

}