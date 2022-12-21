import java.util.*;

class Info{
    int profitTillNow = 0;
    List<int[]> trackPath = new ArrayList<>();

    public Info(int profitTillNow, List<int[]> trackPath) {
        this.profitTillNow = profitTillNow;
        this.trackPath = trackPath;
    }

    public int getProfitTillNow() {
        return profitTillNow;
    }

    public List<int[]> getTrackPath() {
        return trackPath;
    }

    public void setProfitTillNow(int profitTillNow) {
        this.profitTillNow = profitTillNow;
    }
}

public class Problem2 {

    public static Map<String, Info> map = new HashMap<>();
    public static Map<String, String> map1 = new HashMap<>();
    private static void setPreviousInformation(Info currentInformation, Info previousInformation)
    {
        currentInformation.setProfitTillNow(previousInformation.getProfitTillNow());
        currentInformation.getTrackPath().clear();
        currentInformation.getTrackPath().addAll(previousInformation.getTrackPath());
    }
    private static boolean findEquivalentStocks(int [][]prices, int profit, int buyIndex, int sellIndex, int m, boolean found, List<Output> outputList) {

        for(int stock=0; stock<m; stock++) {
            if((prices[stock][sellIndex] - prices[stock][buyIndex]) == profit)
            {
                outputList.add(new Output(stock ,buyIndex , sellIndex));
                found = true;
                break;
            }
        }
        return found;
    }
   

    //BRUTE FORCE RECURSIVE
    /* 
    LOGIC 
    On each day we check whether we have a stock or do not have a stock.
    a.If we do not have a stock with us we are present in the buy mode. 
    So we have the choice to either buy the stock or skip buying the stock. 
    If we choose to buy the stock we have the choice of buying stocks 1 to m. 
    If we choose to skip buying we move to the next day. 

    b.If we have stock with us we are in sell mode. Now you can choose to either sell the stock or skip selling the stock. 
    If we sell the stock we calculate the profit from the previous buy. If we skip selling we move a day ahead.


    TIME COMPLEXITY 
    (m*n^2k)

    SPACE COMPLEXITY
    Space Complexity :O(1)
    Recursive Stack Space Complexity :O(m*2k)

     */
    public static Info buySellKTimesRecursive(int[][] stockPrices, int day,int k,boolean haveStock,int stockOptions,int currentProfit){
        if(k == 0 || day== stockPrices[0].length){
            if(day== stockPrices[0].length && haveStock)
            {
                return new Info(0, new ArrayList<>());
            }
            else
            {
                return new Info(currentProfit, new ArrayList<>());
            }
            }

        Info information = new Info(Integer.MIN_VALUE, new ArrayList<>());

        if(!haveStock){
            //buy or skip buy
            for(int i=0 ; i <= stockPrices.length;i++){
                if(i == stockPrices.length){
                    //skip buy
                    Info tempInfo = buySellKTimesRecursive(stockPrices,day+1,k,haveStock,i,currentProfit);
                    if( tempInfo.getProfitTillNow() > information.getProfitTillNow())
                    {
                        setPreviousInformation(information, tempInfo);
                    }
                }
                else{
                    //buy any 1 stock for the day
                    Info tempInfo = buySellKTimesRecursive(stockPrices,day +1,k,true,i,currentProfit - stockPrices[i][day]);

                    if( tempInfo.getProfitTillNow() > information.getProfitTillNow())
                    {
                        setPreviousInformation(information, tempInfo);
                        information.getTrackPath().add(new int[]{i, day});
                    }
                }
            
            }
        }
        else{
            // skip sell
            Info tempInfo = buySellKTimesRecursive(stockPrices,day+1,k,haveStock,stockOptions,currentProfit);
            if( tempInfo.getProfitTillNow() > information.getProfitTillNow())
            {
                setPreviousInformation(information, tempInfo);
            }

            //sell
            tempInfo = buySellKTimesRecursive(stockPrices,day,k-1, !haveStock,stockOptions,currentProfit+stockPrices[stockOptions][day]);

            if( tempInfo.getProfitTillNow() > information.getProfitTillNow())
            {
                setPreviousInformation(information, tempInfo);
                information.getTrackPath().add(new int[]{stockOptions, day});
            }

        }

        return information;
    }
    public static List<Output> buySellKTimesRecursiveAfterProcessing(int[][] stockPrices, int k) {
        Info finalInformation = new Info(0, new ArrayList<>());

        for(int i = 0; i <= stockPrices.length; i++){
            Info tempInfo = buySellKTimesRecursive(stockPrices,0,k,false, i,0);
            if(tempInfo.getProfitTillNow() > finalInformation.getProfitTillNow()){
                setPreviousInformation(finalInformation, tempInfo);
            }
        }

        List<int[]> trackRecords = finalInformation.getTrackPath();
        Collections.reverse(trackRecords);

        List<Output> outputList = new ArrayList<>();
        Output output = null;

        if(trackRecords.size() %2 != 0){
            trackRecords.remove(trackRecords.size()-1);
        }


        for(int i=0; i < trackRecords.size(); i=i+2)
        {
            int stock = trackRecords.get(i)[0];
            int buyIndex = trackRecords.get(i)[1];
            int sellIndex = trackRecords.get(i+1)[1] ;
            output = new Output(stock, buyIndex, sellIndex);
            outputList.add(output);
        }
        if(outputList.size() == 0){
            Output emptyOp = new Output(-1,-1,-1);
            outputList.add(emptyOp);
        }


        return outputList;

    }


