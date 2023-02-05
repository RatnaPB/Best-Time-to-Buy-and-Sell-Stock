import java.util.Scanner;

public class Task3A {
    private static Integer[][] M;
    private static int[][] P;
    private static int[][] prices;

    private static void maxProfit(int[][] prices, int m, int n) {
        M = new Integer[m + 1][n + 1];
        P = new int[m + 1][n + 1];

        //base case: 0 profit for day 1 for all stocks
        for (var i = 0; i <= m; i++) {
            M[i][1] = 0;
        }

        //base case: 0 profit for 0 stocks
        for (var i = 0; i <= n; i++) {
            M[0][i] = 0;
        }

        //fill P[i][j]
        //P[i][j] = the day of the least price of the stock i before day j
        for (var i = 1; i <= m; i++) {
            P[i][1] = 1;
            for (var j = 2; j <= n; j++) {
                P[i][j] = (prices[i][P[i][j - 1]]) < (prices[i][j - 1]) ? (P[i][j - 1]) : (j - 1);
            }
        }

        //top-down approach
        computeOpt(m, n);

        //backtrack to find solution
        findSolution(m, n);
    }

    private static int computeOpt(int i, int j) {
        if (M[i][j] == null) {
            M[i][j] = Math.max(prices[i][j] - prices[i][P[i][j]], Math.max(computeOpt(i - 1, j), computeOpt(i, j - 1)));
        }

        return M[i][j];
    }

    private static void findSolution(int m, int n) {
        if (M[m][n].equals(M[m - 1][n])) {
            findSolution(m - 1, n);
        } else if (M[m][n].equals(M[m][n - 1])) {
            findSolution(m, n - 1);
        } else {
            System.out.println(m + " " + P[m][n] + " " + n);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var m = sc.nextInt();
        var n = sc.nextInt();

        prices = new int[m + 1][n + 1];

        for (var i = 1; i <= m; i++) {
            for (var j = 1; j <= n; j++) {
                prices[i][j] = sc.nextInt();
            }
        }

        sc.close();

        maxProfit(prices, m, n);
    }
}