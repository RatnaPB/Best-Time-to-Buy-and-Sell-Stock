import java.util.Scanner;
import java.util.*;
public class Task8 {
    static int[][] prices;
    static int c;
    static int[][][] dp;
    static int globalMax = 0;
    static List<List<Integer>> l = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        c = sc.nextInt();
        int m = sc.nextInt();
        int n = sc.nextInt();

        prices = new int[m][n];
        dp = new int[n][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prices[i][j] = sc.nextInt();
            }
        }

        List<List<Integer>> l1 = new ArrayList<>();

        int profit = findMaxProfit(0, 1, 0, l1, 0);

        System.out.println("Profit: " + profit);

        sc.close();
    }

    static int findMaxProfit(int day, int buy, int prevBuy, List<List<Integer>> l1, int prevProfit) {

        if (day >= prices[0].length) {
            return 0;
        }
        if (prevBuy != 0 && dp[day][prevBuy][buy] != 0)
            return dp[day][prevBuy][buy];
        if (buy == 1) {
            int profit1 = findMaxProfit(day + 1, buy, prevBuy, l1, prevProfit);
            int profit2 = findMaxProfit(day + 1, 0, day, l1, prevProfit);
            dp[day][prevBuy][buy] = Math.max(profit1, profit2);
            return Math.max(profit1, profit2);
        } else {
            int profit1 = findMaxProfit(day + 1, buy, prevBuy, l1, prevProfit);
            int maxProfit = 0;
            int stockIndex = 0;

            /*Checking all the prices of m stocks on prevBuy day and current day and selecting only 
            the stock that has max difference*/
            for (int i = 0; i < prices.length; i++) {
                if (prices[i][day] - prices[i][prevBuy] > maxProfit) {
                    maxProfit = prices[i][day] - prices[i][prevBuy];
                    stockIndex = i;
                }
            }
            ArrayList<Integer> current = new ArrayList<>();
            List<List<Integer>> l2 = new ArrayList<>(l1);
            current.add(stockIndex);
            current.add(prevBuy);
            current.add(day);
            l2.add(current);
            int sellProfit = maxProfit + prevProfit;

            if (sellProfit >= globalMax) {
                if (sellProfit == globalMax) {
                    //If there are two list of same size print the list with less size 
                    if (l.size() == 0 || l.size() > l1.size()) {
                        l = l2;
                    }
                } else {
                    l = l2;
                    globalMax = sellProfit;
                }
            }

            int profit2 = findMaxProfit(day + 1 + c, 1, 0, l2, sellProfit) + maxProfit;

            dp[day][prevBuy][buy] = Math.max(profit1, profit2);

            return Math.max(profit1, profit2);
        }
    }
}
