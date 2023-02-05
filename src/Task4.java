import java.util.*;

public class Task4 {
    public static helpInitialFunc findSolution(Integer[][] stockPrices, int day, int k, boolean considerBuy, Integer ID, Integer boughtDay, ArrayList<ArrayList<Integer>> result) {
        int profitMaximum = 0;

        var output = new ArrayList<ArrayList<Integer>>();

        if ((day >= stockPrices[0].length) || (k == 0)) {
            return new helpInitialFunc(profitMaximum, result);
        }

        if (considerBuy) {
            /* Holding stocks */
            helpInitialFunc resultHelper = findSolution(stockPrices, day + 1, k, considerBuy, ID, boughtDay, (ArrayList<ArrayList<Integer>>) result.clone());

            int profit = resultHelper.profitMaximum;
            ArrayList<ArrayList<Integer>> outputPresent = resultHelper.result;

            if (profit > profitMaximum) {
                profitMaximum = profit;
                output = outputPresent;
            }

            /* Selling stock */
            int isDifference = stockPrices[ID][day] - stockPrices[ID][boughtDay];

            ArrayList<ArrayList<Integer>> modifiedOutput = (ArrayList<ArrayList<Integer>>) result.clone();

            modifiedOutput.add(new ArrayList<Integer>(Arrays.asList(ID, boughtDay, day)));

            resultHelper = findSolution(stockPrices, day, k - 1, false, null, null, modifiedOutput);

            profit = resultHelper.profitMaximum;
            profit = profit + isDifference;
            outputPresent = resultHelper.result;

            if (profit > profitMaximum) {
                profitMaximum = profit;
                output = outputPresent;
            }

        } else {
            /* if we are to skip any particular day */
            helpInitialFunc resultHelper = findSolution(stockPrices, day + 1, k, false, null, null, (ArrayList<ArrayList<Integer>>) result.clone());
            int profit = resultHelper.profitMaximum;
            ArrayList<ArrayList<Integer>> outputPresent = resultHelper.result;

            if (profit > profitMaximum) {
                profitMaximum = profit;
                output = outputPresent;
            }

            // Buying stocks
            for (int i = 0; i < stockPrices.length; i++) {
                resultHelper = findSolution(stockPrices, day + 1, k, true, i, day, (ArrayList<ArrayList<Integer>>) result.clone());
                profit = resultHelper.profitMaximum;
                outputPresent = resultHelper.result;

                if (profit > profitMaximum) {
                    profitMaximum = profit;
                    output = outputPresent;
                }
            }
        }

        return new helpInitialFunc(profitMaximum, output);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int m = sc.nextInt();
        int n = sc.nextInt();

        Integer[][] stockPrices = new Integer[m][n];

        /* Input from the users */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                stockPrices[i][j] = sc.nextInt();
            }
        }

        helpInitialFunc res = findSolution(stockPrices, 0, k, false, null, null, new ArrayList<ArrayList<Integer>>());

        for (ArrayList<Integer> list : res.result) {
            for (Integer i : list) {
                System.out.print((i + 1) + " ");
            }
            System.out.println();
        }
    }
}

class helpInitialFunc {
    public int profitMaximum;
    public ArrayList<ArrayList<Integer>> result;

    public helpInitialFunc(int profitMaximum, ArrayList<ArrayList<Integer>> result) {
        this.profitMaximum = profitMaximum;
        this.result = result;
    }
}