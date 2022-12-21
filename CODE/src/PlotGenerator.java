import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlotGenerator {

    Problem1 p1;
    public PlotGenerator(){
        p1 = new Problem1();
    }

    public int[][] matrixGenerator(int m, int n){
        int[][] matrix = new int[m][n];

        for(int i=0; i < matrix.length;i++){
            for(int j=0; j < matrix[0].length;j++){
                int randomStockPrice = (int)(Math.random()*(double)10.0);
                matrix[i][j] = randomStockPrice;
            }
        }

        return matrix;

    }

    public Map<String,List<Long>> timeGeneratorForFixedMVariableN(int m , int[] arr_n){
        List<Long> timeForbuySellStocksBrute = new ArrayList<>();
        List<Long> timeForbuySellStocksGreedy = new ArrayList<>();
        List<Long> timeForbuySellStocksDPTopDownMemoize = new ArrayList<>();
        List<Long> timeForbuySellStocksDPBottomUpTabulation = new ArrayList<>();

        for(int n:arr_n){
            int[][] stocks = matrixGenerator(m,n);
            long startTime = System.nanoTime();
            p1.buySellStocksUsingBrute(stocks);
            long stopTime = System.nanoTime();
            timeForbuySellStocksBrute.add(stopTime - startTime);
        }

        for(int n:arr_n){
            int[][] stocks = matrixGenerator(m,n);
            long startTime = System.nanoTime();
            p1.buySellStocksUsingGreedy(stocks);
            long stopTime = System.nanoTime();
            timeForbuySellStocksGreedy.add(stopTime - startTime);
        }


        for(int n:arr_n){
            int[][] stocks = matrixGenerator(m,n);
            long startTime = System.nanoTime();
            p1.buySellStocksUsingDPTopDownMemoize(stocks);
            long stopTime = System.nanoTime();
            timeForbuySellStocksDPTopDownMemoize.add(stopTime - startTime);
        }

        for(int n:arr_n){
            int[][] stocks = matrixGenerator(m,n);
            long startTime = System.nanoTime();
            p1.buySellStocksUsingDPBottomUpTabulation(stocks);
            long stopTime = System.nanoTime();
            timeForbuySellStocksDPBottomUpTabulation.add(stopTime - startTime);
        }

        Map<String,List<Long>> timeMap = new HashMap<>();
        timeMap.put("RUNNING_TIME_FOR_TASK1",timeForbuySellStocksBrute);
        timeMap.put("RUNNING_TIME_FOR_TASK2",timeForbuySellStocksGreedy);
        timeMap.put("RUNNING_TIME_FOR_TASK3A",timeForbuySellStocksDPTopDownMemoize);
        timeMap.put("RUNNING_TIME_FOR_TASK3B",timeForbuySellStocksDPBottomUpTabulation);

        return timeMap;


    }

    public Map<String,List<Long>>  timeGeneratorForVariableMFixedN(int[] arr_m ,int n){
        List<Long> timeForbuySellStocksBrute = new ArrayList<>();
        List<Long> timeForbuySellStocksGreedy = new ArrayList<>();
        List<Long> timeForbuySellStocksDPTopDownMemoize = new ArrayList<>();
        List<Long> timeForbuySellStocksDPBottomUpTabulation = new ArrayList<>();

        for(int m:arr_m){
            int[][] stocks = matrixGenerator(m,n);
            long startTime = System.nanoTime();
            p1.buySellStocksUsingBrute(stocks);
            long stopTime = System.nanoTime();
            timeForbuySellStocksBrute.add(stopTime - startTime);
        }

        for(int m:arr_m){
            int[][] stocks = matrixGenerator(m,n);
            long startTime = System.nanoTime();
            p1.buySellStocksUsingGreedy(stocks);
            long stopTime = System.nanoTime();
            timeForbuySellStocksGreedy.add(stopTime - startTime);
        }



        for(int m:arr_m){
            int[][] stocks = matrixGenerator(m,n);
            long startTime = System.nanoTime();
            p1.buySellStocksUsingDPTopDownMemoize(stocks);
            long stopTime = System.nanoTime();
            timeForbuySellStocksDPTopDownMemoize.add(stopTime - startTime);
        }

        for(int m:arr_m){
            int[][] stocks = matrixGenerator(m,n);
            long startTime = System.nanoTime();
            p1.buySellStocksUsingDPBottomUpTabulation(stocks);
            long stopTime = System.nanoTime();
            timeForbuySellStocksDPBottomUpTabulation.add(stopTime - startTime);
        }

        Map<String,List<Long>> timeMap = new HashMap<>();
        timeMap.put("RUNNING_TIME_FOR_TASK1",timeForbuySellStocksBrute);
        timeMap.put("RUNNING_TIME_FOR_TASK2",timeForbuySellStocksGreedy);
        timeMap.put("RUNNING_TIME_FOR_TASK3A",timeForbuySellStocksDPTopDownMemoize);
        timeMap.put("RUNNING_TIME_FOR_TASK3B",timeForbuySellStocksDPBottomUpTabulation);

        return timeMap;

    }







}
