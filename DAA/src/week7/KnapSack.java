package week7;
import java.util.Scanner;

public class KnapSack {

    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of values: ");
        int n = sc.nextInt();
        int [] w = new int[n + 1];
        int [] p = new int[n + 1];
        for(int i = 0; i < n; i++) {
            System.out.print("Enter the Profit and weight of Object " + (i + 1)+" "+ ": ");
            p[i + 1] = sc.nextInt();
            w[i + 1] = sc.nextInt();
        }
        System.out.print("Enter the Size of Knap Sack: ");
        int m = sc.nextInt();

        int [][] knapmat = new int[n+1][m+1];
        // DP knapsack
        for(int i = 1; i <= n ; i++) {
            for(int j = 1; j <= m; j++) {
                if(w[i] <= j) {
                    knapmat[i][j] = Math.max(knapmat[i -1][j],p[i] + knapmat[i - 1][j - w[i]]);
                }
                else {
                    knapmat[i][j] = knapmat[i - 1][j];
                }
            }
        }
        // Printing knapsack table
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                System.out.print(knapmat[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nMaximum Profit = " + knapmat[n][m]);

        // Backtracking to find selected items
        int res = knapmat[n][m];
        int cap = m;
        System.out.print("Selected items: ");
        for(int i = n; i > 0 && res > 0; i--) {
            if(res != knapmat[i-1][cap]) {
                // Item i was included
                System.out.print("Object " + i + " (Profit=" + p[i] + ", Weight=" + w[i] + ")  \n");
                res -= p[i];
                cap -= w[i];
            }
        }

        sc.close();
    }
}