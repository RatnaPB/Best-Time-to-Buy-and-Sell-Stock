import java.util.ArrayList;
import java.util.Scanner;

public class Task5 {
    static ArrayList<Integer> sell = new ArrayList<>();
    static ArrayList<Integer> buy = new ArrayList<>();
    static ArrayList<Integer> stockNumber = new ArrayList<>();
    static int[][][] dp;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] stocks = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                stocks[i][j] = sc.nextInt();
            }
        }

        dp = new int[k + 1][m + 1][n];

        findMaxProfit(k, m, n, stocks);

        //checking how many transactions are done for max profit (can any be anywhere for 1 to k)
        int reqTrans = 0;
        int max = 0;
        for (int i = 0; i <= k; i++) {
            if (dp[i][m][n - 1] > max) {
                max = dp[i][m][n - 1];
                reqTrans = i;
            }
        }

        findBuySellIndex(k, m, n - 1, 1, 0, stocks, reqTrans, 0);
        int i = sell.size() - 1;
        int j = buy.size() - 1;
        int s = stockNumber.size() - 1;
        while (i >= 0 && j >= 0) {
            System.out.print(stockNumber.get(s) - 1 + " " + buy.get(j));
            s--;
            System.out.println(" " + sell.get(i));
            i--;
            j--;
            s--;
        }
        sc.close();
        ;

    }

    static void findMaxProfit(int k, int m, int n, int[][] stocks) {
        for (int i = 1; i <= k; i++) {

            for (int j = 1; j <= m; j++) {

                for (int p = 1; p < n; p++) {

                    int maxProfit = Integer.MIN_VALUE;
                    /*Checking Max Difference for p-1 days */
                    for (int q = 0; q < p; q++) {
                        int currentProfit = stocks[j - 1][p] - stocks[j - 1][q] + dp[i - 1][m][q];
                        if (currentProfit > maxProfit)
                            maxProfit = currentProfit;
                    }

                    /*Comparing the values for three possible values(current day sell+maxDifference, pervious day, previous stock) */
                    dp[i][j][p] = Math.max(Math.max(maxProfit, dp[i][j][p - 1]), dp[i][j - 1][p]);
                }
            }
        }
    }

    static void findBuySellIndex(int transactionIndex, int stockNumber, int day, int sell,
                                 int required, int[][] Stock, int transLeft, int prevStockIndex) {


        if (transLeft == 0) {
            return;
        }

        if (sell == 1) {
            /* If this condition is true then stock has sold on the current indexs */
            if (dp[transactionIndex][stockNumber][day] != dp[transactionIndex][stockNumber][day - 1]
                    && dp[transactionIndex][stockNumber][day] != dp[transactionIndex][stockNumber - 1][day]) {
                Task5.sell.add(day);
                Task5.stockNumber.add(stockNumber);
                findBuySellIndex(transactionIndex - 1, dp[0].length - 1, day - 1, 0,
                        dp[transactionIndex][stockNumber][day] - Stock[stockNumber - 1][day], Stock, transLeft, stockNumber);
            }
            /* The current stock value taken from previous day*/
            else if (dp[transactionIndex][stockNumber][day] == dp[transactionIndex][stockNumber][day - 1]) {
                findBuySellIndex(transactionIndex, stockNumber, day - 1,
                        sell, required, Stock, transLeft, stockNumber);
            }
            /* The current stock value taken from previous stock number*/
            else {
                findBuySellIndex(transactionIndex, stockNumber - 1, day,
                        sell, required, Stock, transLeft, stockNumber);
            }
        } else {
            /* If this condition is true then we bought on the current indexs */
            if (dp[transactionIndex][stockNumber][day] - Stock[prevStockIndex - 1][day] == required) {
                buy.add(day);
                Task5.stockNumber.add(prevStockIndex);
                findBuySellIndex(transactionIndex, stockNumber, day,
                        1, required, Stock, transLeft - 1, stockNumber);
            }
            /* The current stock value taken from previous day*/
            else {
                findBuySellIndex(transactionIndex, stockNumber, day - 1,
                        0, required, Stock, transLeft, prevStockIndex);
            }
        }
    }

}
