package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerTester
{
    Player player = new Player();

    @Test
    public void testPurchaseIngredientsNeg1lemons0sugar()
    {
        assertEquals(100, player.getBalance());
        assertEquals(false, player.purchaseIngredients(-1, 0));
        assertEquals(100, player.getBalance());  // still 100
    }
    @Test
    public void testPurchaseIngredients0lemonsNeg1sugar()
    {
        assertEquals(false, player.purchaseIngredients(0, -1));
        assertEquals(100, player.getBalance());  // still 100
    }
    @Test
    public void testPurchaseIngredients0lemons0sugar()
    {
        assertEquals(true, player.purchaseIngredients(0, 0));
        assertEquals(0, player.getNumLemons());
        assertEquals(0, player.getNumSugar());
        assertEquals(100, player.getBalance());  // still 100
    }
    @Test
    public void testPurchaseIngredients1lemons0sugar()
    {
        assertEquals(true, player.purchaseIngredients(1, 0));
        assertEquals(1, player.getNumLemons());
        assertEquals(0, player.getNumSugar());
        assertEquals(99, player.getBalance());
    }
    @Test
    public void testPurchaseIngredients0lemons1sugar()
    {
        assertEquals(true, player.purchaseIngredients(0, 1));
        assertEquals(0, player.getNumLemons());
        assertEquals(1, player.getNumSugar());
        assertEquals(99.5, player.getBalance(), 0.01);
    }
    @Test
    public void testPurchaseIngredients1lemons1sugar()
    {
        assertEquals(true, player.purchaseIngredients(1, 1));
        assertEquals(1, player.getNumLemons());
        assertEquals(1, player.getNumSugar());
        assertEquals(98.5, player.getBalance(), 0.01);
    }
    @Test
    public void testPurchaseIngredients2lemons2sugar()
    {
        assertEquals(true, player.purchaseIngredients(2, 2));
        assertEquals(2, player.getNumLemons());
        assertEquals(2, player.getNumSugar());
        assertEquals(97, player.getBalance(), 0.01);
    }
    @Test
    public void testPurchaseIngredients19lemons25sugar()
    {
        assertEquals(true, player.purchaseIngredients(19, 25));
        assertEquals(19, player.getNumLemons());
        assertEquals(25, player.getNumSugar());
        assertEquals(68.5, player.getBalance(), 0.01);
    }
    @Test
    public void testPurchaseIngredients100lemons0sugar()
    {
        assertEquals(true, player.purchaseIngredients(100, 0));
        assertEquals(100, player.getNumLemons());
        assertEquals(0, player.getNumSugar());
        assertEquals(0, player.getBalance(), 0.01);
    }
    @Test
    public void testPurchaseIngredients101lemons0sugar()
    {
        assertEquals(false, player.purchaseIngredients(101, 0));
        assertEquals(100, player.getBalance());
    }
    @Test
    public void testPurchaseIngredients0lemons200sugar()
    {
        assertEquals(true, player.purchaseIngredients(0, 200));
        assertEquals(0, player.getNumLemons());
        assertEquals(200, player.getNumSugar());
        assertEquals(0, player.getBalance(), 0.01);
    }
    @Test
    public void testPurchaseIngredients0lemons201sugar()
    {
        assertEquals(false, player.purchaseIngredients(0, 201));
        assertEquals(100, player.getBalance());
    }



    // TEST TweakRecipe
    @Test
    public void testTweakRecipeNegativeLemons0sugar0water()
    {
        assertEquals(0, player.getRecipe().getLemons());
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
        assertEquals(false, player.tweakRecipe(-1, 0, 0));
        assertEquals(0, player.getRecipe().getLemons());  // recipe remains unchanged
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
    }
    @Test
    public void testTweakRecipe0LemonsNegativeSugar0water()
    {
        assertEquals(0, player.getRecipe().getLemons());
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
        assertEquals(false, player.tweakRecipe(0, -1, 0));
        assertEquals(0, player.getRecipe().getLemons());
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
    }
    @Test
    public void testTweakRecipe0Lemons0sugarNegativeWater()
    {

        assertEquals(0, player.getRecipe().getLemons());
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
        assertEquals(false, player.tweakRecipe(0, 0, -1));
        assertEquals(0, player.getRecipe().getLemons());
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
    }
    @Test
    public void testTweakRecipe0lemons0sugar0water()
    {
        assertEquals(true, player.tweakRecipe(0, 0, 0));
        assertEquals(0, player.getRecipe().getLemons());
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
    }
    @Test
    public void testTweakRecipe1lemons0sugar0water()
    {
        assertEquals(true, player.tweakRecipe(1, 0, 0));
        assertEquals(1, player.getRecipe().getLemons());
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(7.75, player.getRecipe().getWater());
    }
    @Test
    public void testTweakRecipe0lemons1sugar0water()
    {
        assertEquals(true, player.tweakRecipe(0, 1, 0));
        assertEquals(0, player.getRecipe().getLemons());
        assertEquals(1, player.getRecipe().getSugar());
        assertEquals(7.5, player.getRecipe().getWater());
    }
    @Test
    public void testTweakRecipe0lemons0sugar1water()
    {
        assertEquals(true, player.tweakRecipe(0, 0, 1));
        assertEquals(0, player.getRecipe().getLemons());
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
    }

    @Test
    public void testTweakRecipe1lemons1sugar1water()
    {
        assertEquals(true, player.tweakRecipe(1, 1, 1));
        assertEquals(1, player.getRecipe().getLemons());
        assertEquals(1, player.getRecipe().getSugar());
        assertEquals(7.25, player.getRecipe().getWater());
    }
    @Test
    public void testTweakRecipe5lemons10sugar12waterOverflow()
    {
        assertEquals(0, player.getRecipe().getLemons());
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
        assertEquals(false, player.tweakRecipe(5, 10, 12));
        assertEquals(0, player.getRecipe().getLemons());  // overflow. recipe remains same
        assertEquals(0, player.getRecipe().getSugar());
        assertEquals(8, player.getRecipe().getWater());
    }

    // TEST setPrice
    @Test
    public void testSetPriceNegative()
    {
        assertEquals(0, player.getPricePerCup());
        player.setPricePerCup(-1);
        assertEquals(0,player.getPricePerCup() );
    }
    @Test
    public void testSetPrice1()
    {
        assertEquals(0, player.getPricePerCup());
        player.setPricePerCup(1);
        assertEquals(1,player.getPricePerCup() );
    }
    @Test
    public void testSetPrice1Point50()
    {
        assertEquals(0, player.getPricePerCup());
        player.setPricePerCup(1.50);
        assertEquals(1.5, player.getPricePerCup());
    }
    @Test
    public void testSetPrice99Cents()
    {
        assertEquals(0, player.getPricePerCup());
        player.setPricePerCup(.99);
        assertEquals(.99,player.getPricePerCup() );
    }
    @Test
    public void testSetPrice4Point23()
    {
        assertEquals(0, player.getPricePerCup());
        player.setPricePerCup(4.23);
        assertEquals(4.23,player.getPricePerCup() );
    }


    // TEST makePitchers
    @Test
    public void testMakePitchersNegative1()
    {
        assertEquals(false,player.makePitchers(-1) );
        assertEquals(0, player.getNumPitchers()); //still 0
    }
    @Test
    public void testMakePitchers0()
    {
        assertEquals(false,player.makePitchers(0));  // returns false
        assertEquals(0, player.getNumPitchers()); //still 0
    }
    @Test
    public void testMakePitchers1DefaultRecipe()
    {
        assertEquals(true,player.makePitchers(1));
        assertEquals(1, player.getNumPitchers());

    }
    @Test
    public void testMakePitchers1()
    {
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        assertEquals(true,player.makePitchers(1));
        assertEquals(1, player.getNumPitchers());
        assertEquals(8, player.getNumLemons()); // inventory went down after making pitcher
        assertEquals(8, player.getNumSugar());

    }
    @Test
    public void testMakePitchers4()
    {
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        assertEquals(true,player.makePitchers(4));  // returns false - no money
        assertEquals(4, player.getNumPitchers());
        assertEquals(2, player.getNumLemons()); // inventory went down after making pitchers
        assertEquals(2, player.getNumSugar());
    }
    @Test
    public void testMakePitchers5()
    {
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        assertEquals(true,player.makePitchers(5));
        assertEquals(5, player.getNumPitchers());
        assertEquals(0, player.getNumLemons()); // inventory went down after making pitchers
        assertEquals(0, player.getNumSugar());
    }
    @Test
    public void testMakePitchers6NotEnoughIngredients()
    {
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        assertEquals(false,player.makePitchers(6));
        assertEquals(0, player.getNumPitchers());
        assertEquals(10, player.getNumLemons()); // inventory not changed
        assertEquals(10, player.getNumSugar());
    }
    @Test
    public void testMakePitchers5NotEnoughLemons()
    {
        player.purchaseIngredients(8, 10);
        player.tweakRecipe(2, 2, 2);
        assertEquals(false,player.makePitchers(5));
        assertEquals(0, player.getNumPitchers());
        assertEquals(8, player.getNumLemons()); // inventory not changed
        assertEquals(10, player.getNumSugar());
    }
    @Test
    public void testMakePitchers5NotEnoughSugar()
    {
        player.purchaseIngredients(10, 8);
        player.tweakRecipe(2, 2, 2);
        assertEquals(false,player.makePitchers(5));
        assertEquals(0, player.getNumPitchers());
        assertEquals(10, player.getNumLemons()); // inventory not changed
        assertEquals(8, player.getNumSugar());
    }

    // TEST getInventory
    @Test
    public void testGetInventory0lemons0sugar()
    {
        String expected = "Lemons: 0\nSugar (Cups): 0\nWater (Cups): Infinity\nCurrent Pitchers: 0\nCash: $100.00";
        assertEquals(expected, player.getInventory());
    }
    @Test
    public void testGetInventory1lemons1sugar()
    {
        player.purchaseIngredients(1, 1);
        String expected = "Lemons: 1\nSugar (Cups): 1\nWater (Cups): Infinity\nCurrent Pitchers: 0\nCash: $98.50";
        assertEquals(expected, player.getInventory());
    }
    @Test
    public void testGetInventory10lemons9sugar()
    {
        player.purchaseIngredients(10, 9);
        String expected = "Lemons: 10\nSugar (Cups): 9\nWater (Cups): Infinity\nCurrent Pitchers: 0\nCash: $85.50";
        assertEquals(expected, player.getInventory());
    }

    // TEST sell lemonade
    @Test
    public void testSellLemonade0customers()
    {
        assertEquals(false, player.sellLemonade(0));
    }
    @Test
    public void testSellLemonade1customers()
    {
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        assertEquals(true, player.sellLemonade(1));
    }
    @Test
    public void testSellLemonade40customers()
    {
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        assertEquals(true, player.sellLemonade(40));
    }
    @Test
    public void testSellLemonade45customers()
    {
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        assertEquals(true, player.sellLemonade(45));
    }


    // TEST getLeftOvers
    @Test
    public void testGetLeftovers0Customers()
    {
        //numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade."
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        String expected = "5 pitchers and 0 cups of lemonade.";
        assertEquals(expected, player.getLeftOvers(0));
    }
    @Test
    public void testGetLeftovers1Customers()
    {
        //numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade."
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        String expected = "4 pitchers and 7 cups of lemonade.";
        assertEquals(expected, player.getLeftOvers(1));
    }
    @Test
    public void testGetLeftovers2Customers()
    {
        //numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade."
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        String expected = "4 pitchers and 6 cups of lemonade.";
        assertEquals(expected, player.getLeftOvers(2));
    }
    @Test
    public void testGetLeftovers7Customers()
    {
        //numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade."
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        String expected = "4 pitchers and 1 cups of lemonade.";
        assertEquals(expected, player.getLeftOvers(7));
    }
    @Test
    public void testGetLeftovers8Customers()
    {
        //numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade."
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        String expected = "4 pitchers and 0 cups of lemonade.";
        assertEquals(expected, player.getLeftOvers(8));
    }
    @Test
    public void testGetLeftovers9Customers()
    {
        //numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade."
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        String expected = "3 pitchers and 7 cups of lemonade.";
        assertEquals(expected, player.getLeftOvers(9));
    }
    @Test
    public void testGetLeftovers39Customers()
    {
        //numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade."
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        String expected = "0 pitchers and 1 cups of lemonade.";
        assertEquals(expected, player.getLeftOvers(39));
    }
    @Test
    public void testGetLeftovers40Customers()
    {
        //numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade."
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        String expected = "0 pitchers and 0 cups of lemonade.";
        assertEquals(expected, player.getLeftOvers(40));
    }
    @Test
    public void testGetLeftovers41Customers()
    {
        //numPitchersLeftover + " pitchers and " + numCupsLeftover + " cups of lemonade."
        player.purchaseIngredients(10, 10);
        player.tweakRecipe(2, 2, 2);
        player.makePitchers(5);
        String expected = "0 pitchers and 0 cups of lemonade.";
        assertEquals(expected, player.getLeftOvers(41));
    }


    @Test
    public void payRentNegativeValue()
    {
        assertEquals(false, player.payRent(-50));
    }
    @Test
    public void payRent0Value()
    {
        assertEquals(100, player.getBalance());
        assertEquals(true, player.payRent(0));
        assertEquals(100, player.getBalance());
    }
    @Test
    public void payRent1DollarValue()
    {
        assertEquals(100, player.getBalance());
        assertEquals(true, player.payRent(1));
        assertEquals(99, player.getBalance());
    }
    @Test
    public void payRent20DollarValue()
    {
        assertEquals(100, player.getBalance());
        assertEquals(true, player.payRent(20));
        assertEquals(80, player.getBalance());
    }
    @Test
    public void payRent100DollarValue()
    {
        assertEquals(100, player.getBalance());
        assertEquals(true, player.payRent(100));
        assertEquals(0, player.getBalance());
    }
    @Test
    public void payRent101DollarValue()
    {
        assertEquals(100, player.getBalance());
        assertEquals(false, player.payRent(101));
        assertEquals(-1, player.getBalance());
    }


    // Test Fluctuating Prices
    @Test
    public void testSetPriceLemonNeverAboveOrBelow5PercentChange()
    {
        assertEquals(1, player.getPricePerLemon(), 0.01);
        System.out.println(player.getPricePerLemon());
        boolean goesAbove5 = false;
        for(int i = 0; i < 5000; i++)
        {
            double oldPrice = player.getPricePerLemon();
            player.setPricePerLemon();
            double newPrice = player.getPricePerLemon();
            double change = (newPrice - oldPrice)/oldPrice;
            if(Math.abs(change) > .05)
            {
                goesAbove5 = true;
            }
        }
        assertEquals(false, goesAbove5);
    }

    @Test
    public void testGetMultiplierForPrice0Free()
    {
        assertEquals(0.0, player.getPricePerCup());
        assertEquals(1, player.getMultiplierForPrice());
    }
    @Test
    public void testGetMultiplierForPrice50Cents()
    {
        player.setPricePerCup((0.5));
        assertEquals(0.5, player.getPricePerCup());
        assertEquals(0.9, player.getMultiplierForPrice());
    }
    @Test
    public void testGetMultiplierForPrice1Dollar()
    {
        player.setPricePerCup((1));
        assertEquals(1, player.getPricePerCup());
        assertEquals(0.8, player.getMultiplierForPrice());
    }
    @Test
    public void testGetMultiplierForPrice2Dollars()
    {
        player.setPricePerCup((2));
        assertEquals(2, player.getPricePerCup());
        assertEquals(0.6, player.getMultiplierForPrice());
    }
    @Test
    public void testGetMultiplierForPrice3Dollars()
    {
        player.setPricePerCup((3));
        assertEquals(3, player.getPricePerCup());
        assertEquals(0.4, player.getMultiplierForPrice());
    }
    @Test
    public void testGetMultiplierForPrice5Dollars()
    {
        player.setPricePerCup((5));
        assertEquals(5, player.getPricePerCup());
        assertEquals(0, player.getMultiplierForPrice());
    }
    @Test
    public void testGetMultiplierForPrice6Dollars()
    {
        player.setPricePerCup((6));
        assertEquals(6, player.getPricePerCup());
        assertEquals(0, player.getMultiplierForPrice());
    }
    @Test
    public void testGetMultiplierForPrice1Dollar25Cents()
    {
        player.setPricePerCup((1.25));
        assertEquals(1.25, player.getPricePerCup());
        assertEquals(0.75, player.getMultiplierForPrice());
    }
    @Test
    public void testGetMultiplierForPrice5Dollar1Cent()
    {
        player.setPricePerCup((5.01));
        assertEquals(5.01, player.getPricePerCup());
        assertEquals(0, player.getMultiplierForPrice());
    }
    @Test
    public void testGetMultiplierForPrice4Dollar99Cents()
    {
        player.setPricePerCup((4.99));
        assertEquals(4.99, player.getPricePerCup());
        assertEquals(0.002, player.getMultiplierForPrice(), 0.0001);
    }
    @Test
    public void testGetMultiplierForPrice0Dollar1Cent()
    {
        player.setPricePerCup((0.01));
        assertEquals(0.01, player.getPricePerCup());
        assertEquals(.998, player.getMultiplierForPrice(), 0.0001);
    }



}
