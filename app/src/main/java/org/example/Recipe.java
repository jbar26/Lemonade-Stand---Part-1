package org.example;

public class Recipe
{
    private int lemons;
    private int sugar;
    private double water;

    public Recipe()
    {
        lemons = 0;
        sugar = 0;
        water = 8;  // Starting recipe is just 8 cups of water
    }
    public Recipe(int lemons, int sugar, double water)
    {
            this.lemons = lemons;
            this.sugar = sugar;
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
    public double getWater()
    {
        return water;
    }
//    public void setLemons(int lemons)
//    {
//        this.lemons = lemons;
//    }
//    public void setSugar(int sugar)
//    {
//        this.sugar= sugar;
//    }
//    public void setWater(double water)
//    {
//        this.water = water;
//    }
//    public boolean isValidRecipe()
//    {
//        if (lemons==0 || water == 0)
//        {
//            return false;
//        }
//        else {return true;}
//    }

    public double getMultiplierForTastiness()
    {
        double currentParts = sugar + lemons + water;
        double ratioSugar = (double)sugar/currentParts;
        double ratioLemon = (double)lemons/currentParts;
        double ratioWater = water/currentParts;
        double idealSugar = (double)7/48;  // 7 parts of 48 total parts in ideal recipe
        double idealLemon = (double) 9/48;
        double idealWater = (double)32/48;
        double threshold = 0.1;  // determined by me for being close.
        boolean closeSugar = false;
        boolean closeLemons = false;
        boolean closeWater = false;
        boolean exactSugar = false;
        boolean exactLemons = false;
        boolean exactWater = false;
        if(Math.abs(ratioSugar - idealSugar) < 0.01)
        {
            exactSugar = true;
            closeSugar = true;
        }
        else if(Math.abs(ratioSugar - idealSugar) < threshold)
        {
            closeSugar = true;
        }
        if(Math.abs(ratioLemon - idealLemon)<0.01)
        {
            exactLemons = true;
            closeLemons = true;
        }
        else if(Math.abs(ratioLemon - idealLemon)<threshold)
        {
            closeLemons = true;
        }
        if(Math.abs(ratioWater - idealWater)<0.01)
        {
            exactWater = true;
            closeWater = true;
        }
        else if(Math.abs(ratioWater - idealWater)<threshold)
        {
            closeWater = true;
        }

        if(water == 0)
        {
            return 0;
        }
        else if(lemons == 0 && sugar == 0)
        {
            return 0.1;
        }
        else if(sugar == 0)
        {
            return 0.5;
        }
        else if(exactSugar && exactLemons && exactWater)
        {
            return 1.5;
        }
        else if(closeSugar && closeLemons && closeWater) {
            return 1.25;
        }
        return 1;  // no special multiplier
    }
    public String getRecipeRating()
    {
        double multiplier = this.getMultiplierForTastiness();
        if(multiplier == 0.0)
        {
            return "Yuck. Your recipe was just lemon and sugar. Nobody liked it.";
        }
        else if(multiplier == 0.1)
        {
            return "Blah. Your recipe was just water.  Who wants to pay for water?";
        }
        else if(multiplier == 0.5)
        {
            return "Hmmm... Your recipe was just water and lemons, which was refreshing, but you can make better lemonade.";
        }
        else if(multiplier == 1.25)
        {
            return "Ooooo... Your recipe was pretty good.  Can it get even better?";
        }
        else if(multiplier == 1.5)
        {
            return "Wow! Your recipe is hard to beat!  Great work!";
        }
        else  // multiplier = 1.0
        {
            return "Your recipe was good, but average.  I wonder if you can improve upon it?";  // should never get here.
        }
    }

    public String toString()
    {
        return "Current Recipe: Lemons = " + lemons + " Sugar = " + sugar + " Water = " + water;
    }
}
