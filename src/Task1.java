import java.util.Scanner;

public class Task1 {
    public static void maxProfit(int[][] prices, int m, int n) {
        int maxProfit = 0;
        int buy = 0, sell = 0, stock = 0;
        for (int k = 0; k < m; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    /*comparing all the stock prices in the matrix with each other.*/
                    int diff = prices[k][j] - prices[k][i];
                    if (diff > maxProfit) {
                        buy = i;
                        sell = j;
                        stock = k;
                        maxProfit = diff;
                    }
                }
            }
        }

        System.out.println((stock + 1) + " " + (buy + 1) + " " + (sell + 1));

    }

    public static void main(String[] args) {
        //Input of m x n size matrix from user.
        Scanner sc = new Scanner(System.in);
        int var1 = sc.nextInt();
        int var2 = sc.nextInt();

        int[][] prices = new int[var1][var2];

        for (int k = 0; k < var1; k++) {
            for (int i = 0; i < var2; i++) {
                prices[k][i] = sc.nextInt();
            }
        }
        sc.close();
        maxProfit(prices, var1, var2);
    }
}