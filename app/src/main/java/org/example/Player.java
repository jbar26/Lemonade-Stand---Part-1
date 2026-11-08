package org.example;

public class Player {

    private double balance;
    private int numLemons;
    private int numSugar;
    private int numPitchers;
    private Recipe recipe;
    private double pricePerCup;
    private double pricePerLemon;
    private double pricePerSugar;
    public final double TOTAL_VOL = 8;
    public final double MAX_PRICE_PER_CUP = 5.00;

    // no water variable - infinite

    public Player() {
        balance = 100;
        numLemons = 0;
        numSugar = 0;
        numPitchers = 0;
        recipe = new Recipe();  // starting recipe = 8 cups water
        pricePerCup = 0.00;
        pricePerLemon = 1.00;
        pricePerSugar = 0.5;
    }

    public double getBalance() {
        return balance;
    }

    public int getNumLemons() {
        return numLemons;
    }

    public int getNumSugar() {
        return numSugar;
    }

    public double getPricePerCup() {
        return pricePerCup;
    }

    public double getPricePerLemon() {
        return pricePerLemon;
    }

    public double getPricePerSugar() {
        return pricePerSugar;
    }

    public Recipe getRecipe()  // returns reference to this object so can make changes
    {
        return recipe;
    }

    public int getNumPitchers() {
        return numPitchers;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setNumLemons(int lemons) {
        this.numLemons = lemons;
    }

    public void setNumSugar(int sugar) {
        this.numSugar = sugar;
    }

    public void setPricePerCup(double price) {
        if (price >= 0)
            this.pricePerCup = Math.round(price * 100) / 100.0;  //rounds parameter to nearest hundredth.
    }

    public void setNumPitchers(int pitchers) {
        numPitchers = pitchers;
    }

    public String setPricePerLemon() {
        double change = (Math.random() * 10 - 5) / 100.0;
        double newPrice = pricePerLemon * change + pricePerLemon;
        while (newPrice < 0) {
            change = (Math.random() * 10 - 5) / 100.0;
            newPrice = pricePerLemon * change + pricePerLemon;
        }
        String newPriceStr = String.format("%.2f", newPrice);
        String priceStr = String.format("%.2f", pricePerLemon);
        String direction = "";
        if (change < 0) {
            direction = "down";
        } else if (change > 0) {
            direction = "up";
        } else {
            direction = "no change";
        }
        String result = "Today's price per lemon is $" + newPriceStr + ". This is " + direction + " from yesterday's price of $" + priceStr;
        pricePerLemon = newPrice;
        return result;
    }

    public String setPriceSugar() {
        double change = (Math.random() * 10 - 5) / 100.0;
        double newPrice = pricePerSugar * change + pricePerSugar;
        while (newPrice < 0) {
            change = (Math.random() * 10 - 5) / 100.0;
            newPrice = pricePerSugar * change + pricePerSugar;
        }
        String newPriceStr = String.format("%.2f", newPrice);
        String priceStr = String.format("%.2f", pricePerSugar);
        String direction = "";
        if (change < 0) {
            direction = "down";
        } else if (change > 0) {
            direction = "up";
        } else {
            direction = "no change";
        }
        String result = "Today's price per cup of sugar is $" + newPriceStr + ". This is " + direction + " from yesterday's price of $" + priceStr;
        pricePerSugar = newPrice;
        return result;
    }
    // no setter for Recipe


    public boolean purchaseIngredients(int lemons, int sugar) {
        if (lemons < 0 || sugar < 0) {
            return false;
        }
        double cost = pricePerLemon*lemons + pricePerSugar * sugar;
        if (cost <= balance) {
            numLemons += lemons;
            numSugar += sugar;
            balance -= cost;
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    public boolean tweakRecipe(int lemon, int sugar, int water) {
        if (lemon < 0 || sugar < 0 || water < 0)  // any negative inputs
        {
            System.out.print("\nCan not have negative values.");
            return false;
        }
        double volLemon = lemon * 0.25;
        double volSugar = sugar * 0.5;
        double currentVol = volLemon + volSugar + water;
        if (currentVol > TOTAL_VOL) {
            System.out.println("Error. Your pitcher would overflow with these amounts. The recipe remains unchanged.");
            return false;
        } else // if currentVol <= TOTAL_VOL
        {
            double waterNeeded = water + (TOTAL_VOL - currentVol);
            recipe = new Recipe(lemon, sugar, waterNeeded);
            return true;
        }
    }

    public boolean makePitchers(int pitchers) {
        if (pitchers <= 0) {
            return false;
        }
//        if(recipe.isValidRecipe() == false)
//        {
//            System.out.println("Must have a valid recipe before making pitchers with at least some lemons and some water.");
//            return false;
//        }
        int lemonsNeeded = recipe.getLemons() * pitchers;
        int sugarNeeded = recipe.getSugar() * pitchers;
        double waterNeeded = recipe.getWater() * pitchers;
        if (lemonsNeeded <= numLemons && sugarNeeded <= numSugar) {
            numLemons -= lemonsNeeded;
            numSugar -= sugarNeeded;
            numPitchers += pitchers;
            System.out.println("Fantastic!");
            System.out.println("You used " + lemonsNeeded + " lemons and " + sugarNeeded + " cups of sugar and " + waterNeeded + " cups of water.");
            return true;
        } else {
            System.out.println("You do not have enough ingredients to make that many pitchers.");
            return false;
        }
    }

    public String getInventory() {
        String result = "";
        result += "Lemons: " + numLemons;
        result += "\nSugar (Cups): " + numSugar;
        result += "\nWater (Cups): Infinity";
        result += "\nCurrent Pitchers: " + numPitchers;
        String bal = String.format("%.2f", balance);
        result += "\nCash: $" + bal;
        return result;
    }

    public String getLeftOvers(int numCustomers) {
        String result = "";
        int totalNumCupsLeftover = numPitchers * 8 - numCustomers;
        int numPitchersLeftover = totalNumCupsLeftover / 8;
        int numCupsLeftover = totalNumCupsLeftover % 8;
        if (numPitchersLeftover < 0) {
            numPitchers = 0;
        }
        if (numCupsLeftover < 0) {
            numCupsLeftover = 0;
        }
        result += numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade.";
        return result;
    }

    public boolean sellLemonade(int customers) {
        // since random num Customers is 1 - 500, we should not get to this branch.  For possible future mods
        if (customers <= 0) {
            System.out.println("Sorry, no customers attended today.");
            setNumPitchers(0);
            return false;
        }

        int cupsAvailable = numPitchers * 8;
        if (cupsAvailable == 0) {
            System.out.println("Sorry, you have no lemonade to sell.");
            setNumPitchers(0);
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

    public boolean payRent(double value) {
        if (value < 0) {
            return false;
        }
        balance -= value;
        if (balance < 0) {
            return false;
        } else {
            return true;
        }
    }

    public double getMultiplierForPrice() {
        double multiplier = (1 - (pricePerCup / MAX_PRICE_PER_CUP));
        if (multiplier <= 0) {
            return 0;
        } else {
            return multiplier;
        }
    }

    public String getPriceRating() {
        double multiplier = getMultiplierForPrice();
        String result = "Customer Rating on the Lemonade Price: ";
        if (multiplier == 0) {
            result += "Too expensive! Don't want to buy any at that price.";
        } else if (multiplier <= 0.2) {
            result += "A little too high, but a few will still buy it at that price.";
        } else if (multiplier <= 0.4) {
            result += "A little high, but not a bad price.";
        } else if (multiplier <= 0.6) {
            result += "This is a fair price!";
        } else if (multiplier <= 0.8) {
            result += "As a customer, I think it's a great price. But can the Lemonade Stand make enough profit to stay in business?";
        } else if (multiplier < 1) {
            result += "Almost everyone can afford lemonade at this price! But can the Lemonade Stand make enough profit to stay in business?";
        } else {
            result += "Free Lemonade? Can't argue with that price!";
        }
        return result;
    }
}

