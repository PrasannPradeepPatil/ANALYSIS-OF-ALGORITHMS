import java.util.Arrays;
import java.util.Scanner;

public class Util {

    public int[][] fetchUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of stocks (m) and number of days (n)");
        String userInputIndices = scanner.nextLine();
        int m = Integer.parseInt(userInputIndices.split(" ")[0]);
        int n = Integer.parseInt(userInputIndices.split(" ")[1]);

        int[][] stockPrices = new int[m][n];

        for(int i = 0; i< m; i++) {
            int stock = i+1;
            System.out.println("Enter value of stock "+stock+" for "+n+" days");
            String input= scanner.nextLine();
            String[] charPrices = input.split(" ");
            int[] prices = new int[n];
            for(int j=0;j <n;j++){
                prices[j] = Integer.parseInt(charPrices[j]) ;
            }
            stockPrices[i] = prices;


        }

         return stockPrices;
    }
}
