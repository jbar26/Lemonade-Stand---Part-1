package org.example;
import java.util.Scanner;

public class App {
    public static Scanner keys = new Scanner(System.in);
    public static final double RENT = 20.00;

public static void main(String[] args)
    {
        System.out.println();
        System.out.println("Welcome to The Lemonade Stand!");
        System.out.println("You will start with $100 and no inventory.");
        System.out.println("Rent is $" + String.format("%.2f", RENT) + " and is due at the start of each day.");
        System.out.println("Do not let your cash dip below $0 or the game will be over.");
        System.out.println("See how many days you can stay in business.  Good luck!");
        Player player = new Player();
        player.tweakRecipe(0, 0, 8);  // default starting recipe
        int day = 1;
        boolean isGameOver = false;
        while(!isGameOver)
        {
            isGameOver = printStartOfDayMenu(day, player);
            boolean isPrepping = !isGameOver;  //if game over true, then do not prep
            while(isPrepping)
            {
                printPrepMenu(player);
                int response = readInt("\nWhat would you like to do?", 1, 6);
                if(response == 1)
                {
                    String pricePerLemon = String.format("%.2f", player.getPricePerLemon());
                    String pricePerSugar = String.format("%.2f", player.getPricePerSugar());
                    System.out.print("\nLemons are $" + pricePerLemon + " each.\nSugar is $" + pricePerSugar + " per cup.");
                    int numLemons = readInt("\nHow many lemons do you want to buy? ", 0, 1000000);
                    int numSugar = readInt("\nHow many cups of sugar do you want to buy? ", 0, 1000000);
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
                    int numLemons = readInt("\nHow many lemons do you want per pitcher?  ", 0, 1000000);
                    int numSugar = readInt("\nHow many cups of sugar do you want per pitcher? ", 0, 1000000);
                    int numWater = readInt("\nHow many cups of water do you want per pitcher? ", 0, 1000000);
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
                    System.out.print("\nOne pitcher is 8 cups.  ");

                    double price = readDouble("\nHow much do you want to sell each cup of lemonade for? ", 0.0, 1000000);
                    String priceFormatted = String.format("%.2f", price);
                    player.setPricePerCup(price);
                    System.out.println("\nGreat! Each cup of lemonade is $" + priceFormatted);

                }
                else if(response == 4)
                {
                    int numPitchers = readInt("\nHow many pitchers of lemonade do you want to make?  ", 0, 1000000);
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
            }
            if(!isGameOver)
            {
                System.out.print("\nDay " + day + ": Selling Phase");
                System.out.print("\n\nThe customers are coming!");
                int numCustomers = (int)(Math.random()*50) + 1;
                //System.out.println("\nnum customers at first: " + numCustomers);
                numCustomers = (int)Math.round(numCustomers * player.getRecipe().getMultiplierForTastiness());
                //System.out.println("num customers at after tastiness: " + numCustomers);
                numCustomers = (int)Math.round(numCustomers * player.getMultiplierForPrice());
                //System.out.println("num customers at price mult: " + numCustomers);
                System.out.print("\n\n" + numCustomers + " customers visited your stand today. ");
                System.out.print("\nCustomer Review for your Recipe: " + player.getRecipe().getRecipeRating());
                System.out.print("\nCustomer Feedback on your Price: " + player.getPriceRating());

                player.sellLemonade(numCustomers);

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
        System.out.println("\nYou made it to day " + day + " before going bankrupt or quitting.");
        System.out.println("\nThank you for playing!");
    }

    private static boolean printStartOfDayMenu(int d, Player p)
    {
        System.out.println("\nDay " + d + ":");
        System.out.print("\nRent is due.  You paid $" + String.format("%.2f", RENT));
        p.payRent(RENT);
        if(p.getBalance() < 0)
        {
            System.out.print("\nYou went bankrupt after paying rent.  Your balance is $" + String.format("%.2f", p.getBalance()));
            return true;  // isGameOver = true.
        }
        else if(d == 1)
        {
            System.out.print("\nMarkets fluctuate daily. ");
            System.out.print("\nToday's price for each lemon is $1.00.");
            System.out.print("\nToday's price for each cup of sugar is $0.50.");
        }
        else
        {
            System.out.print("\n"+p.setPricePerLemon());
            System.out.print("\n"+p.setPriceSugar());
        }
        System.out.println("\nLet's prepare for the day!");
        return false;  // isGameOver = false
    }
    private static void printPrepMenu(Player p)
    {
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\n" + p.getInventory());
        System.out.println("\n1. Purchase Ingredients");
        System.out.println("2. Tweak the Recipe. (" +p.getRecipe().toString() + ")");
        System.out.println("3. Set the Price.  (Current Price: $" + String.format("%.2f", p.getPricePerCup())+")");
        System.out.println("4. Make Pitchers of Lemonade");
        System.out.println("5. Move onto the Selling Phase");
        System.out.println("6. Quit");
        System.out.println();
    }

    private static int readInt(String prompt, int min, int max)
    {
        boolean isValid = false;
        while(!isValid)
        {
            System.out.println(prompt);
            String input = keys.next();
            try
            {
                int result = Integer.parseInt(input);
                if(result >= min && result <= max)
                {
                    // isValid = true;
                    return result;
                }
            }
            catch(NumberFormatException nfe)
            {
                nfe.getMessage();
            }
            System.out.println("Enter an integer between " + min + " and " + max);
        }
        return -99;  // Should never get here.  could make void.
    }
    private static double readDouble(String prompt, double min, double max)  // include place value later?
    {
        boolean isValid = false;
        while(!isValid)
        {
            System.out.println(prompt);
            String input = keys.next();
            try
            {
                double result = Double.parseDouble(input);
                if(result >= min && result <= max)
                {
                    // isValid = true;
                    return result;
                }
            }
            catch(NumberFormatException nfe)
            {
                nfe.getMessage();
            }
            System.out.println("Enter an value between " + min + " and " + max);
        }
        return -99.99;  // Should never get here.  could make void.
    }

}