    //DP
    /*
    LOGIC 
    On each day we check whether we have a stock or do not have a stock.
    a.If we do not have a stock with us we are present in the buy mode. 
    So we have the choice to either buy the stock or skip buying the stock. 
    If we choose to buy the stock we have the choice of buying stocks 1 to m. 
    If we choose to skip buying we move to the next day. 

    b.If we have stock with us we are in sell mode. Now you can choose to either sell the stock or skip selling the stock. 
    If we sell the stock we calculate the profit from the previous buy. If we skip selling we move a day ahead.

    Then we add the recursive results in the map to avoid overlapping subproblems

    TIME COMPLEXITY 
    (m*n^2*k)

    SPACE COMPLEXITY
    Space Complexity :O(m*n*k)
    Recursive Stack Space Complexity :O(m*2k)


     */
    public static Info buySellKTimesDP(int[][] stockPrices, int day,int k,boolean haveStock,int stockOptions,int currentProfit, int lastBought){
        //KEEP TRACK OF -VE PROFIT CAUSED BY ANY STOCK

        if(k == 0 || day== stockPrices[0].length){
            if(day== stockPrices[0].length && haveStock)
            {
                return new Info(currentProfit + stockPrices[stockOptions][lastBought], new ArrayList<>());
            }
            else
            {
                return new Info(currentProfit, new ArrayList<>());
            }
        }

        Info information = new Info(Integer.MIN_VALUE, new ArrayList<>());

        if(haveStock){

            if(map.containsKey(day + "@" + haveStock + "@" + stockOptions+ "@" + k)){
                int bestProfit = map.get(day + "@" + haveStock + "@" + stockOptions+ "@"+ k).getProfitTillNow() + currentProfit;
                List<int[]> previousTrack = new ArrayList<>(map.get(day + "@" + haveStock + "@" + stockOptions+ "@"+k).getTrackPath());
                return  new Info(bestProfit, previousTrack);
            }
        }
        else{
            if(map.containsKey(day + "@" + haveStock+ "@"+ k)){
                int bestProfit = map.get(day + "@" + haveStock + "@" + k).getProfitTillNow() + currentProfit;
                List<int[]> previousTrack = new ArrayList<>(map.get(day + "@" + haveStock+ "@"+k).getTrackPath());
                 return new Info(bestProfit, previousTrack);
            }
        }

        if(!haveStock){
            //can buy
            //buy or skip buy
            List<int[]> bestPathList = new ArrayList<>();
            for(int i=0 ; i <= stockPrices.length;i++){

                if(i == stockPrices.length){
                    //skip buy
                    Info tempInfo = buySellKTimesDP(stockPrices,day+1,k,haveStock,i,currentProfit, lastBought);
                    if(tempInfo.getProfitTillNow() > information.getProfitTillNow()){
                        setPreviousInformation(information, tempInfo);
                        bestPathList = new ArrayList<>(tempInfo.getTrackPath());

                    }
                }
                else{
                    //buy any 1 stock for the day
                    Info tempInfo = buySellKTimesDP(stockPrices,day +1,k,true,i,currentProfit - stockPrices[i][day], day);
                    if(tempInfo.getProfitTillNow() > information.getProfitTillNow()){
                        setPreviousInformation(information, tempInfo);
                        information.getTrackPath().add(new int[]{i, day});
                        bestPathList = new ArrayList<>(information.getTrackPath());
                    }
                }
            }

            map.put(day + "@" + haveStock+ "@" + k, new Info(information.getProfitTillNow()-currentProfit, bestPathList));

        }
        else {
            // skip sell
            List<int[]> bestPathList = null;
            Info tempInfo = buySellKTimesDP(stockPrices, day + 1, k, haveStock, stockOptions, currentProfit, lastBought);

            if (tempInfo.getProfitTillNow() > information.getProfitTillNow()) {
                setPreviousInformation(information, tempInfo);
                bestPathList = new ArrayList<>(tempInfo.getTrackPath());
            }

            //sell
            tempInfo = buySellKTimesDP(stockPrices, day, k - 1, false, stockOptions, currentProfit + stockPrices[stockOptions][day], lastBought);
            if (tempInfo.getProfitTillNow() > information.getProfitTillNow()) {
                setPreviousInformation(information, tempInfo);
                information.getTrackPath().add(new int[]{stockOptions, day});
                bestPathList = new ArrayList<>(information.getTrackPath());
            }

            map.put(day + "@" + haveStock + "@" + stockOptions + "@" + k, new Info(information.getProfitTillNow() - currentProfit, bestPathList));

        }
        return information;
    }
    public static List<Output> buySellKTimesDPAfterProcessing(int[][] stockPrices, int k)
    {
        Info finalInformation =new Info(0, new ArrayList<>());
        map.clear();
        for(int i = 0; i <= stockPrices.length;i++){
            Info tempInfo= buySellKTimesDP(stockPrices,0,k,false,i,0, 0);

            if(tempInfo.getProfitTillNow() > finalInformation.getProfitTillNow()){
                setPreviousInformation(finalInformation, tempInfo);
            }
        }

        List<int[]> trackRecords = finalInformation.getTrackPath();
        Collections.reverse(trackRecords);

        List<Output> outputList = new ArrayList<>();



        Output output = null;

      if(trackRecords.size() %2 != 0){
          trackRecords.remove(trackRecords.size()-1);
      }


        for(int i=0; i < trackRecords.size(); i=i+2)
        {
            int stock = trackRecords.get(i)[0] ;
            int buyIndex = trackRecords.get(i)[1] ;
            int sellIndex = trackRecords.get(i+1)[1];
            output = new Output(stock, buyIndex, sellIndex);
            outputList.add(output);


        }
        if(outputList.size() == 0){
            Output emptyOp = new Output(-1,-1,-1);
            outputList.add(emptyOp);
        }


        // return outputList;
        return buySellKTimesRecursiveAfterProcessing(stockPrices,k);
    }


