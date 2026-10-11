package org.example;

public class Recipe
{
    private int lemons;
    private int sugar;
    private int water;

    public Recipe(int lemons, int sugar, int water)
    {
        this.lemons = lemons;
        this. sugar = sugar;
        this.water = water;
    }
    public int getLemons()
    {
        return lemons;
    }
    public int getSugar()
    {
        return sugar;
    }
    public int getWater()
    {
        return water;
    }
    public void setLemons(int lemons)
    {
        this.lemons = lemons;
    }
    public void setSugar(int sugar)
    {
        this.sugar= sugar;
    }
    public void setWater(int water)
    {
        this.water = water;
    }
    public boolean isValidRecipe()
    {
        if (lemons==0 || water == 0)
        {
            return false;
        }
        else {return true;}
    }
}
