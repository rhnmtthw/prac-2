public abstract class Trade {
    private static final int MAX_QUANTITY = 10000;
    private static int totalTradesPlaced = 0;
    private String symbol;
    private int quantity;
    private double price; 

    public Trade(String symbol, double price){
        this(symbol, 1, price);
    }

    public Trade(String symbol, int quantity, double price){
        this.symbol = symbol;
        setQuantity(quantity);
        this.price = price; 
        totalTradesPlaced++; 
    }

    public String getSymbol(){
        return symbol;
    }

    public int getQuantity(){
        return quantity;
    }

    protected double getPrice(){
        return price; 
    }

    public void setQuantity(int quantity){
        if (quantity > 1 && quantity <10000){
            this.quantity = quantity;
        } else {
            System.out.println("Invalid quantity! Using 1.");
            this.quantity = 1; 
        }
    }

    public static int getTotalTradesPlace(){
        return totalTradesPlaced;
    }

    public abstract String getAssetType();
    public abstract double calculateValue();
    public abstract double calculateCommission();

    public double calculateCost(){
         return calculateValue() + calculateCommission();
    }

    @Override 
    public String toString(){
        return getAssetType() + " " + symbol + " x " + quantity + " @ " + String.format("%.2f", price);
    }
}