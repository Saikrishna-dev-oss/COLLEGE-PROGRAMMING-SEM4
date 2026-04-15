package week9;

import java.util.Scanner;
public class SumOfSubSets {
    static int s = 0, n, m, k;
    static int[] x = new int[20]; // x[i] is 1 if w[i] is included
    static int[] w = new int[20]; // weights
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = 0;
        System.out.print("Enter number of weights: ");
        n = sc.nextInt();
        System.out.println("Enter the weights:");
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
        }
        System.out.print("Enter the value of m: ");
        m = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            r += w[i];
        }
        if (m > r) {
            System.out.println("No possible solution");
        } else if (m == r) {
            System.out.println("Given set itself is the solution");
        } else {
            System.out.println("The possible subsets are:");
            sumOfSubset(0, 1, r);
        }
        sc.close();
    }
    static void sumOfSubset(int s, int k, int r) {
        x[k] = 1;
        if (s + w[k] == m) {
            print(k);
            System.out.println();
        } else if ((s + w[k] + w[k + 1]) <= m) {
            //printf("\n %d,%d,%d",s+w[k],k+1,r-w[k]);
            sumOfSubset(s + w[k], k + 1, r - w[k]);
        }
        if ((s + r - w[k] >= m) && (s + w[k + 1] <= m)) {
            x[k] = 0;
            //printf("\n %d,%d,%d",s,k+1,r-w[k]);
            sumOfSubset(s, k + 1, r - w[k]);
        }
    }
    static void print(int y) {
        for (int i = y + 1; i <= n; i++) {
            x[i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(x[i] + " ");
        }
    }
}
