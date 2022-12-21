import java.util.*;

public class Problem3Test {

    static int[][] input1 = new int[][]{{1,6,4,5,7,10},{3,2,5,7,8,9},{2,1,4,7,2,10}};static int c1 = 2;
    static int[][] input2 = new int[][]{{7,1,5,3,6,2},{1,2,3,4,5,4},{6,7,10,8,2,7},{4,8,1,6,7,9}};static int c2 = 2;
    static int[][] input3 = new int[][]{{10,9,1,5,1,6,2,8},{4,1,7,5,4,6,5,9},{4,8,6,3,6,4,9,2},{6,3,9,6,8,2,10,4},{10,7,6,2,9,8,10,7}};static int c3 = 3;
    static int[][] input4 = new int[][]{{1,6,4,5,6,9,7,8},{3,2,6,7,3,1,6,8}};static int c4 = 3;
    static int[][] input5 = new int[][]{{7,1,5,3,6,2,5,4,6,2},{4,2,8,4,5,4,6,2,1,6},{6,7,9,8,2,7,1,6,4,9}};static int c5 = 3;
    static int[][] input6 = new int[][]{{1,6,4,5},{3,2,5,7},{2,1,4,7}};static int c6 = 1;
    static int[][] input7 = new int[][]{{3,6,5,6,6,4,8,5,1,9},{1,5,3,9,2,3,2,4,1,4}};static int c7 = 4;
    static int[][] input8 = new int[][]{{10,9,1,5,1,6,2,8},{4,1,7,5,4,6,5,9},{6,3,10,6,8,2,10,4},{1,7,6,2,9,8,10,7}};static int c8 = 2;
    static int[][] input9 = new int[][]{{4,3,2,1},{8,7,6,5}};static int c9 = 2;
    static int[][] input10 = new int[][]{{3,2,1}};static int c10 = 1;
    static int[][] input11 = new int[][]{{}};static int c11 = 1;
    static int[][] input12 = new int[][]{{1,7,4,0,9,1},{4,8,8,2,4,6},{5,5,1,7,1,1}};static int c12 = 2;

    static int[][] output1 = new int[][]{{0,0,1},{2,4,5}};

    static int[][] output2 = new int[][]{{3,0,1},{2,4,5}};

    static int[][] output3_1 = new int[][]{{1,1,2},{0,6,7}};

    static int[][] output3_2 = new int[][]{{3,1,3},{0,6,7}};

    static int[][] output3_3 = new int[][]{{2,0,1},{3,5,6}};

    static int[][] output4 = new int[][]{{0,0,1},{1,5,7}};


    static int[][] output5 = new int[][]{{1,1,2},{2,6,9}};

    static int[][] output5_2 = new int[][]{{3,0,1},{3,2,4}};

    static int[][] output6 = new int[][]{{2,1,3}};


    static int[][] output7 = new int[][]{{1,0,3},{0,8,9}};


    static int[][] output8 = new int[][]{{2,1,2},{2,5,6}};

    static int[][] output9 = new int[][]{{-1,-1,-1}};
    static int[][] output10 = new int[][]{{-1,-1,-1}};
    static int[][] output11 = new int[][]{{-1,-1,-1}};
    static int[][] output12 = new int[][]{{0,3,4}};


    static Problem3 p3 = new Problem3();


    public static void main(String[] args){
        //testBrute();
        testDP();
        //testDPTopDownMemoize();
        // testDPBottomUpTabulation();


    }


    public static boolean deepCompare(int[][] arr1,int[][] arr2){
        Arrays.sort(arr1, (int[] a,int[] b) ->{
            if(a[0] != b[0]){
                return a[0] - b[0];
            }
            else if(a[0] == b[0] && a[1] != b[1]){
                return a[1] - b[1];
            }
            else{
                return a[2] - b[2];
            }
        });
        Arrays.sort(arr2, (int[] a,int[] b) ->{
            if(a[0] != b[0]){
                return a[0] - b[0];
            }
            else if(a[0] == b[0] && a[1] != b[1]){
                return a[1] - b[1];
            }
            else{
                return a[2] - b[2];
            }
        });

        return Arrays.deepEquals(arr1,arr2);

    }

    public static int[][] convertOutput(List<Output> output){

        int[][] outputConverted = new int[output.size()][3];

        int index =0;
        for(Output outputElement: output){
            outputConverted[index++] = new int[]{outputElement.getStock(),outputElement.getBuyDayIndex(),outputElement.getSellDayIndex()};
        }
        return outputConverted;
    }


