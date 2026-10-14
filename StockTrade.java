public class StockTrade extends Trade {
    private static final double COMMISSION_RATE = 0.0025;
    private static final double MIN_COMMISSION = 50.00;
    
    public StockTrade(String symbol, int shares, double pricePerShare){
        super(symbol, shares, pricePerShare);
    }

    @Override 
    public double calculateValue(){
        return getQuantity() * getPrice();
    }

    @Override 
    public double calculateCommission(){
        double com = calculateValue() * COMMISSION_RATE;
        if(com < MIN_COMMISSION) {
            return MIN_COMMISSION;
        }
        return com;
    }

    @Override 
    public String getAssetType(){
        return "Stock";
    }
    
}