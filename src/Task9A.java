import java.util.Objects;
import java.util.Scanner;

public class Task9A {
    private static Integer[][] M;
    private static Integer[][] N;
    private static int[][] prices;

    private static int c;

    private static Integer[] X;

    static int computeOptSell(int m, int j) {
        if (M[m][j] == null) {
            M[m][j] = Math.max(computeOptBuy(m, j - 1) + prices[m][j], Math.max(computeOptSell(m, j - 1), computeOptSell(m - 1, j)));
        }

        return M[m][j];
    }

    static int computeOptBuy(int m, int j) {
        if (N[m][j] == null) {
            N[m][j] = Math.max(Maximum(j - c - 1) - prices[m][j], computeOptBuy(m, j - 1));
        }

        return N[m][j];
    }

    /*gets and stores maximum sell price till day j*/
    static int Maximum(int j) {
        if (j <= 0) {
            return 0;
        }

        if (X[j] == null) {
            var max = Integer.MIN_VALUE;

            for (int i = 1; i < prices.length; i++) {
                var x = computeOptSell(i, j);
                if (x > max) {
                    max = x;
                    X[j] = i;
                }
            }
        }

        return M[X[j]][j];
    }

    private static void maxProfit(int m, int n) {
        M = new Integer[m + 1][n + 1];
        N = new Integer[m + 1][n + 1];
        X = new Integer[n + 1];

        //base case: 0 sell for 1 day
        for (var j = 0; j <= m; j++) {
            M[j][1] = 0;
            N[j][1] = -prices[j][1];
        }

        //base case: 0 buy-sell for 0 stocks
        for (var j = 0; j <= n; j++) {
            M[0][j] = 0;
            N[0][j] = 0;
        }

        /* compute opt sell(m, n)*/
        computeOptSell(m, n);

        /* backtrack answers to get solution*/
        findSolutionSell(m, n);
    }

    private static void findSolutionSell(int m, int n) {
        if (m <= 0 || n <= 0) {
            return;
        }

        if (Objects.equals(M[m][n], M[m - 1][n])) {
            findSolutionSell(m - 1, n);
        } else if (Objects.equals(M[m][n], M[m][n - 1])) {
            findSolutionSell(m, n - 1);
        } else {
            findSolutionBuy(m, n - 1);
            System.out.println(" " + n);
        }
    }

    private static void findSolutionBuy(int m, int n) {
        if (m <= 0 || n <= 0) {
            return;
        }

        if (Objects.equals(N[m][n], N[m][n - 1])) {
            findSolutionBuy(m, n - 1);
        } else {
            if (n - c - 1 > 1 && X[n - c - 1] != null && X[n - c - 1] > 0) {
                findSolutionSell(X[n - c - 1], n - c - 1);
            }
            System.out.print(m + " " + n);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        var m = sc.nextInt();
        var n = sc.nextInt();

        prices = new int[m + 1][n + 1];

        for (var i = 1; i <= m; i++) {
            for (var j = 1; j <= n; j++) {
                prices[i][j] = sc.nextInt();
            }
        }

        sc.close();

        maxProfit(m, n);
    }
}