package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeTester
{
    @Test
    public void testRecipeLemons0Sugar0Water0()
    {
        Recipe recipe = new Recipe(0, 0, 0);
        assertEquals(0, recipe.getLemons());
        assertEquals(0, recipe.getSugar());
        assertEquals(0, recipe.getWater());
    }
    @Test
    public void testRecipeLemons1Sugar0Water0()
    {
        Recipe recipe = new Recipe(1, 0, 0);
        assertEquals(1, recipe.getLemons());
        assertEquals(0, recipe.getSugar());
        assertEquals(0, recipe.getWater());
    }
    @Test
    public void testRecipeLemons0Sugar1Water0()
    {
        Recipe recipe = new Recipe(0, 1, 0);
        assertEquals(0, recipe.getLemons());
        assertEquals(1, recipe.getSugar());
        assertEquals(0, recipe.getWater());
    }
    @Test
    public void testRecipeLemons0Sugar0Water1()
    {
        Recipe recipe = new Recipe(0, 0, 1);
        assertEquals(0, recipe.getLemons());
        assertEquals(0, recipe.getSugar());
        assertEquals(1, recipe.getWater());
    }
    @Test
    public void testRecipeLemons1Sugar1Water1()
    {
        Recipe recipe = new Recipe(1, 1, 1);
        assertEquals(1, recipe.getLemons());
        assertEquals(1, recipe.getSugar());
        assertEquals(1, recipe.getWater());
    }
    @Test
    public void testRecipeLemons2Sugar2Water2()
    {
        Recipe recipe = new Recipe(2, 2, 2);
        assertEquals(2, recipe.getLemons());
        assertEquals(2, recipe.getSugar());
        assertEquals(2, recipe.getWater());
    }
    @Test
    public void testRecipeLemons1Sugar2Water3()
    {
        Recipe recipe = new Recipe(1, 2, 3);
        assertEquals(1, recipe.getLemons());
        assertEquals(2, recipe.getSugar());
        assertEquals(3, recipe.getWater());
    }
    @Test
    public void testRecipeLemons5Sugar7Water6()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        assertEquals(5, recipe.getLemons());
        assertEquals(7, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }
    @Test
    public void testSetLemons0()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setLemons(0);
        assertEquals(0, recipe.getLemons());
        assertEquals(7, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }
    @Test
    public void testSetLemons1()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setLemons(1);
        assertEquals(1, recipe.getLemons());
        assertEquals(7, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }
    @Test
    public void testSetLemons2()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setLemons(2);
        assertEquals(2, recipe.getLemons());
        assertEquals(7, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }
    @Test
    public void testSetLemons19()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setLemons(19);
        assertEquals(19, recipe.getLemons());
        assertEquals(7, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }
    @Test
    public void testSetSugar0()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setSugar(0);
        assertEquals(5, recipe.getLemons());
        assertEquals(0, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }
    @Test
    public void testSetSugar1()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setSugar(1);
        assertEquals(5, recipe.getLemons());
        assertEquals(1, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }
    @Test
    public void testSetSugar2()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setSugar(2);
        assertEquals(5, recipe.getLemons());
        assertEquals(2, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }
    @Test
    public void testSetSugar19()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setSugar(19);
        assertEquals(5, recipe.getLemons());
        assertEquals(19, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }
    @Test
    public void testSetWater0()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setWater(0);
        assertEquals(5, recipe.getLemons());
        assertEquals(7, recipe.getSugar());
        assertEquals(0, recipe.getWater());
    }
    @Test
    public void testSetWater1()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setWater(1);
        assertEquals(5, recipe.getLemons());
        assertEquals(7, recipe.getSugar());
        assertEquals(1, recipe.getWater());
    }
    @Test
    public void testSetWater2()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setWater(2);
        assertEquals(5, recipe.getLemons());
        assertEquals(7, recipe.getSugar());
        assertEquals(2, recipe.getWater());
    }
    @Test
    public void testSetWater19()
    {
        Recipe recipe = new Recipe(5, 7, 6);
        recipe.setWater(19);
        assertEquals(5, recipe.getLemons());
        assertEquals(7, recipe.getSugar());
        assertEquals(19, recipe.getWater());
    }
    @Test
    public void testIsValidRecipe0lemons0sugar0water()
    {
        Recipe recipe = new Recipe(0, 0, 0);
        assertEquals(false, recipe.isValidRecipe());
    }
    @Test
    public void testIsValidRecipe0Lemons()
    {
        Recipe recipe = new Recipe(0, 2, 5);
        assertEquals(false, recipe.isValidRecipe());
    }
    @Test
    public void testIsValidRecipe0Water()
    {
        Recipe recipe = new Recipe(2, 2, 0);
        assertEquals(false, recipe.isValidRecipe());
    }
    @Test
    public void testIsValidRecipe1Lemon1Water()
    {
        Recipe recipe = new Recipe(1, 2, 1);
        assertEquals(true, recipe.isValidRecipe());
    }
    @Test
    public void testIsValidRecipe5Lemon6Water()
    {
        Recipe recipe = new Recipe(5, 2, 6);
        assertEquals(true, recipe.isValidRecipe());
    }
}
