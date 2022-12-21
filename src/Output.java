public class Output {
    private int maximumProfit = -1;

    private int stock = -1;
    private int buyDayIndex = -1;
    private int sellDayIndex = -1;

    public Output() {
    }

    public Output(int stock,int buyDayIndex, int sellDayIndex) {
        this.stock = stock;
        this.buyDayIndex = buyDayIndex;
        this.sellDayIndex = sellDayIndex;
    }


    public Output(int stockPrice,int stock,int buyDayIndex, int sellDayIndex) {
        this.maximumProfit = stockPrice;
        this.stock = stock;
        this.buyDayIndex = buyDayIndex;
        this.sellDayIndex = sellDayIndex;
    }

    public int getMaximumProfit() {
        return maximumProfit;
    }

    public void setMaximumProfit(int maximumProfit) {
        this.maximumProfit = maximumProfit;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {this.stock = stock;}

    public int getBuyDayIndex() {
        return buyDayIndex;
    }

    public void setBuyDayIndex(int buyDayIndex) {
        this.buyDayIndex = buyDayIndex;
    }

    public int getSellDayIndex() {
        return sellDayIndex;
    }

    public void setSellDayIndex(int sellDayIndex) {
        this.sellDayIndex = sellDayIndex;
    }
}
