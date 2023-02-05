import java.util.ArrayList;
import java.util.Scanner;

public class Task6B {
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

        sc.close();

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

    }

    static void findMaxProfit(int k, int m, int n, int[][] stocks) {
        for (int i = 1; i <= k; i++) {
            for (int x = 0; x < n; x++) {
                dp[i][0][x] = 0;
            }
            for (int j = 1; j <= m; j++) {
                int maxDiff = -stocks[j - 1][0];
                for (int p = 1; p < n; p++) {
                    /*Comparing the values for three possible values(current day sell+maxDiff, pervious day, previous stock) */
                    dp[i][j][p] = Math.max(Math.max(stocks[j - 1][p] + maxDiff, dp[i][j][p - 1]), dp[i][j - 1][p]);

                    /*Finding maxDifference on just current day */
                    maxDiff = Math.max(maxDiff, dp[i - 1][m][p] - stocks[j - 1][p]);
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
                Task6B.sell.add(day);
                Task6B.stockNumber.add(stockNumber);
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
                Task6B.stockNumber.add(prevStockIndex);
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