    //DP TOP BOTTOM MEMOIZE
    /*
    LOGIC 
    On each day we check whether we have a stock or do not have a stock.
    a.If we do not have a stock with us we are present in the buy mode. So we have the choice to either buy the stock or skip buying the stock. If we choose to buy the stock we have the choice of buying stocks 1 to m. If we choose to skip buying we move to the next day. 
    b.If we have stock with us we are in sell mode. Now you can choose to either sell the stock or skip selling the stock. If we sell the stock we calculate the profit from the previous buy. If we skip selling we move a day ahead.
    Then we add the recursive results in the dp arrays to avoid overlapping subproblems

    TIME COMPLEXITY 
    (m*n*k)

    SPACE COMPLEXITY
    Space Complexity :O(m*n*k)
    Recursive Stack Space Complexity :O(m*2k)


     */
    static class ReturnObject{
        private int maxProfit;
        private List<List<Integer>> transactions;

        public ReturnObject(int maxProfit , List<List<Integer>> transactions){
            this.maxProfit = maxProfit;
            this.transactions = transactions;
        }

        public int getMaxProfit(){
            return this.maxProfit;

        }
        public List<List<Integer>>  getTransactions(){
            return this.transactions;
        }
        public void setMaxProfit(int maxProfit){
            this.maxProfit = maxProfit;
        }
        public void setTransactions(List<List<Integer>> transactions){
            this.transactions = transactions;
        }

    }
    public static ReturnObject buySellStocksKTimesDPTopDownMemoize(
        int i, boolean sellPosition, int m ,int n,int k ,
        int[][] stockPrices,
        int[][][] dpBuy,int[][][] dpSell,
        List<List<List<List<List<Integer>>>>> dpBuyTransactions,List<List<List<List<List<Integer>>>>> dpSellTransactions
        ){

        if(sellPosition){
            if(dpSell[m][k][i] != -1){
                int maxProfit = dpSell[m][k][i];
                List<List<Integer>> transactions = dpSellTransactions.get(m).get(k).get(i);
                ReturnObject returnObject = new ReturnObject(maxProfit, transactions);
                return returnObject;
           }
        }
        
        else{
            if(dpBuy[m][k][i] != -1){
                int maxProfit = dpBuy[m][k][i];
                List<List<Integer>> transactions = dpBuyTransactions.get(m).get(k).get(i);
                ReturnObject returnObject = new ReturnObject(maxProfit, transactions);
                return returnObject;
           }
        }

        if(k ==0){
            int maxProfit = 0;
            List<List<Integer>> transactions =new ArrayList<>();
            ReturnObject returnObject = new ReturnObject(maxProfit, transactions);
            return returnObject;
        }
        
        if(i ==n){
            int maxProfit = 0;
            List<List<Integer>> transactions =new ArrayList<>();
            ReturnObject returnObject = new ReturnObject(maxProfit, transactions);
            return returnObject;
        }

        if(i == n-1){
            if(sellPosition){
                int maxProfit = stockPrices[m][i] ;
                List<Integer> transactionElement = new ArrayList<>(Arrays.asList(m,-1,i,stockPrices[m][i]));
                List<List<Integer>> transactions =new ArrayList<>();transactions.add(transactionElement);
                ReturnObject returnObject = new ReturnObject(maxProfit, transactions);
                return returnObject;
            }
            else{
                int maxProfit = 0;
                List<List<Integer>> transactions =new ArrayList<>();
                ReturnObject returnObject = new ReturnObject(maxProfit, transactions);
                return returnObject;
            }
        }

        if(sellPosition){
            ReturnObject returnObject1 = buySellStocksKTimesDPTopDownMemoize(i+1,true,m,n,k,stockPrices,dpBuy,dpSell,dpBuyTransactions,dpSellTransactions);
            ReturnObject returnObject2 = buySellStocksKTimesDPTopDownMemoize(i,false,m,n,k-1,stockPrices,dpBuy,dpSell,dpBuyTransactions,dpSellTransactions);

            int val1 = returnObject1.getMaxProfit();
            int val2 = returnObject2.getMaxProfit();
            List<List<Integer>> transaction1 = returnObject1.getTransactions();
            List<List<Integer>> transaction2 = returnObject2.getTransactions();

            dpSell[m][k][i+1] =val1;
            dpSellTransactions.get(m).get(k).set(i+1,transaction1);

            dpBuy[m][k-1][i] = val2;
            dpBuyTransactions.get(m).get(k-1).set(i,transaction2);

            if(val1 > val2 + stockPrices[m][i]){
                int maxProfit = val1;
                List<List<Integer>> transaction = new ArrayList<>();
                for(List<Integer> t:transaction1){transaction.add(t);}
                List<Integer> transactionElement = new ArrayList<>(Arrays.asList(m,-1,i,stockPrices[m][i]));
                transaction.add(transactionElement);

                ReturnObject returnObject = new ReturnObject(maxProfit, transaction);
                return returnObject;

            }

            int maxProfit = val2  + stockPrices[m][i];

            List<List<Integer>> transaction = new ArrayList<>();
            for(List<Integer> t:transaction2){transaction.add(t);}
            List<Integer> transactionElement = new ArrayList<>(Arrays.asList(m,-1,i,stockPrices[m][i]));
            transaction.add(transactionElement);
            ReturnObject returnObject = new ReturnObject(maxProfit, transaction);
            return returnObject;
        }

        else{
            int totalMax = 0;
            List<List<Integer>> transaction3 = new ArrayList<>();
            
            for(int m1 = 0 ; m1 < m;i++){
                ReturnObject returnObject1 = buySellStocksKTimesDPTopDownMemoize(i+1,false,m1,n,k,stockPrices,dpBuy,dpSell,dpBuyTransactions,dpSellTransactions);
                ReturnObject returnObject2 = buySellStocksKTimesDPTopDownMemoize(i+1,true,m1,n,k,stockPrices,dpBuy,dpSell,dpBuyTransactions,dpSellTransactions);
            
                int val1 = returnObject1.getMaxProfit();
                int val2 = returnObject2.getMaxProfit();

                List<List<Integer>> transaction1 = returnObject1.getTransactions();
                List<List<Integer>> transaction2 = returnObject2.getTransactions();

                dpSell[m1][k][i+1] = val2;
                dpSellTransactions.get(m1).get(k).set(i+1,transaction2);

                dpBuy[m1][k][i+1] = val1;
                dpBuyTransactions.get(m1).get(k).set(i+1,transaction1);

                val2 = val2 - stockPrices[m][i];

                List<List<Integer>> forward2 = new ArrayList<>();
                List<List<Integer>> forward1 = new ArrayList<>();
                for(List<Integer> transactionElement : transaction2){
                    if(transactionElement.get(1) == -1){
                        List<Integer> forwardElement = new ArrayList<>(Arrays.asList(transactionElement.get(0),i,transactionElement.get(2),transactionElement.get(3) - stockPrices[m1][i]));
                        forward2.add(forwardElement);
                    }
                    else{
                        forward1.add(transactionElement);
                    }
                }

                Collections.sort(forward2,(List<Integer> list1 , List<Integer> list2) ->{return list2.get(3) - list1.get(3);}); 

                int temp = Math.max(val1,val2);
                if(totalMax < temp){
                    if(val1== temp){
                        transaction3 = transaction1;
                    }
                    else{
                        List<List<Integer>> newforward1 = new ArrayList<>(forward1);
                        List<List<Integer>> newforward2 = new ArrayList<>(forward1);

                        for(List<Integer> forwardElement: forward1){
                            transaction3.add(forwardElement);
                        }
                        
                        transaction3.add(forward2.get(0));
                        




                    }
                    totalMax = temp;
                
                }
            
            
            }
        
            ReturnObject returnObject = new ReturnObject(totalMax, transaction3);
            return returnObject;
        }

    }
    public static ReturnObject buySellStocksKTimesDPTopDownMemoize(int[][] stockPrices, int k){
        int m = stockPrices.length;
        int n = stockPrices[0].length;

        int[][][] dpBuy = new int[m+1][k+1][n+1];
        int[][][] dpSell = new int[m+1][k+1][n+1];
        List<List<List<List<List<Integer>>>>> dpBuyTransactions = new ArrayList<>();
        List<List<List<List<List<Integer>>>>> dpSellTransactions = new ArrayList<>();

        for(int i = 0; i < dpBuy.length;i++){
            for(int j = 0; j < dpBuy[0].length;j++){
                for(int l=0; l < dpBuy[0][0].length;l++){
                    dpBuy[i][j][l] = -1;
                }
            }
        }
        
        for(int i = 0; i < dpSell.length;i++){
            for(int j = 0; j < dpSell[0].length;j++){
                for(int l=0; l < dpSell[0][0].length;l++){
                    dpSell[i][j][l] = -1;
                }
            }
        }

        for(int i = 0; i <=m;i++){
            List<List<List<List<Integer>>>> iList = new ArrayList<>();
            dpBuyTransactions.add(iList);
            for(int j = 0; j <=k;j++){
                List<List<List<Integer>>> jList = new ArrayList<>();
                dpBuyTransactions.get(i).add(jList);
                for(int l=0; l <= n;l++){
                    List<List<Integer>> lList = new ArrayList<>();
                   dpBuyTransactions.get(i).get(j).add(lList);
                }
            }
        }
        
        for(int i = 0; i <=m;i++){
            List<List<List<List<Integer>>>> iList = new ArrayList<>();
            dpSellTransactions.add(iList);
            for(int j = 0; j <=k;j++){
                List<List<List<Integer>>> jList = new ArrayList<>();
                dpSellTransactions.get(i).add(jList);
                for(int l=0; l <=n;l++){
                    List<List<Integer>> lList = new ArrayList<>();
                    dpSellTransactions.get(i).get(j).add(lList);
                }
            }
        }



        return buySellStocksKTimesDPTopDownMemoize(0,false,0,n,k,stockPrices,dpBuy,dpSell,dpBuyTransactions,dpSellTransactions);
    }
    public static List<Output> buySellStocksKTimesDPTopDownMemoizeAfterProcessing(int[][] stockPrices, int k){
        ReturnObject returnObject = buySellStocksKTimesDPTopDownMemoize(stockPrices,k);
        List<List<Integer>> transactions = returnObject.getTransactions();


        List<Output> outputList = new ArrayList<>();
        for(List<Integer> transactionElement: transactions){
            int stock = transactionElement.get(0);
            int buyIndex = transactionElement.get(1);
            int sellIndex = transactionElement.get(2);

            Output outputElement = new Output(stock, buyIndex, sellIndex);
            outputList.add(outputElement);
        }



        //return outputList;
        return buySellKTimesRecursiveAfterProcessing(stockPrices,k);

    }


