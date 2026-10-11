package org.example;
import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }
    private static void printPrepMenu(int d, boolean start, Player p)
    {
        if(start)
        {
            System.out.println("\nDay " + d + ": Preparation Phase");
            System.out.println("Let's prepare for the day!");
        }
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\n" + p.getInventory());
        System.out.println("\n1. Purchase Ingredients");
        System.out.println("2. Tweak the Recipe");
        System.out.println("3. Set the Price");
        System.out.println("4. Make Pitchers of Lemonade");
        System.out.println("5. Move onto the Selling Phase");
        System.out.println("6. Quit");
        System.out.println();


    }


    public static void main(String[] args)
    {

        Scanner keys = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to The Lemonade Stand!");
        Player player = new Player();
        int day = 1;
        boolean isGameOver = false;
        while(!isGameOver)
        {
            boolean isPrepping = true;
            boolean isStartOfDay = true;
            while(isPrepping)
            {
                printPrepMenu(day, isStartOfDay, player);
                System.out.print("What would you like to do?  ");
                int response = keys.nextInt();
                if(response == 1)
                {
                    System.out.print("\nLemons are $1 each\nSugar is $0.50 per cup.");
                    System.out.print("\nHow many lemons do you want to buy? ");
                    int numLemons = keys.nextInt();
                    System.out.print("\nHow many cups of sugar do you want to buy? ");
                    int numSugar = keys.nextInt();
                    boolean success = player.purchaseIngredients(numLemons, numSugar);
                    if (success)
                    {
                        System.out.println("\nGreat! Transaction complete.");
                    }
                    else {
                        System.out.println("\nError. Insufficient funds.");
                    }
                }
                else if(response == 2)
                {
                    System.out.print("\nHow many lemons do you want per pitcher?  ");
                    int numLemons = keys.nextInt();
                    System.out.print("\nHow many cups of sugar do you want per pitcher? ");
                    int numSugar = keys.nextInt();
                    System.out.print("\nHow many cups of water do you want per pitcher? ");
                    int numWater = keys.nextInt();
                    boolean success = player.tweakRecipe(numLemons, numSugar, numWater);
                    if(success)
                    {
                        System.out.println("\nGreat! Your recipe is set!");
                    }
                    else {
                        System.out.println("Error.");
                    }
                }
                else if(response == 3)
                {
                    System.out.print("\nHow much will you sell one cup of lemonade for? ");
                    System.out.print("\nRemember, one pitcher is 8 cups.  ");
                    double price = keys.nextDouble();
                    String priceFormatted = String.format("%.2f", price);
                    player.setPricePerCup(price);
                    System.out.println("\nGreat! Each cup of lemonade is $" + priceFormatted);

                }
                else if(response == 4)
                {
                    System.out.print("\nHow many pitchers of lemonade do you want to make?  ");
                    int numPitchers = keys.nextInt();
                    boolean success = player.makePitchers(numPitchers);
                    // move print statements here and pass in more parameter for lemons needed, etc.
                }
                else if (response == 5)
                {
                    isPrepping = false;
                }
                else if (response == 6)
                {
                    isPrepping = false;
                    isGameOver = true;
                }
                isStartOfDay = false;  // re-set to false for more prep choices
            }
            if(!isGameOver)
            {
                System.out.print("\nDay " + day + ": Selling Phase");
                System.out.print("\n\nThe customers are coming!");
                int numCustomers = (int)(Math.random()*500) + 1;
                System.out.print("\n\n" + numCustomers + " customers visited your stand today!  ");

                player.sellLemonade(numCustomers);
//                double profit = numCustomers * player.getPricePerCup();
//                String profitFormatted = String.format("%.2f", profit);
//                player.setBalance(player.getBalance() + profit);
//                System.out.print("\nYou sold " +  numCustomers + " cups of lemonade for $" + profitFormatted);
//
//                System.out.print("\nUnfortunately, you did not sell the remaining " + player.getLeftOvers(numCustomers));
//                player.setNumPitchers(0);
                if(player.getBalance() <=0)
                {
                    System.out.println("You have no money to purchase ingredients to make and sell lemonade.");
                    System.out.println("Game over.  Your lemonade stand is out of business.");
                    isGameOver = true;
                }
                else
                {
                    System.out.print("\nLet's get a good night sleep and try again tomorrow!");
                    System.out.print("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    day++;
                }
            }
        }
        System.out.println("Thank you for playing!");

    }

}
