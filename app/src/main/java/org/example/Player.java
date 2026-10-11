package org.example;

public class Player
{
    private double balance;
    private int numLemons;
    private int numSugar;
    private int numPitchers;
    private Recipe recipe;
    private double pricePerCup;
    // no water variable - infinite

    public Player()
    {
        balance = 100;
        numLemons = 0;
        numSugar = 0;
        numPitchers = 0;
        recipe = new Recipe(0, 0, 0);
        pricePerCup = 0;
    }

    public double getBalance()
    {
        return balance;
    }
    public int getNumLemons()
    {
        return numLemons;
    }
    public int getNumSugar()
    {
        return numSugar;
    }
    public double getPricePerCup()
    {
        return pricePerCup;
    }
    public Recipe getRecipe()  // returns reference to this object so can make changes
    {
        return recipe;
    }
    public int getNumPitchers()
    {
        return numPitchers;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    public void setNumLemons(int lemons)
    {
        this.numLemons = lemons;
    }
    public void setNumSugar(int sugar)
    {
        this.numSugar = sugar;
    }
    public void setPricePerCup(double price)
    {
        if(price >= 0)
            this.pricePerCup = Math.round(price*100)/100.0;  //rounds parameter to nearest hundredth.
    }
    public void setNumPitchers(int pitchers)
    {
        numPitchers = pitchers;
    }
    // no setter for Recipe


    public boolean purchaseIngredients(int lemons, int sugar)
    {
        if(lemons < 0 || sugar < 0)
        {
            return false;
        }
        double cost = lemons + sugar * 0.5;
        if(cost <= balance)
        {
            numLemons += lemons;
            numSugar += sugar;
            balance -= cost;
            return true;
        }
        else
        {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    public boolean tweakRecipe(int lemon, int sugar, int water)
    {
        if(lemon <0 || sugar < 0 || water < 0)
        {
            System.out.println("Can't have negative ingredients in the recipe.");
            return false;
        }
        if(lemon == 0 || water == 0)
        {
            System.out.println("The recipe must have at least one lemon and some water.");
            return false;
        }
        recipe.setLemons(lemon);
        recipe.setSugar(sugar);
        recipe.setWater(water);
        return true;  // later the magic will disappear and might return false
    }
    public boolean makePitchers(int pitchers)
    {
        if(pitchers <= 0){return false;}
        if(recipe.isValidRecipe() == false)
        {
            System.out.println("Must have a valid recipe before making pitchers with at least some lemons and some water.");
            return false;
        }
        int lemonsNeeded = recipe.getLemons() * pitchers;
        int sugarNeeded = recipe.getSugar() * pitchers;
        int waterNeeded = recipe.getWater() * pitchers;
        if(lemonsNeeded <= numLemons && sugarNeeded <= numSugar)
        {
            numLemons -= lemonsNeeded;
            numSugar -= sugarNeeded;
            numPitchers += pitchers;
            System.out.println("Fantastic!");
            System.out.println("You used " + lemonsNeeded + " lemons and " + sugarNeeded + " cups of sugar and " + waterNeeded + " cups of water.");
            return true;
        }
        else
        {
            System.out.println("You do not have enough ingredients to make that many pitchers.");
            return false;
        }
    }
    public String getInventory()
    {
        String result = "";
        result += "Lemons: " + numLemons;
        result += "\nSugar (Cups): " + numSugar;
        result += "\nWater (Cups): Infinity";
        result += "\nCurrent Pitchers: " + numPitchers;
        String bal = String.format("%.2f", balance);
        result += "\nCash: $" + bal;
        return result;
    }
    public String getLeftOvers(int numCustomers)
    {
        String result = "";
        int totalNumCupsLeftover = numPitchers * 8 - numCustomers;
        int numPitchersLeftover = totalNumCupsLeftover / 8;
        int numCupsLeftover = totalNumCupsLeftover % 8;
        if (numPitchersLeftover < 0){numPitchers = 0;}
        if(numCupsLeftover < 0){numCupsLeftover = 0;}
        result += numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade.";
        return result;
    }

    public boolean sellLemonade(int customers) {
        // since random num Customers is 1 - 500, we should not get to this branch.  For possible future mods
        if(customers <= 0)
        {
            System.out.println("Sorry, no customers attended today.");
            return false;
        }

        int cupsAvailable = numPitchers * 8;
        if (cupsAvailable == 0) {
            System.out.println("Sorry, you have no lemonade to sell.");
            return false;
        }

        if (customers <= cupsAvailable)  // have leftover lemonade
        {
            double profit = customers * pricePerCup;
            String profitFormatted = String.format("%.2f", profit);
            setBalance(balance + profit);
            System.out.print("\nYou sold " + customers + " cups of lemonade for $" + profitFormatted);
            System.out.print("\nUnfortunately, you did not sell the remaining " + getLeftOvers(customers));
        } else  // have leftover customers and sold out of all lemonade
        {
            double profit = cupsAvailable * pricePerCup;
            String profitFormatted = String.format("%.2f", profit);
            setBalance(balance + profit);
            System.out.print("\nYou sold all " + cupsAvailable + " cups of your lemonade for $" + profitFormatted);
            System.out.print("\nUnfortunately, you did not have enough lemonade for all customers.");
        }
        setNumPitchers(0);
        return true;
    }
}