    public  static void testBrute(){
        List<Integer> passedTest = new ArrayList<>();
        List<Integer> failedTest = new ArrayList<>();
        List<Output> output = new ArrayList<>();

        output = p3.buySellStocksCIntervalBruteAfterProcess(input1, c1);
        int[][] outputReceived1 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input2, c2);
        int[][] outputReceived2 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input3, c3);
        int[][] outputReceived3 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input4, c4);
        int[][] outputReceived4 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input5, c5);
        int[][] outputReceived5 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input6, c6);
        int[][] outputReceived6 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input7, c7);
        int[][] outputReceived7 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input8, c8);
        int[][] outputReceived8 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input9, c9);
        int[][] outputReceived9 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input10, c10);
        int[][] outputReceived10 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input11, c11);
        int[][] outputReceived11 = convertOutput(output);

        output = p3.buySellStocksCIntervalBruteAfterProcess(input12, c12);
        int[][] outputReceived12 = convertOutput(output);


        if(deepCompare(outputReceived1,output1)){
            passedTest.add(1);
        }
        else{
            failedTest.add(1);
        }

        if(deepCompare(output2,outputReceived2)){
            passedTest.add(2);
        }
        else{
            failedTest.add(2);
        }


        if(deepCompare(output3_1,outputReceived3)||deepCompare(output3_2,outputReceived3)||deepCompare(output3_3,outputReceived3)){
            passedTest.add(3);
        }
        else{
            failedTest.add(3);
        }

        if(deepCompare(output4,outputReceived4)){
            passedTest.add(4);
        }
        else{
            failedTest.add(4);
        }

        if(deepCompare(output5,outputReceived5)){
            passedTest.add(5);
        }
        else{
            failedTest.add(5);
        }

        if(deepCompare(output6,outputReceived6)){
            passedTest.add(6);
        }
        else{
            failedTest.add(6);
        }

        if(deepCompare(output7,outputReceived7)){
            passedTest.add(7);
        }
        else{
            failedTest.add(7);
        }
        if(deepCompare(output8,outputReceived8)){
            passedTest.add(8);
        }
        else{
            failedTest.add(8);
        }
        if(deepCompare(output9,outputReceived9)){
            passedTest.add(9);
        }
        else{
            failedTest.add(9);
        }
        if(deepCompare(output10,outputReceived10)){
            passedTest.add(10);
        }
        else{
            failedTest.add(10);
        }

        if(deepCompare(output11,outputReceived11)){
            passedTest.add(11);
        }
        else{
            failedTest.add(11);
        }


        if(deepCompare(output12,outputReceived12)){
            passedTest.add(12);
        }
        else{
            failedTest.add(12);
        }

        System.out.println("Brute Passed: " + passedTest);
        System.out.println("Brute failed: " + failedTest);
    }

    public static void testDP(){
        List<Integer> passedTest = new ArrayList<>();
        List<Integer> failedTest = new ArrayList<>();
        List<Output> output = new ArrayList<>();

        output = p3.buySellCIntervalDPAfterProcessing(input1, c1);
        int[][] outputReceived1 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input2, c2);
        int[][] outputReceived2 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input3, c3);
        int[][] outputReceived3 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input4, c4);
        int[][] outputReceived4 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input5, c5);
        int[][] outputReceived5 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input6, c6);
        int[][] outputReceived6 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input7, c7);
        int[][] outputReceived7 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input8, c8);
        int[][] outputReceived8 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input9, c9);
        int[][] outputReceived9 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input10, c10);
        int[][] outputReceived10 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input11, c11);
        int[][] outputReceived11 = convertOutput(output);

        output = p3.buySellCIntervalDPAfterProcessing(input12, c12);
        int[][] outputReceived12 = convertOutput(output);

        if(deepCompare(outputReceived1,output1)){
            passedTest.add(1);
        }
        else{
            failedTest.add(1);
        }

        if(deepCompare(output2,outputReceived2)){
            passedTest.add(2);
        }
        else{
            failedTest.add(2);
        }


        if(deepCompare(output3_1,outputReceived3)||deepCompare(output3_2,outputReceived3)||deepCompare(output3_3,outputReceived3)){
            passedTest.add(3);
        }
        else{
            failedTest.add(3);
        }

        if(deepCompare(output4,outputReceived4)){
            passedTest.add(4);
        }
        else{
            failedTest.add(4);
        }

        if(deepCompare(output5,outputReceived5)){
            passedTest.add(5);
        }
        else{
            failedTest.add(5);
        }

        if(deepCompare(output6,outputReceived6)){
            passedTest.add(6);
        }
        else{
            failedTest.add(6);
        }

        if(deepCompare(output7,outputReceived7)){
            passedTest.add(7);
        }
        else{
            failedTest.add(7);
        }
        if(deepCompare(output8,outputReceived8)){
            passedTest.add(8);
        }
        else{
            failedTest.add(8);
        }
        if(deepCompare(output9,outputReceived9)){
            passedTest.add(9);
        }
        else{
            failedTest.add(9);
        }
        if(deepCompare(output10,outputReceived10)){
            passedTest.add(10);
        }
        else{
            failedTest.add(10);
        }
        if(deepCompare(output11,outputReceived11)){
            passedTest.add(11);
        }
        else{
            failedTest.add(11);
        }

        if(deepCompare(output12,outputReceived12)){
            passedTest.add(12);
        }
        else{
            failedTest.add(12);
        }

        System.out.println("DP Passed: " + passedTest);
        System.out.println("DP failed: " + failedTest);
    }

    public static void testDPTopDownMemoize(){
        List<Integer> passedTest = new ArrayList<>();
        List<Integer> failedTest = new ArrayList<>();
        List<Output> output = new ArrayList<>();

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input1, c1);
        int[][] outputReceived1 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input2, c2);
        int[][] outputReceived2 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input3, c3);
        int[][] outputReceived3 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input4, c4);
        int[][] outputReceived4 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input5, c5);
        int[][] outputReceived5 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input6, c6);
        int[][] outputReceived6 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input7, c7);
        int[][] outputReceived7 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input8, c8);
        int[][] outputReceived8 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input9, c9);
        int[][] outputReceived9 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input10, c10);
        int[][] outputReceived10 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input11, c11);
        int[][] outputReceived11 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPTopDownMemoizeAfterProcessing(input12, c12);
        int[][] outputReceived12 = convertOutput(output);


        if(deepCompare(outputReceived1,output1)){
            passedTest.add(1);
        }
        else{
            failedTest.add(1);
        }

        if(deepCompare(output2,outputReceived2)){
            passedTest.add(2);
        }
        else{
            failedTest.add(2);
        }


        if(deepCompare(output3_1,outputReceived3)||deepCompare(output3_2,outputReceived3)||deepCompare(output3_3,outputReceived3)){
            passedTest.add(3);
        }
        else{
            failedTest.add(3);
        }

        if(deepCompare(output4,outputReceived4)){
            passedTest.add(4);
        }
        else{
            failedTest.add(4);
        }

        if(deepCompare(output5,outputReceived5)){
            passedTest.add(5);
        }
        else{
            failedTest.add(5);
        }

        if(deepCompare(output6,outputReceived6)){
            passedTest.add(6);
        }
        else{
            failedTest.add(6);
        }

        if(deepCompare(output7,outputReceived7)){
            passedTest.add(7);
        }
        else{
            failedTest.add(7);
        }
        if(deepCompare(output8,outputReceived8)){
            passedTest.add(8);
        }
        else{
            failedTest.add(8);
        }
        if(deepCompare(output9,outputReceived9)){
            passedTest.add(9);
        }
        else{
            failedTest.add(9);
        }
        if(deepCompare(output10,outputReceived10)){
            passedTest.add(10);
        }
        else{
            failedTest.add(10);
        }
        if(deepCompare(output11,outputReceived11)){
            passedTest.add(11);
        }
        else{
            failedTest.add(11);
        }

        if(deepCompare(output12,outputReceived12)){
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
        List<Output> output = new ArrayList<>();

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input1, c1);
        int[][] outputReceived1 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input2, c2);
        int[][] outputReceived2 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input3, c3);
        int[][] outputReceived3 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input4, c4);
        int[][] outputReceived4 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input5, c5);
        int[][] outputReceived5 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input6, c6);
        int[][] outputReceived6 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input7, c7);
        int[][] outputReceived7 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input8, c8);
        int[][] outputReceived8 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input9, c9);
        int[][] outputReceived9 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input10, c10);
        int[][] outputReceived10 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input11, c11);
        int[][] outputReceived11 = convertOutput(output);

        output = p3.buySellStocksCIntervalDPBottomUpTabulationAfterProcessing(input12, c12);
        int[][] outputReceived12 = convertOutput(output);

        if(deepCompare(outputReceived1,output1)){
            passedTest.add(1);
        }
        else{
            failedTest.add(1);
        }

        if(deepCompare(output2,outputReceived2)){
            passedTest.add(2);
        }
        else{
            failedTest.add(2);
        }


        if(deepCompare(output3_1,outputReceived3)||deepCompare(output3_2,outputReceived3)||deepCompare(output3_3,outputReceived3)){
            passedTest.add(3);
        }
        else{
            failedTest.add(3);
        }

        if(deepCompare(output4,outputReceived4)){
            passedTest.add(4);
        }
        else{
            failedTest.add(4);
        }

        if(deepCompare(output5,outputReceived5)){
            passedTest.add(5);
        }
        else{
            failedTest.add(5);
        }

        if(deepCompare(output6,outputReceived6)){
            passedTest.add(6);
        }
        else{
            failedTest.add(6);
        }

        if(deepCompare(output7,outputReceived7)){
            passedTest.add(7);
        }
        else{
            failedTest.add(7);
        }
        if(deepCompare(output8,outputReceived8)){
            passedTest.add(8);
        }
        else{
            failedTest.add(8);
        }
        if(deepCompare(output9,outputReceived9)){
            passedTest.add(9);
        }
        else{
            failedTest.add(9);
        }
        if(deepCompare(output10,outputReceived10)){
            passedTest.add(10);
        }
        else{
            failedTest.add(10);
        }
        if(deepCompare(output11,outputReceived11)){
            passedTest.add(11);
        }
        else{
            failedTest.add(11);
        }
        if(deepCompare(output12,outputReceived12)){
            passedTest.add(12);
        }
        else{
            failedTest.add(12);
        }

        System.out.println("DP Bottom UP Tabulation Passed: " + passedTest);
        System.out.println("DP Bottom UP Tabulation Failed: " + failedTest);
    }


}
