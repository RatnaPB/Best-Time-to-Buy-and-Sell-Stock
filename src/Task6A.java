import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Task6A {
    private static Integer[][][] M;
    private static Integer[][][] N;
    private static int[][] prices;

    private static Integer[][] X;

    static int computeOptSell(int m, int j, int t) {
        if (M[m][t][j] == null) {
            M[m][t][j] = Math.max(computeOptBuy(m, j - 1, t) + prices[m][j], Math.max(computeOptSell(m, j - 1, t), computeOptSell(m - 1, j, t)));
        }

        return M[m][t][j];
    }

    static int computeOptBuy(int m, int j, int t) {
        if (N[m][t][j] == null) {
            N[m][t][j] = Math.max(Maximum(j, t - 1) - prices[m][j], computeOptBuy(m, j - 1, t));
        }

        return N[m][t][j];
    }

    /*gets and stores maximum sell price till day j and transaction t*/
    static int Maximum(int j, int t) {

        if (X[t][j] == null) {
            var max = Integer.MIN_VALUE;

            for (int i = 1; i < prices.length; i++) {
                var x = computeOptSell(i, j, t);
                if (x > max) {
                    max = x;
                    X[t][j] = i;
                }
            }
        }

        return M[X[t][j]][t][j];
    }

    private static void maxProfit(int m, int n, int k) {
        M = new Integer[m + 1][k + 1][n + 1];
        N = new Integer[m + 1][k + 1][n + 1];
        X = new Integer[k + 1][n + 1];

        /*base case: 0 sell for 1 day, -price buy for day 1*/
        for (var i = 0; i <= k; i++) {
            for (var j = 0; j <= m; j++) {
                M[j][i][1] = 0;
                N[j][i][1] = -prices[j][1];
            }
        }

        /*base case: 0 buy-sell for 0 transactions*/
        for (var i = 0; i <= n; i++) {
            for (var j = 0; j <= m; j++) {
                M[j][0][i] = 0;
                N[j][0][i] = 0;
            }
        }

        /*base case: 0 buy-sell for 0 stocks*/
        for (var i = 0; i <= k; i++) {
            for (var j = 0; j <= n; j++) {
                M[0][i][j] = 0;
                N[0][i][j] = 0;
            }
        }

        /*compute opt sell (m, n, k)*/
        computeOptSell(m, n, k);

        /*backtrack solution*/
        findSolutionSell(m, k, n);
    }

    private static void findSolutionSell(int m, int k, int n) {
        if (m <= 0 || k <= 0 || n <= 0) {
            return;
        }

        if (Objects.equals(M[m][k][n], M[m - 1][k][n])) {
            findSolutionSell(m - 1, k, n);
        } else if (Objects.equals(M[m][k][n], M[m][k][n - 1])) {
            findSolutionSell(m, k, n - 1);
        } else if (Objects.equals(M[m][k][n], M[m][k - 1][n])) {
            findSolutionSell(m, k - 1, n);
        } else {
            findSolutionBuy(m, k, n - 1);
            System.out.println(" " + n);
        }
    }

    private static void findSolutionBuy(int m, int k, int n) {
        if (m <= 0 || k <= 0 || n <= 0) {
            return;
        }

        if (Objects.equals(N[m][k][n], N[m][k][n - 1])) {
            findSolutionBuy(m, k, n - 1);
        } else {
            if (X[k - 1][n] != null) {
                findSolutionSell(X[k - 1][n], k - 1, n);
            }
            System.out.print(m + " " + n);
        }
    }

    public static void main(String[] args) {
        var random = new Random();
        Scanner sc = new Scanner(System.in);
        var k = sc.nextInt();
        var m = sc.nextInt();
        var n = sc.nextInt();

        prices = new int[m + 1][n + 1];

        for (var i = 1; i <= m; i++) {
            for (var j = 1; j <= n; j++) {
                prices[i][j] = sc.nextInt();
            }
        }

        sc.close();

        maxProfit(m, n, k);
    }
}