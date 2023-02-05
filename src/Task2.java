import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int buyIndex = -1;
        int sellIndex = -1;
        int stockIndex = -1;
        int globalTotalmax = 0;
        for (int j = 0; j < m; j++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int currentBuyIndex = -1;
            int currentSellIndex = -1;
            for (int i = 1; i < n; i++) {
                if (a[j][i] < min) {
                    min = a[j][i];
                    currentBuyIndex = i;
                } else if (a[j][i] - min > max) {
                    currentSellIndex = i;
                    max = a[j][i] - min;
                }
            }
            if (max > globalTotalmax) {
                globalTotalmax = max;
                buyIndex = currentBuyIndex;
                sellIndex = currentSellIndex;
                stockIndex = j;
            }
        }
        System.out.println((stockIndex + 1) + " " + (buyIndex + 1) + " " + (sellIndex + 1));
    }
}