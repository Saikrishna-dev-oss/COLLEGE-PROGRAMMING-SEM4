package week10;

import java.util.Scanner;
public class NQueens {
    static final int MAX = 20;
    static int ns=0;
    static int[] x = new int[MAX]; // x[k] = column position of queen in row k
    static int n;
    // Display the board configuration (as position array)
    static void display(int i) {
        System.out.println();
        for (int j = 1; j <= i; j++) {
            System.out.print(x[j] + " ");
        }
        
    }
    // Check if a queen can be placed at row k, column i
    static boolean place(int k, int i) {
        for (int j = 1; j < k; j++) {
            if (x[j] == i || Math.abs(x[j] - i) == Math.abs(j - k)) {
                return false;
            }
        }
        return true;
    }
    // Recursive function to place queens
    static void nqueens(int k, int n) {
        for (int i = 1; i <= n; i++) {
            if (place(k, i)) {
                x[k] = i;
                if (k == n) {
                    ns++;
                    display(k);
                } else {
                    nqueens(k + 1, n);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of queens: ");
        n = sc.nextInt();
        nqueens(1, n);
        System.out.print("\n No.of Solutions: "+ns);
        sc.close();
    }
}
