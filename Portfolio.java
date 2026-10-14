public class Portfolio implements Taxable{
    private static final int MAX_TRADES = 10;
    private static final double PREMIUM_DISCOUNT_RATE = 0.5;
    private String traderName;
    private boolean isPremium;
    private Trade[] trades = new Trade[MAX_TRADES];
    private int tradeCount = 0;

    public Portfolio(String traderName){
        this(traderName, false);
    }

    public Portfolio(String traderName, boolean isPremium){
        this.traderName = traderName;
        this.isPremium = isPremium;
    }

    public boolean addTrade(Trade t){
        if(tradeCount == MAX_TRADES){
            System.out.println("Portfolio is full! Maximum 10 trades. ");
            return false;
        }
        trades[tradeCount] = t;
        tradeCount++;
        return true;
    }

    public void addTrade(Trade[] newTrades){
        for(int i=0; i<newTrades.length; i++){
            addTrade(newTrades[i]);
        }
    }

    public double getSubTotal(){
        double sum = 0;
        for(int i = 0; i < tradeCount; i++){
            sum += trades[i].calculateCost();
        }
        return sum;
    }

    public double getTotalCommission(){
        double sum = 0;
        for(int i = 0; i < tradeCount; i++){
            sum += trades[i].calculateCommission();
        }
        return sum; 
    }

    public double getPremiumDiscount(){
        if(isPremium){
            return getTotalCommission() * PREMIUM_DISCOUNT_RATE;
        }
        return 0;
    }

    public double calculateTax(double amount){
        return amount * TAX_RATE;
    }

    public double getTax(){
        return calculateTax(getTotalCommission() - getPremiumDiscount());
    }

    public double getTotal(){
        return getSubTotal() - getPremiumDiscount() + getTax();
    }

    public void printStatement(){
        System.out.println("==============================================");
        System.out.println("          LUNA CAPITAL TRADING DESK           ");
        System.out.println("==============================================");
        System.out.println("Trader: " + traderName);
        System.out.println("Premium: " + ((isPremium) ? "Yes" : "No"));
        System.out.println("----------------------------------------------");
        
        for(int i=0; i<tradeCount; i++){
            System.out.println(i+1 + ". " + trades[i].toString() + " - PHP" + trades[i].calculateCost());
        }

        System.out.println("----------------------------------------------");
        System.out.printf("%-20s PHP%12.2f%n", "Subtotal:", getSubTotal());
        System.out.printf("%-20s PHP%12.2f%n", "Premium discount:", getPremiumDiscount());
        System.out.printf("%-20s PHP%12.2f%n", "Tax:", getTax());
        System.out.printf("%-20s PHP%12.2f%n", "Total DUE:", getTotal());
        System.out.println("==============================================");
        System.out.println("Trades placed so far (all traders): " + tradeCount);
    }
}