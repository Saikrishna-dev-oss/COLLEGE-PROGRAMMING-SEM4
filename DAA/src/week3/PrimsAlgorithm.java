package week3;
import java.util.*;

public class PrimsAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("Enter adjacency matrix (0 if no edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        primMST(graph, n);
        sc.close();
    }
    
    static void primMST(int[][] graph, int vertices) {
        int[] parent = new int[vertices];   // Stores constructed MST
        int[] key = new int[vertices];      // Key values used to pick minimum weight edge
        boolean[] mstSet = new boolean[vertices]; // To represent set of vertices included in MST

        // Initialize all keys as infinite
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;      // Start from vertex 0
        parent[0] = -1;  // First node is always root of MST

        for (int count = 0; count < vertices - 1; count++) {
            // Pick the minimum key vertex not yet included in MST
            int u = minKey(key, mstSet, vertices);
            mstSet[u] = true;

            // Update key and parent of adjacent vertices
            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print MST
        System.out.println("\nEdges in the Minimum Spanning Tree:");
        int totalCost = 0;
        for (int i = 1; i < vertices; i++) {
            System.out.println(parent[i] + " - " + i + " : " + graph[i][parent[i]]);
            totalCost += graph[i][parent[i]];
        }
        System.out.println("Total cost of MST = " + totalCost);
    }

    // Utility function to find vertex with minimum key value
    private static int minKey(int[] key, boolean[] mstSet, int vertices) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < vertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    
}
