package week6;

import java.util.Scanner;

public class MatrixChainMultiplication {
    static int[][] m; // DP table for costs
    static int[][] s; // DP table for splits

    static int matrixChainOrder(int p[], int n) {
        m = new int[n][n];
        s = new int[n][n];

        for (int i = 1; i < n; i++) {
            m[i][i] = 0;
        }

        for (int L = 2; L < n; L++) {
            for (int i = 1; i < n - L + 1; i++) {
                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k; // store split point
                    }
                }
            }
        }
        return m[1][n - 1];
    }

    // Recursive function to print optimal parenthesization
    static void printOptimalParens(int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(i, s[i][j]);
            printOptimalParens(s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of matrices: ");
        int num = sc.nextInt();

        int arr[] = new int[num + 1];
        System.out.println("Enter dimensions (length " + (num + 1) + "):");
        for (int i = 0; i <= num; i++) {
            arr[i] = sc.nextInt();
        }

        int size = arr.length;
        int minCost = matrixChainOrder(arr, size);

        System.out.println("Minimum number of multiplications: " + minCost);
        System.out.print("Optimal Parenthesization: ");
        printOptimalParens(1, num);

        sc.close();
    }
}

