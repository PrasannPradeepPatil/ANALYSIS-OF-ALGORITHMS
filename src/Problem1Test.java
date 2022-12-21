import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1Test {

    static int[][] input1 = new int[][]{{7,1,5,3,6},{1,2,3,4,5}};
    static int[][] input2 = new int[][]{{8,4,9,3},{9,1,5,7}};
    static int[][] input3 = new int[][]{{6,5,3,6,9},{6,10,4,1,7},{6,9,7,8,10}};
    static int[][] input4 = new int[][]{{6,5,3,6,9},{6,10,4,1,7},{6,9,7,8,10},{4,8,1,9,7}};
    static int[][] input5 = new int[][]{{10,9,1,5,1,6,2,5},{4,1,7,5,4,6,5,9},{4,10,6,3,6,4,9,2},{6,3,10,6,8,2,10,4},{10,7,6,2,9,8,10,7}};
    static int[][] input6 = new int[][]{{6,5,4,2,7,4},{5,1,2,5,4,1},{3,9,6,4,2,2},{4,2,1,6,8,2},{7,5,7,3,9,7},{8,9,5,1,6,6}};
    static int[][] input7 = new int[][]{{7,1,5,3,6},{1,2,3,4,5},{6,9,7,8,10},{4,8,1,6,7}};
    static int[][] input8 = new int[][]{{6,1,1,2},{2,3,9,10},{2,7,8,4},{4,4,9,4},{5,3,3,6},{3,2,1,9},{1,5,4,4,}};
    static int[][] input9 = new int[][]{{4,3,2,1},{8,7,6,5}};
    static int[][] input10 = new int[][]{{3,2,1}};
    static int[][] input11 = new int[][]{{}};
    static int[][] input12 = new int[][]{{1,7,4,0,9},{4,8,8,2,4},{5,5,1,7,1}};

    static int[] output1 = new int[]{0,1,4};
    static int[] output2 = new int[]{1,1,3};
    static int[] output3_1 = new int[]{0,2,4};
    static int[] output3_2 = new int[]{1,3,4};
    static int[] output4 = new int[]{3,2,3};
    static int[] output5 = new int[]{1,1,7};
    static int[] output6 = new int[]{3,2,4};
    static int[] output7 = new int[]{3,2,4};
    static int[] output8_1 = new int[]{1,0,3};
    static int[] output8_2 = new int[]{5,2,3};
    static int[] output9 = new int[]{-1,-1,-1};
    static int[] output10 = new int[]{-1,-1,-1};
    static int[] output11 = new int[]{-1,-1,-1};
    static int[] output12 = new int[]{0,3,4};

    static Problem1 p1 = new Problem1();


    public static void main(String[] args){
        testBrute();
        testGreedy();
        testDPTopDownMemoize();
        testDPBottomUpTabulation();


    }



    public  static void testBrute(){
        List<Integer> passedTest = new ArrayList<>();
        List<Integer> failedTest = new ArrayList<>();
        Output output = new Output();

        output = p1.buySellStocksUsingBrute(input1);
        int[] outputReceived1 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingBrute(input2);
        int[] outputReceived2 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingBrute(input3);
        int[] outputReceived3 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingBrute(input4);
        int[] outputReceived4 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingBrute(input5);
        int[] outputReceived5 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingBrute(input6);
        int[] outputReceived6 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingBrute(input7);
        int[] outputReceived7 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingBrute(input8);
        int[] outputReceived8 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingBrute(input9);
        int[] outputReceived9 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingBrute(input10);
        int[] outputReceived10 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingBrute(input11);
        int[] outputReceived11 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingBrute(input12);
        int[] outputReceived12 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};

        if(Arrays.equals(output1,outputReceived1)){
            passedTest.add(1);
        }
        else{
            failedTest.add(1);
        }

        if(Arrays.equals(output2,outputReceived2)){
            passedTest.add(2);
        }
        else{
            failedTest.add(2);
        }


        if(Arrays.equals(output3_1,outputReceived3)|| Arrays.equals(output3_2,outputReceived3)){
            passedTest.add(3);
        }
        else{
            failedTest.add(3);
        }

        if(Arrays.equals(output4,outputReceived4)){
            passedTest.add(4);
        }
        else{
            failedTest.add(4);
        }

        if(Arrays.equals(output5,outputReceived5)){
            passedTest.add(5);
        }
        else{
            failedTest.add(5);
        }

        if(Arrays.equals(output6,outputReceived6)){
            passedTest.add(6);
        }
        else{
            failedTest.add(6);
        }

        if(Arrays.equals(output7,outputReceived7)){
            passedTest.add(7);
        }
        else{
            failedTest.add(7);
        }
        if(Arrays.equals(output8_1,outputReceived8) || Arrays.equals(output8_2,outputReceived8)){
            passedTest.add(8);
        }
        else{
            failedTest.add(8);
        }
        if(Arrays.equals(output9,outputReceived9)){
            passedTest.add(9);
        }
        else{
            failedTest.add(9);
        }
        if(Arrays.equals(output10,outputReceived10)){
            passedTest.add(10);
        }
        else{
            failedTest.add(10);
        }
        if(Arrays.equals(output11,outputReceived11)){
            passedTest.add(11);
        }
        else{
            failedTest.add(11);
        }
        if(Arrays.equals(output12,outputReceived12)){
            passedTest.add(12);
        }
        else{
            failedTest.add(12);
        }


        System.out.println("Brute Passed: " + passedTest);
        System.out.println("Brute failed: " + failedTest);
    }

    public static void testGreedy(){
        List<Integer> passedTest = new ArrayList<>();
        List<Integer> failedTest = new ArrayList<>();
        Output output = new Output();

        output = p1.buySellStocksUsingGreedy(input1);
        int[] outputReceived1 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingGreedy(input2);
        int[] outputReceived2 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingGreedy(input3);
        int[] outputReceived3 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingGreedy(input4);
        int[] outputReceived4 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingGreedy(input5);
        int[] outputReceived5 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingGreedy(input6);
        int[] outputReceived6 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingGreedy(input7);
        int[] outputReceived7 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingGreedy(input8);
        int[] outputReceived8 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingGreedy(input9);
        int[] outputReceived9 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingGreedy(input10);
        int[] outputReceived10 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingGreedy(input11);
        int[] outputReceived11 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingGreedy(input12);
        int[] outputReceived12 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};

        if(Arrays.equals(output1,outputReceived1)){
            passedTest.add(1);
        }
        else{
            failedTest.add(1);
        }

        if(Arrays.equals(output2,outputReceived2)){
            passedTest.add(2);
        }
        else{
            failedTest.add(2);
        }


        if(Arrays.equals(output3_1,outputReceived3)|| Arrays.equals(output3_2,outputReceived3)){
            passedTest.add(3);
        }
        else{
            failedTest.add(3);
        }

        if(Arrays.equals(output4,outputReceived4)){
            passedTest.add(4);
        }
        else{
            failedTest.add(4);
        }

        if(Arrays.equals(output5,outputReceived5)){
            passedTest.add(5);
        }
        else{
            failedTest.add(5);
        }

        if(Arrays.equals(output6,outputReceived6)){
            passedTest.add(6);
        }
        else{
            failedTest.add(6);
        }

        if(Arrays.equals(output7,outputReceived7)){
            passedTest.add(7);
        }
        else{
            failedTest.add(7);
        }
        if(Arrays.equals(output8_1,outputReceived8) || Arrays.equals(output8_2,outputReceived8)){
            passedTest.add(8);
        }
        else{
            failedTest.add(8);
        }
        if(Arrays.equals(output9,outputReceived9)){
            passedTest.add(9);
        }
        else{
            failedTest.add(9);
        }
        if(Arrays.equals(output10,outputReceived10)){
            passedTest.add(10);
        }
        else{
            failedTest.add(10);
        }
        if(Arrays.equals(output11,outputReceived11)){
            passedTest.add(11);
        }
        else{
            failedTest.add(11);
        }
        if(Arrays.equals(output12,outputReceived12)){
            passedTest.add(12);
        }
        else{
            failedTest.add(12);
        }


        System.out.println("Greedy Passed: " + passedTest);
        System.out.println("Greedy failed: " + failedTest);
    }

    public static void testDPTopDownMemoize(){
        List<Integer> passedTest = new ArrayList<>();
        List<Integer> failedTest = new ArrayList<>();
        Output output = new Output();

        output = p1.buySellStocksUsingDPTopDownMemoize(input1);
        int[] outputReceived1 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPTopDownMemoize(input2);
        int[] outputReceived2 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingDPTopDownMemoize(input3);
        int[] outputReceived3 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPTopDownMemoize(input4);
        int[] outputReceived4 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPTopDownMemoize(input5);
        int[] outputReceived5 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPTopDownMemoize(input6);
        int[] outputReceived6 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingDPTopDownMemoize(input7);
        int[] outputReceived7 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingDPTopDownMemoize(input8);
        int[] outputReceived8 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPTopDownMemoize(input9);
        int[] outputReceived9 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPTopDownMemoize(input10);
        int[] outputReceived10 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPTopDownMemoize(input11);
        int[] outputReceived11 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPTopDownMemoize(input12);
        int[] outputReceived12 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};


        if(Arrays.equals(output1,outputReceived1)){
            passedTest.add(1);
        }
        else{
            failedTest.add(1);
        }

        if(Arrays.equals(output2,outputReceived2)){
            passedTest.add(2);
        }
        else{
            failedTest.add(2);
        }


        if(Arrays.equals(output3_1,outputReceived3)|| Arrays.equals(output3_2,outputReceived3)){
            passedTest.add(3);
        }
        else{
            failedTest.add(3);
        }

        if(Arrays.equals(output4,outputReceived4)){
            passedTest.add(4);
        }
        else{
            failedTest.add(4);
        }

        if(Arrays.equals(output5,outputReceived5)){
            passedTest.add(5);
        }
        else{
            failedTest.add(5);
        }

        if(Arrays.equals(output6,outputReceived6)){
            passedTest.add(6);
        }
        else{
            failedTest.add(6);
        }

        if(Arrays.equals(output7,outputReceived7)){
            passedTest.add(7);
        }
        else{
            failedTest.add(7);
        }
        if(Arrays.equals(output8_1,outputReceived8) || Arrays.equals(output8_2,outputReceived8)){
            passedTest.add(8);
        }
        else{
            failedTest.add(8);
        }
        if(Arrays.equals(output9,outputReceived9)){
            passedTest.add(9);
        }
        else{
            failedTest.add(9);
        }
        if(Arrays.equals(output10,outputReceived10)){
            passedTest.add(10);
        }
        else{
            failedTest.add(10);;
        }
        if(Arrays.equals(output11,outputReceived11)){
            passedTest.add(11);
        }
        else{
            failedTest.add(11);
        }
        if(Arrays.equals(output12,outputReceived12)){
            passedTest.add(12);
        }
        else{
            failedTest.add(12);
        }


        System.out.println("DP Top Down Memoize Passed: " + passedTest);
        System.out.println("DP Top Down Memoize failed: " + failedTest);
    }

    public static void testDPBottomUpTabulation(){
        List<Integer> passedTest = new ArrayList<>();
        List<Integer> failedTest = new ArrayList<>();
        Output output = new Output();

        output = p1.buySellStocksUsingDPBottomUpTabulation(input1);
        int[] outputReceived1 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPBottomUpTabulation(input2);
        int[] outputReceived2 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingDPBottomUpTabulation(input3);
        int[] outputReceived3 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPBottomUpTabulation(input4);
        int[] outputReceived4 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPBottomUpTabulation(input5);
        int[] outputReceived5 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPBottomUpTabulation(input6);
        int[] outputReceived6 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingDPBottomUpTabulation(input7);
        int[] outputReceived7 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output = p1.buySellStocksUsingDPBottomUpTabulation(input8);
        int[] outputReceived8 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPBottomUpTabulation(input9);
        int[] outputReceived9 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPBottomUpTabulation(input10);
        int[] outputReceived10 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPBottomUpTabulation(input11);
        int[] outputReceived11 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};
        output= p1.buySellStocksUsingDPBottomUpTabulation(input12);
        int[] outputReceived12 = new int[]{output.getStock(),output.getBuyDayIndex(),output.getSellDayIndex()};


        if(Arrays.equals(output1,outputReceived1)){
            passedTest.add(1);
        }
        else{
            failedTest.add(1);
        }

        if(Arrays.equals(output2,outputReceived2)){
            passedTest.add(2);
        }
        else{
            failedTest.add(2);
        }


        if(Arrays.equals(output3_1,outputReceived3)|| Arrays.equals(output3_2,outputReceived3)){
            passedTest.add(3);
        }
        else{
            failedTest.add(3);
        }

        if(Arrays.equals(output4,outputReceived4)){
            passedTest.add(4);
        }
        else{
            failedTest.add(4);
        }

        if(Arrays.equals(output5,outputReceived5)){
            passedTest.add(5);
        }
        else{
            failedTest.add(5);
        }

        if(Arrays.equals(output6,outputReceived6)){
            passedTest.add(6);
        }
        else{
            failedTest.add(6);
        }

        if(Arrays.equals(output7,outputReceived7)){
            passedTest.add(7);
        }
        else{
            failedTest.add(7);
        }
        if(Arrays.equals(output8_1,outputReceived8) || Arrays.equals(output8_2,outputReceived8)){
            passedTest.add(8);
        }
        else{
            failedTest.add(8);
        }
        if(Arrays.equals(output9,outputReceived9)){
            passedTest.add(9);
        }
        else{
            System.out.println(Arrays.toString(outputReceived9));
            failedTest.add(9);
        }
        if(Arrays.equals(output10,outputReceived10)){
            passedTest.add(10);
        }
        else{
            System.out.println(Arrays.toString(outputReceived10));
            failedTest.add(10);
        }
        if(Arrays.equals(output11,outputReceived11)){
            passedTest.add(11);
        }
        else{
            failedTest.add(11);
        }
        if(Arrays.equals(output12,outputReceived12)){
            passedTest.add(12);
        }
        else{
            failedTest.add(12);
        }


        System.out.println("DP Bottom UP Tabulation Passed: " + passedTest);
        System.out.println("DP Bottom UP Tabulation Failed: " + failedTest);
    }









}
