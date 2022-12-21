import java.util.List;
import java.util.Map;

public class ExperimentalStudy {

    public static void main(String[] args) {

        /*
            Plot 1
            We will compute running time for variable n (No of days) and fixed m (Number of stocks)
         */

        PlotGenerator plotGenerator = new PlotGenerator();
        //int[] noOfDays = {10, 50, 100, 200, 500, 1000};
        int[] noOfDays = {10, 100, 1000,2000,3000};
        System.out.println("Plot 1 for variable n and fixed m");
        System.out.println(new String(new char[50]).replace('\0', '-'));
        displayComputedRunningTime(plotGenerator.timeGeneratorForFixedMVariableN(5, noOfDays), noOfDays);

        /*
            Plot 2
            We will compute running time for variable m (Number of stocks) and fixed n (Number of days)
         */

        System.out.println(new String(new char[50]).replace('\0', '-'));
        System.out.println("Plot 2 for variable m and fixed n");
        System.out.println(new String(new char[50]).replace('\0', '-'));
        //int[] noOfStocks = {10, 50, 100, 200, 500, 1000};
        int[] noOfStocks = {10, 100, 1000,2000,3000};

        displayComputedRunningTime(plotGenerator.timeGeneratorForVariableMFixedN(noOfStocks, 50), noOfStocks);

    }

    private static void displayComputedRunningTime(Map<String, List<Long>> runningTimeDataMap, int[] variableInputList)
    {
        List<Long> runningTimeList;
        for(Map.Entry<String, List<Long>> map: runningTimeDataMap.entrySet())
        {
            System.out.println(map.getKey());
            runningTimeList = map.getValue();
            int i= 0;
            while(i < variableInputList.length)
            {
                System.out.println("Input: " + variableInputList[i] + " Running time " + runningTimeList.get(i));
                i++;
            }
        }
    }
}
