package week11;

import java.util.Scanner;
public class HamiltonCycle {
    static final int MAX = 25;
    static int[] x = new int[MAX]; // x[i] = i-th vertex in the path
    // Try the next valid vertex
    static void nextVertex(int[][] G, int n, int k) {
        while (true) {
            x[k] = (x[k] + 1) % (n + 1);  // try next vertex
            if (x[k] == 0)
                return; // no further vertices to try
            if (G[x[k - 1]][x[k]] == 1) { // edge must exist between previous and current
                int j;
                for (j = 1; j < k; j++) {
                    if (x[j] == x[k]) // vertex already used
                        break;
                }
                if (j == k) { // if no repetition
                    if (k < n || (k == n && G[x[n]][x[1]] == 1)) // cycle complete if last node
                        return;
                }
            }
        }
    }

    static void hamiltonianCycle(int[][] G, int n, int k) {
        while (true) {
            nextVertex(G, n, k);
            if (x[k] == 0)
                return; // no more options at position k
            if (k == n) {
                // Found one Hamiltonian cycle
                System.out.print("Cycle: ");
                for (int i = 1; i <= n; i++) {
                    System.out.print(x[i] + " ");
                }
                System.out.println(x[1]); // complete the cycle
            } else {
                hamiltonianCycle(G, n, k + 1);
            }
        }
    }
    public static void main(String[] args) {
        int[][] G = new int[MAX][MAX];
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Hamiltonian Cycle Finder ---");
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        // Initialize graph with 0s
        for (int i = 1; i <= n; i++) {
            x[i] = 0;
            for (int j = 1; j <= n; j++) {
                G[i][j] = 0;
            }
        }
        // Input edges
        for (int i = 0; i < e; i++) {
            System.out.print("Enter edge " + (i + 1) + " (v1 v2): ");
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            G[v1][v2] = 1;
            G[v2][v1] = 1;
        }
        x[1] = 1; // Start from vertex 1
        System.out.println("\nHamiltonian cycle(s):");
        hamiltonianCycle(G, n, 2);
        sc.close();
    }
}
