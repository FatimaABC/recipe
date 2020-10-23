package com.example.recipe.services;

import com.example.recipe.domain.Recipe;
import com.example.recipe.repositories.RecipesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipesRepository recipesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipesRepository);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipesRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipesRepository, times(1)).findById(anyLong());
        verify(recipesRepository, never()).findAll();
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet hashSet = new HashSet();
        hashSet.add(recipe);

        when(recipesRepository.findAll()).thenReturn(hashSet);
        Set<Recipe> recipeSet = recipeService.getRecipes();

        assertEquals(recipeSet.size(), 1);
        verify(recipesRepository, times(1)).findAll();
    }
}