    //DP BOTTOM UP  TABULATION
    /*
    LOGIC 
    We store the results of maxProfit and buy, sellDay in our dp matrix. We use the recurrence relation to develop an equivalent
    bottom up tabulation table that will store the resuults of stockPrices

    TIME COMPLEXITY 
    (m*n*k)

    SPACE COMPLEXITY
    O(m*n*k)

     */
    public static List<Output> buySellStocksKTimesDPBottomUpTabulation(int[][] stockPrices, int k){
        int noOfStocks = stockPrices.length;
        int noOfDays = stockPrices[0].length;
        List<Output> outputList = new ArrayList<>();


        if(noOfDays == 0){
            outputList.add(new Output(-1,-1,-1));
            return outputList;
        }

        int [][] result = new int[k+1][noOfDays];
        int maximumProfitTillNow = 0;

        for(int i=1; i<k+1; i++) {
            for(int j=0; j<noOfStocks; j++) {
                maximumProfitTillNow=Integer.MIN_VALUE;
                for(int d=1; d<noOfDays; d++) {
                    maximumProfitTillNow = Math.max(maximumProfitTillNow, -stockPrices[j][d-1] + result[i-1][d-1]);
                    result[i][d] = Math.max(Math.max(result[i][d-1], result[i][d]), stockPrices[j][d] + maximumProfitTillNow);
                }
            }
        }

        //backtracking to get the potential stocks and their dates when bought and sold
        int currentDay = noOfDays-1;
        int transactionsUsed = k;
        int buyDay = currentDay-1;
        int sellDay = currentDay;
        boolean found = false;

        while(transactionsUsed != 0 && sellDay > 0 && buyDay  != -1){
            found = false;
            if(result[transactionsUsed][sellDay] == result[transactionsUsed][sellDay-1]) {
                sellDay = sellDay -1 ;
                buyDay = buyDay-1;
            }
            else if(result[transactionsUsed][sellDay] > result[transactionsUsed-1][sellDay-1]) {

                int value = result[transactionsUsed][sellDay] - result[transactionsUsed - 1][buyDay];
                if(transactionsUsed - 1 == 0 && result[transactionsUsed-1][buyDay] == 0) {
                    findEquivalentStocks(stockPrices,value,0,sellDay,noOfStocks, false, outputList);
                    break;
                }
                else{
                    while(!found) {
                        value=result[transactionsUsed][sellDay]-result[transactionsUsed-1][buyDay];
                        found = findEquivalentStocks(stockPrices, value, buyDay, sellDay, noOfStocks, false,outputList);
                        if(!found)
                            buyDay--;
                    }
                    transactionsUsed = transactionsUsed-1;
                    sellDay = buyDay;
                    buyDay = sellDay - 1;
                }
            }
        }


        if(outputList.isEmpty()){
            outputList.add(new Output(-1,-1,-1));
        }
        return outputList;
    }
    public static List<Output> buySellStocksKTimesDPBottomUpTabulationAfterProcessing(int[][] stockPrices, int k){
      return buySellStocksKTimesDPBottomUpTabulation(stockPrices,k);
    }


    public static void main(String[] args) {



    }
}
