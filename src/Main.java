import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String taskName = args[0];
        System.out.println("Task " +args[0]);
        Util utilObject = new Util();
        int[][] stockPrices;
        Output output;

        switch (taskName)
        {
            case "1":
                stockPrices= utilObject.fetchUserInput();


                Problem1 task1 = new Problem1();
                output = task1.buySellStocksUsingBrute(stockPrices);
                System.out.println(output.getStock() + " " + output.getBuyDayIndex() + " " + output.getSellDayIndex());

                break;
            case "2":
                stockPrices= utilObject.fetchUserInput();

                Problem1 task2 = new Problem1();
                output = task2.buySellStocksUsingGreedy(stockPrices);
                System.out.println(output.getStock() + " " + output.getBuyDayIndex() + " " + output.getSellDayIndex());
                break;
            case "3a":
                stockPrices= utilObject.fetchUserInput();

                Problem1 task3a = new Problem1();
                output = task3a.buySellStocksUsingDPTopDownMemoize(stockPrices);
                System.out.println(output.getStock() + " " + output.getBuyDayIndex() + " " + output.getSellDayIndex());
                break;
            case "3b":
                stockPrices= utilObject.fetchUserInput();

                Problem1 task3b = new Problem1();
                output = task3b.buySellStocksUsingDPBottomUpTabulation(stockPrices);
                System.out.println(output.getStock() + " " + output.getBuyDayIndex() + " " + output.getSellDayIndex());
                break;
            case "4":
                break;
            case "6a":
                break;
            case "6b":
                break;
        }
    }
}
