import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem2Test {

    static int[][] input1 = new int[][]{{1,6,4,5},{3,2,6,7},{2,1,4,3}};static int k1 = 2;
    final static int[][] input2 = new int[][]{{1,6,4,5},{3,2,6,7},{2,1,4,7}}; final static int k2 = 3;
    static int[][] input3 = new int[][]{{1,6,4,5},{3,2,6,7},{2,1,4,7}};static int k3 = 2;
    static int[][] input4 = new int[][]{{1,6,4,5},{3,2,5,7},{2,1,4,7}};static int k4 = 3;
    static int[][] input5 = new int[][]{{7,1,5,3,6},{1,2,3,4,5},{6,7,10,8,10},{4,8,1,6,7}};static int k5 = 2;
    static int[][] input6 = new int[][]{{7,1,5,3,6},{1,2,3,4,5},{6,7,10,8,10},{4,8,1,6,7}};static int k6 = 3;
    static int[][] input7 = new int[][]{{1,5,3,9,6},{4,8,5,1,9},{3,6,5,6,2},{3,2,4,1,4},{6,2,5,1,8}};static int k7 = 2;
    static int[][] input8 = new int[][]{{1,5,3,9,6},{4,8,5,1,9},{3,6,5,6,2},{3,2,4,1,4},{6,2,5,1,8}};static int k8 = 3;
    static int[][] input9 = new int[][]{{4,3,2,1},{8,7,6,5}};static int k9 = 2;
    static int[][] input10 = new int[][]{{3,2,1}};static int k10 = 1;
    static int[][] input11 = new int[][]{{}};static int k11 = 1;
    static int[][] input12 = new int[][]{{1,7,4,0,9},{4,8,8,2,4},{5,5,1,7,1}};static int k12 = 3;

    static int[][] output1 = new int[][]{{0,0,1},{1,1,3}};
    static int[][] output2 = new int[][]{{0,0,1},{1,1,2},{2,2,3}};

    static int[][] output3 = new int[][]{{0,0,1},{2,1,3}};

    static int[][] output4 = new int[][]{{0,0,1},{1,1,2},{2,2,3}};

    static int[][] output5_1 = new int[][]{{2,0,2},{3,2,4}};

    static int[][] output5_2 = new int[][]{{3,0,1},{3,2,4}};
    static int[][] output6 = new int[][]{{3,0,1},{0,1,2},{3,2,4}};
    static int[][] output7 = new int[][]{{0,0,3},{1,3,4}};

    static int[][] output8_1 = new int[][]{{1,0,1},{0,2,3},{1,3,4}};
    static int[][] output8_2 = new int[][]{{0,0,1},{0,2,3},{1,3,4}};


    static int[][] output9 = new int[][]{{-1,-1,-1}};

    static int[][] output10 = new int[][]{{-1,-1,-1}};

    static int[][] output11 = new int[][]{{-1,-1,-1}};

    static int[][] output12 = new int[][]{{0,0,1},{2,2,3},{0,3,4}};

    static Problem2 p2 = new Problem2();


    public static void main(String[] args){
      testBrute();
      testDP();
      testDPTopDownMemoize();
     testDPBottomUpTabulation();


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

        output = p2.buySellKTimesRecursiveAfterProcessing(input1, k1);
        int[][] outputReceived1 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input2, k2);
        int[][] outputReceived2 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input3, k3);
        int[][] outputReceived3 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input4, k4);
        int[][] outputReceived4 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input5, k5);
        int[][] outputReceived5 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input6, k6);
        int[][] outputReceived6 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input7, k7);
        int[][] outputReceived7 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input8, k8);
        int[][] outputReceived8 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input9, k9);
        int[][] outputReceived9 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input10, k10);
        int[][] outputReceived10 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input11, k11);
        int[][] outputReceived11 = convertOutput(output);

        output = p2.buySellKTimesRecursiveAfterProcessing(input12, k12);
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


        if(deepCompare(output3,outputReceived3)){
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

        if(deepCompare(output5_1,outputReceived5)|| deepCompare(output5_2,outputReceived5)){
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
        if(deepCompare(output8_1,outputReceived8)||deepCompare(output8_2,outputReceived8)){
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

        output = p2.buySellKTimesDPAfterProcessing(input1, k1);
        int[][] outputReceived1 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input2, k2);
        int[][] outputReceived2 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input3, k3);
        int[][] outputReceived3 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input4, k4);
        int[][] outputReceived4 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input5, k5);
        int[][] outputReceived5 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input6, k6);
        int[][] outputReceived6 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input7, k7);
        int[][] outputReceived7 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input8, k8);
        int[][] outputReceived8 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input9, k9);
        int[][] outputReceived9 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input10, k10);
        int[][] outputReceived10 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input11, k11);
        int[][] outputReceived11 = convertOutput(output);

        output = p2.buySellKTimesDPAfterProcessing(input12, k12);
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

        if(deepCompare(output3,outputReceived3)){
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

        if(deepCompare(output5_1,outputReceived5)|| deepCompare(output5_2,outputReceived5)){
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
        if(deepCompare(output8_1,outputReceived8)||deepCompare(output8_2,outputReceived8)){
            passedTest.add(8);
        }
        else{
            failedTest.add(8);
        }
        if(deepCompare(output9,outputReceived9)){
            passedTest.add(9);
        }
        else{
            for(int[] op: outputReceived1){System.out.println(Arrays.toString(op));}
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

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input1, k1);
        int[][] outputReceived1 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input2, k2);
        int[][] outputReceived2 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input3, k3);
        int[][] outputReceived3 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input4, k4);
        int[][] outputReceived4 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input5, k5);
        int[][] outputReceived5 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input6, k6);
        int[][] outputReceived6 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input7, k7);
        int[][] outputReceived7 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input8, k8);
        int[][] outputReceived8 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input9, k9);
        int[][] outputReceived9 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input10, k10);
        int[][] outputReceived10 = convertOutput(output);

        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input11, k11);
        int[][] outputReceived11 = convertOutput(output);


        output = p2.buySellStocksKTimesDPTopDownMemoizeAfterProcessing(input12, k12);
        int[][] outputReceived12 = convertOutput(output);


        if(deepCompare(outputReceived1,output1)){
            passedTest.add(1);
        }
        else{
            for(int[] op:outputReceived1){System.out.println(Arrays.toString(op));}
            for(int[] op:output1){System.out.println(Arrays.toString(op));}
            failedTest.add(1);
        }

        if(deepCompare(output2,outputReceived2)){
            passedTest.add(2);
        }
        else{
            failedTest.add(2);
        }


        if(deepCompare(output3,outputReceived3)){
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

        if(deepCompare(output5_1,outputReceived5)|| deepCompare(output5_2,outputReceived5)){
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
        if(deepCompare(output8_1,outputReceived8)||deepCompare(output8_2,outputReceived8)){
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

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input1, k1);
        int[][] outputReceived1 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input2, k2);
        int[][] outputReceived2 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input3, k3);
        int[][] outputReceived3 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input4, k4);
        int[][] outputReceived4 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input5, k5);
        int[][] outputReceived5 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input6, k6);
        int[][] outputReceived6 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input7, k7);
        int[][] outputReceived7 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input8, k8);
        int[][] outputReceived8 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input9, k9);
        int[][] outputReceived9 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input10, k10);
        int[][] outputReceived10 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input11, k11);
        int[][] outputReceived11 = convertOutput(output);

        output = p2.buySellStocksKTimesDPBottomUpTabulationAfterProcessing(input12, k12);
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


        if(deepCompare(output3,outputReceived3)){
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

        if(deepCompare(output5_1,outputReceived5)|| deepCompare(output5_2,outputReceived5)){
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
        if(deepCompare(output8_1,outputReceived8)||deepCompare(output8_2,outputReceived8)){
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
