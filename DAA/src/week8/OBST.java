package week8;
import java.util.Scanner;
public class OBST{
    private static final int MAX = 10;

    public static void main(String[] args) {
        int[][] w = new int[MAX][MAX];
        int[][] c = new int[MAX][MAX];
        int[][] r = new int[MAX][MAX];
        int[] p = new int[MAX];
        int[] q = new int[MAX];
        int rt = 0, min, min1, n;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        n = scanner.nextInt();

        System.out.println("Enter the Probabilities of Successful searches:");
        for (int i = 1; i <= n; i++) {
            System.out.print("p" + i + ": ");
            p[i] = scanner.nextInt();
        }

        System.out.println("Enter the Probabilities of Unsuccessful Searches:");
        for (int i = 0; i <= n; i++) {
            System.out.print("q" + i + ": ");
            q[i] = scanner.nextInt();
        }

        System.out.println("\nW\t\tC\t\tR");
        for (int i = 0; i <= n; i++) {
            w[i][i] = q[i];
            c[i][i] = 0;
            r[i][i] = 0;
            System.out.printf("W[%d][%d]: %d\tC[%d][%d]: %d\tR[%d][%d]: %d\n", i, i, w[i][i], i, i, c[i][i], i, i, r[i][i]);
        }
        System.out.println();

        for (int m = 1; m <= n; m++) { // Find optimal costs for trees with m nodes
            for (int i = 0; i <= n - m; i++) {
                int j = i + m;
                w[i][j] = p[j] + q[j] + w[i][j - 1];
                min = Integer.MAX_VALUE;

                for (int k = i + 1; k <= j; k++) {
                    min1 = c[i][k - 1] + c[k][j] + w[i][j];
                    if (min1 < min) {
                        min = min1;
                        rt = k;
                    }
                }

                c[i][j] = min;
                r[i][j] = rt;
                System.out.printf("W[%d][%d]: %d\tC[%d][%d]: %d\tR[%d][%d]: %d\n", i, j, w[i][j], i, j, c[i][j], i, j, r[i][j]);
            }
            System.out.println();
        }

        System.out.println("Minimum cost = " + c[0][n]);
        System.out.println("Root  = " + r[0][n]);
        scanner.close();
    }
}