import java.util.Arrays;



public class Problem1 {


//BRUTE FORCE
/*
LOGIC 

For each stock we find the max transaction by trying all possible pairs
We then find the maximum of all the maximum transactions

TIME CPMPLEXITY
O(m*n^2)


SPACE COMPLEXITY
O(1)
*/
public Output buySellStocksUsingBrute(int[] prices) {
    Output output = new Output();

    for (int i = 0; i < prices.length -1; i++) {
        for (int j = i + 1; j < prices.length ; j++) {
            int profit = prices[j] - prices[i];
            if (profit > output.getMaximumProfit()) {
                output.setMaximumProfit(profit);
                output.setBuyDayIndex(i);
                output.setSellDayIndex(j);
            }
        }
    }
    return output;
}
public Output buySellStocksUsingBrute(int[][] stockPrices){
    int m = stockPrices.length;
    Output tempOutput;
    Output finalOutput = new Output();

    for (int i=0; i<m; i++) {
        tempOutput = buySellStocksUsingBrute(stockPrices[i]);
        if(finalOutput.getMaximumProfit() < tempOutput.getMaximumProfit())
        {
            finalOutput.setMaximumProfit(tempOutput.getMaximumProfit());
            finalOutput.setStock(i);
            finalOutput.setBuyDayIndex(tempOutput.getBuyDayIndex());
            finalOutput.setSellDayIndex(tempOutput.getSellDayIndex());
        }
    }

    if(finalOutput.getStock() > stockPrices.length
            || finalOutput.getBuyDayIndex() > stockPrices[0].length
            || finalOutput.getSellDayIndex() > stockPrices[0].length
            || finalOutput.getBuyDayIndex() >= finalOutput.getSellDayIndex()
    ){

        finalOutput.setStock(-1);
        finalOutput.setBuyDayIndex(-1);
        finalOutput.setSellDayIndex(-1);
        return finalOutput;
    }
    return finalOutput;

}



//GREEDY
/*
LOGIC 
For each stock we find the max transaction greedily by finding the difference between highest lowest points
We then find the maximum of all the maximum transactions

O(m*n)


SPACE COMPLEXITY
O(1)
*/
public Output buySellStocksUsingGreedy(int[] prices) {
    int minprice = Integer.MAX_VALUE;
    int maxprofit = 0;
    Output output = new Output();

    for (int i = 0; i < prices.length; i++) {
        if (prices[i] < minprice)
        {
            minprice = prices[i];
            output.setBuyDayIndex(i);
        }
        else if (prices[i] - minprice > maxprofit)
        {
            maxprofit = prices[i] - minprice;
            output.setMaximumProfit(maxprofit);
            output.setSellDayIndex(i);
        }
    }
    return output;
}
public Output buySellStocksUsingGreedy(int[][] stockPrices){
    int m = stockPrices.length;
    Output tempOutput;
    Output finalOutput = new Output();

    for (int i=0; i<m; i++) {
        tempOutput = buySellStocksUsingGreedy(stockPrices[i]);
        if(finalOutput.getMaximumProfit() < tempOutput.getMaximumProfit())
        {
            finalOutput.setMaximumProfit(tempOutput.getMaximumProfit());
            finalOutput.setStock(i);
            finalOutput.setBuyDayIndex(tempOutput.getBuyDayIndex());
            finalOutput.setSellDayIndex(tempOutput.getSellDayIndex());
        }
    }

    if(finalOutput.getStock() > stockPrices.length
            || finalOutput.getBuyDayIndex() > stockPrices[0].length
            || finalOutput.getSellDayIndex() > stockPrices[0].length
            || finalOutput.getBuyDayIndex() >= finalOutput.getSellDayIndex()
    ){

        finalOutput.setStock(-1);
        finalOutput.setBuyDayIndex(-1);
        finalOutput.setSellDayIndex(-1);
        return finalOutput;
    }
    return finalOutput;

}



//DP TOP DOWN MEMOIZE
/*
LOGIC 
For each stock we find the max transaction using recursion and memoizing the recursion 
We then find the maximum of all the maximum transactions

TIME COMPLEXITY
O(m*n)

SPACE COMPLEXITY
O(m*n)
*/
public Output buySellStocksUsingDPTopDownMemoize(int[] prices, int index, int[] dp, Output output){
    if (index == prices.length)
        return output;

    if(dp[index] > prices[index])
    {
        dp[index+1] = prices[index];
        output.setBuyDayIndex(index);
    }
    else
    {
        dp[index+1] = dp[index];
    }

    output = buySellStocksUsingDPTopDownMemoize(prices, index + 1, dp, output);

    if(output.getMaximumProfit() < prices[index] - dp[index+1])
    {
        output.setSellDayIndex(index);
        output.setMaximumProfit(prices[index] - dp[index+1]);
    }
    return output;
}
public Output buySellStocksUsingDPTopDownMemoize(int[][] stockPrices){
    int m = stockPrices.length;
    Output finalOutput = new Output();
    Output tempOutput = new Output();

    for(int i=0; i < m;i++ ){
        int[] dp = new int[stockPrices[0].length+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        tempOutput =  buySellStocksUsingDPTopDownMemoize(stockPrices[i], 0,dp, tempOutput);
        if(finalOutput.getMaximumProfit() < tempOutput.getMaximumProfit())
        {
            finalOutput.setSellDayIndex(tempOutput.getSellDayIndex());
            finalOutput.setStock(i);
            finalOutput.setMaximumProfit(tempOutput.getMaximumProfit());
            finalOutput.setBuyDayIndex(tempOutput.getBuyDayIndex());
        }
    }


    if(finalOutput.getStock() > stockPrices.length
            || finalOutput.getBuyDayIndex() > stockPrices[0].length
            || finalOutput.getSellDayIndex() > stockPrices[0].length
            || finalOutput.getBuyDayIndex() >= finalOutput.getSellDayIndex()
    ){

        finalOutput.setStock(-1);
        finalOutput.setBuyDayIndex(-1);
        finalOutput.setSellDayIndex(-1);
        return finalOutput;
    }
    return finalOutput;
}



//DP BOTTOM UP(VARIATION OF KADANES)
/*
LOGIC 
For each stock we find the max transaction using recursion with tabulatiom 
We then find the maximum of all the maximum transactions

TIME COMPLEXITY:
O(m*n)

SPACE COMPLEXITY:
O(m*n)
*/
public Output buySellStocksUsingDPBottomUpTabulation(int[] prices) {
    int n = prices.length;
    Output output = new Output();



    int[] dp = new int[n + 1];

    if(prices.length >0){
        dp[0] = prices[0];
    }
    else{
        dp[0] = -1;
    }


    output.setBuyDayIndex(0);
    for (int j = 0; j < n; j++) {
        if (prices[j] < dp[j]) {
            dp[j + 1] = prices[j];
            output.setBuyDayIndex(j);
        }
        else {
            dp[j + 1] = dp[j];
        }
        if ((prices[j] - dp[j]) > output.getMaximumProfit()) {
            int profit = prices[j] - dp[j];
            output.setMaximumProfit(profit);
            output.setSellDayIndex(j);
        }
    }

    return output;
}
public Output buySellStocksUsingDPBottomUpTabulation(int[][] stockPrices){

    int m = stockPrices.length;
    Output finalOutput = new Output();
    Output tempOutput;

    for(int i=0; i < m;i++ ){
        tempOutput =  buySellStocksUsingDPBottomUpTabulation(stockPrices[i]);

        if(finalOutput.getMaximumProfit() < tempOutput.getMaximumProfit())
        {
            finalOutput.setMaximumProfit(tempOutput.getMaximumProfit());
            finalOutput.setStock(i);
            finalOutput.setBuyDayIndex(tempOutput.getBuyDayIndex());
            finalOutput.setSellDayIndex(tempOutput.getSellDayIndex());
        }
    }


    if(finalOutput.getStock() > stockPrices.length
            || finalOutput.getBuyDayIndex() > stockPrices[0].length
            || finalOutput.getSellDayIndex() > stockPrices[0].length
            || finalOutput.getBuyDayIndex() >= finalOutput.getSellDayIndex()
    ){

        finalOutput.setStock(-1);
        finalOutput.setBuyDayIndex(-1);
        finalOutput.setSellDayIndex(-1);
        return finalOutput;
    }

    return finalOutput;
}







}
