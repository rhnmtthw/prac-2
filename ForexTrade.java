public class ForexTrade extends Trade{
    private static final int LOT_SIZE = 100;
    private static final double SPREAD_FEE_PER_LOT = 5.00;

    public ForexTrade(String pair, int lots, double rate){
        super(pair, lots, rate);
    }

    @Override 
    public double calculateValue(){
        return getQuantity() * LOT_SIZE * getPrice();
    }

    @Override 
    public double calculateCommission(){
        return getQuantity() * SPREAD_FEE_PER_LOT;
    }

    @Override 
    public String getAssetType(){
        return "FOREX";
    }
}