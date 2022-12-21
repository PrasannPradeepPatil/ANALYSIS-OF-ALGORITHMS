import java.sql.Array;
import java.util.*;

public class Problem3 {

    public static Map<String, Info> map = new HashMap<>();
    public static Map<String, String> map1 = new HashMap<>();

    private static void setPreviousInformation(Info currentInformation, Info previousInformation){
        currentInformation.setProfitTillNow(previousInformation.getProfitTillNow());
        currentInformation.getTrackPath().clear();
        currentInformation.getTrackPath().addAll(previousInformation.getTrackPath());
    }


    //BRUTE FORCE RECURSIVE
    /* 
    LOGIC 
    On each day we check whether we have a stock or do not have a stock.
    a.If we do not have a stock with us we are present in the buy mode. 
    So we have the choice to either buy the stock or skip buying the stock. 
    If we choose to buy the stock we have the choice of buying stocks 1 to m. 
    If we choose to skip buying we move to the  day + c + 1. 
    
    b.If we have stock with us we are in sell mode. Now you can choose to either sell the stock or skip selling the stock. 
    If we sell the stock we calculate the profit from the previous buy. If we skip selling we move a day ahead.


    TIME COMPLEXITY 
    (m*2^n)

    SPACE COMPLEXITY
    Space Complexity :O(1)
    Recursive Stack Space Complexity :O(m*n)

     */
    public static Info buySellStocksCIntervalBrute(int[][] stockPrices, int day, boolean haveStock,int stockOptions,int currentProfit, int c){
        //KEEP TRACK OF -VE PROFIT CAUSED BY ANY STOCK

        if(day > stockPrices[0].length-1){
            if(haveStock)
            {
                return new Info(0, new ArrayList<>());
            }
            else
            {
                return new Info(currentProfit, new ArrayList<>());
            }
        }

        //int profitTillNow = Integer.MIN_VALUE;
        Info information = new Info(Integer.MIN_VALUE, new ArrayList<>());

        if(!haveStock){
            //can buy
            //buy or skip buy
            for(int i=0 ; i <= stockPrices.length;i++){
                if(i == stockPrices.length){
                    //skip buy
                    Info tempInfo = buySellStocksCIntervalBrute(stockPrices,day+1,haveStock,i,currentProfit, c);
                    if( tempInfo.getProfitTillNow() > information.getProfitTillNow())
                    {
                        setPreviousInformation(information, tempInfo);
                    }
                }
                else{
                    //buy any 1 stock for the day

                    Info tempInfo = buySellStocksCIntervalBrute(stockPrices,day +1,true,i,currentProfit - stockPrices[i][day], c);

                    if( tempInfo.getProfitTillNow() > information.getProfitTillNow()){
                        setPreviousInformation(information, tempInfo);
                        information.getTrackPath().add(new int[]{i, day});
                    }
                }
            }

        }
        else{
            // skip sell
            Info tempInfo = buySellStocksCIntervalBrute(stockPrices,day+1,haveStock,stockOptions,currentProfit, c);
            if( tempInfo.getProfitTillNow() > information.getProfitTillNow())
            {
                setPreviousInformation(information, tempInfo);
            }

            //sell
            tempInfo = buySellStocksCIntervalBrute(stockPrices,day + c + 1, !haveStock,stockOptions,currentProfit+stockPrices[stockOptions][day], c);

            if( tempInfo.getProfitTillNow() > information.getProfitTillNow())
            {
                setPreviousInformation(information, tempInfo);
                information.getTrackPath().add(new int[]{stockOptions, day});
            }

        }

        return information;
    }
    public static List<Output> buySellStocksCIntervalBruteAfterProcess(int[][] stockPrices, int c) {
        Info finalInformation = new Info(0, new ArrayList<>());

        for(int i = 0; i <= stockPrices.length; i++){
            Info tempInfo = buySellStocksCIntervalBrute(stockPrices,0,false, i,0,c);
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
    If we choose to skip buying we move to the  day + c + 1. 
    
    b.If we have stock with us we are in sell mode. Now you can choose to either sell the stock or skip selling the stock. 
    If we sell the stock we calculate the profit from the previous buy. If we skip selling we move a day ahead.

    Then we add the recursive results in the map to avoid overlapping subproblems

    TIME COMPLEXITY 
    (m*n^2)

    SPACE COMPLEXITY
    Space Complexity :O(m*n)
    Recursive Stack Space Complexity :O(m*n)


     */
    
    public static Info buySellCIntervalTimesDP(int[][] stockPrices, int day, boolean haveStock,int stockOptions,int currentProfit, int c){
        //KEEP TRACK OF -VE PROFIT CAUSED BY ANY STOCK

        if(day > stockPrices[0].length-1){
            if(haveStock)
            {
                return new Info(0, new ArrayList<>());
            }
            else
            {
                return new Info(currentProfit, new ArrayList<>());
            }
        }

        Info information = new Info(Integer.MIN_VALUE, new ArrayList<>());

        if(haveStock){
            if(map.containsKey(day + "@" + haveStock +"@" +  stockOptions)){
                int bestProfit = map.get(day + "@" + haveStock +"@" +  stockOptions).getProfitTillNow() + currentProfit;
                List<int[]> previousTrack = new ArrayList<>(map.get(day + "@" + haveStock + "@" + stockOptions).getTrackPath());

                return new Info(bestProfit,previousTrack);

            }
        }
        else{
            if(map.containsKey(day + "@" + haveStock )){
                int bestProfit = map.get(day + "@" + haveStock ).getProfitTillNow() + currentProfit;
                List<int[]> previousTrack = new ArrayList<>(map.get(day + "@" + haveStock).getTrackPath());

                return new Info(bestProfit,previousTrack);

            }

        }

        if(!haveStock){
            //can buy
            //buy or skip buy
            List<int[]> bestPathList = new ArrayList<>();
            for(int i=0 ; i <= stockPrices.length;i++){
                if(i == stockPrices.length){
                    //skip buy
                    Info tempInfo = buySellCIntervalTimesDP(stockPrices,day+1,haveStock,i,currentProfit, c);
                    if( tempInfo.getProfitTillNow() > information.getProfitTillNow()){
                        setPreviousInformation(information, tempInfo);
                        bestPathList = new ArrayList<>(tempInfo.getTrackPath());
                    }
                }
                else{
                    //buy any 1 stock for the day

                    Info tempInfo = buySellCIntervalTimesDP(stockPrices,day +1,true,i,currentProfit - stockPrices[i][day], c);

                    if( tempInfo.getProfitTillNow() > information.getProfitTillNow())
                    {
                        setPreviousInformation(information, tempInfo);
                        information.getTrackPath().add(new int[]{i, day});
                        bestPathList = new ArrayList<>(information.getTrackPath());
                    }
                }
            }
            map.put(day + "@" + haveStock,new Info(information.getProfitTillNow()-currentProfit,bestPathList));

        }
        else{
            // skip sell
            List<int[]> bestPathList = null;
            Info tempInfo = buySellCIntervalTimesDP(stockPrices,day+1,haveStock,stockOptions,currentProfit, c);
            if( tempInfo.getProfitTillNow() > information.getProfitTillNow()){
                setPreviousInformation(information, tempInfo);
                bestPathList = new ArrayList<>(tempInfo.getTrackPath());
            }

            //sell
            tempInfo = buySellCIntervalTimesDP(stockPrices,day + c + 1, !haveStock,stockOptions,currentProfit+stockPrices[stockOptions][day], c);

            if( tempInfo.getProfitTillNow() > information.getProfitTillNow()){
                setPreviousInformation(information, tempInfo);
                information.getTrackPath().add(new int[]{stockOptions, day});
                bestPathList = new ArrayList<>(information.getTrackPath());
            }

            map.put(day + "@" + haveStock + "@" + stockOptions,new Info(information.getProfitTillNow()-currentProfit,bestPathList));
        }

        return information;
    }
    public static List<Output> buySellCIntervalDPAfterProcessing(int[][] stockPrices, int c){
        Info finalInformation =new Info(0, new ArrayList<>());
        map.clear();
        for(int i = 0; i <= stockPrices.length;i++){
            Info tempInfo= buySellCIntervalTimesDP(stockPrices,0,false,i,0, c);

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


        return outputList;
    }


    public static List<Output> buySellStocksCIntervalDPTopDownMemoize(int[][] stockPrices, int k){
        List<Output> outputList = new ArrayList<>();
        return outputList;
    }
    public static List<Output> buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(int[][] stockPrices, int k){
        List<Output> outputList = new ArrayList<>();
        return outputList;
    }

    public static List<Output> buySellStocksCIntervalDPBottomUpTabulation(int[][] stockPrices, int k){
        List<Output> outputList = new ArrayList<>();
        return outputList;
    }
    public static List<Output> buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(int[][] stockPrices, int k){
        List<Output> outputList = new ArrayList<>();
        return outputList;
    }



    public static int[][] convertOutput(List<Output> output){

        int[][] outputConverted = new int[output.size()][3];

        int index =0;
        for(Output outputElement: output){
            outputConverted[index++] = new int[]{outputElement.getStock(),outputElement.getBuyDayIndex(),outputElement.getSellDayIndex()};
        }
        return outputConverted;
    }


    public static void main(String[] args) {
        int[][] input8 = new int[][]{{10,9,1,5,1,6,2,8},{4,1,7,5,4,6,5,9},{6,3,10,6,8,2,10,4},{1,7,6,2,9,8,10,7}}; int c8 = 2;
        int[][] output8 = new int[][]{{3,0,1},{2,5,6}};


        List<Output> output = buySellCIntervalDPAfterProcessing(input8, c8);
        int[][] outputReceived8 = convertOutput(output);

        for(int[] op:outputReceived8){System.out.println(Arrays.toString(op));}
    }
}
