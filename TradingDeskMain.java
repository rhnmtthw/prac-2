import java.util.*;

public class TradingDeskMain {
    static Scanner console = new Scanner(System.in);
    public static void main(String[] args){

        System.out.print("Trader name: ");
        String traderName = console.nextLine();
        System.out.print("Premium account? (y/n): ");
        String answer = console.nextLine().trim();
        boolean isPremium = answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes");

        Portfolio portfolio = new Portfolio(traderName, isPremium);

        int choice = 0;
        do{
            System.out.println("=== MENU ===");
            System.out.println("1. Place stock trade");
            System.out.println("2. Place forex trade");
            System.out.println("3. Checkout");
            System.out.print("Choose: ");
            choice = Integer.parseInt(console.nextLine());

            Trade t;
            boolean check;
            switch (choice){
            case 1: 
                System.out.print("Symbol: ");
                String symbol = console.nextLine();
                System.out.print("Shares (1 to 10000): ");
                int shares = Integer.parseInt(console.nextLine());
                System.out.print("Price per share: ");
                double price = Double.parseDouble(console.nextLine());
                t = new StockTrade(symbol, shares, price);
                check = portfolio.addTrade(t);
                System.out.println(check ? String.format("Trade added! Cost: PHP%.2f", t.calculateCost()) : "Trade not added");
                break;
            case 2: 
                System.out.print("Pair (e.g. USD/PHP): ");
                String pair = console.nextLine();
                System.out.print("Lots (1 to 10000): ");
                int lots  = Integer.parseInt(console.nextLine());
                System.out.print("Rate: ");
                double rate = Double.parseDouble(console.nextLine());
                t = new ForexTrade(pair, lots, rate);
                check = portfolio.addTrade(t);
                System.out.println(check ? String.format("Trade added!", t.calculateCost()) : "Trade not added,");
                break;
            case 3:
                portfolio.printStatement();
                break;
            default:
                System.out.println("Please choose 1,2, or 3");
                break;
            }
        } while(choice != 3);

    }
}