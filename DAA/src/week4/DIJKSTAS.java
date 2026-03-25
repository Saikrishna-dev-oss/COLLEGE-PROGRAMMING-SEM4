package week4;

import java.util.*;

public class DIJKSTAS {

    static final int INF = 999999;

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
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();
        dijkstra(graph, n, src);
        sc.close();
    }

    static void dijkstra(int[][] graph, int n, int src) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int[] parent = new int[n]; // To store the shortest path tree

        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            visited[i] = false;
            parent[i] = -1; // Initialize parents as -1
        }

        dist[src] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = -1;
            int min = INF;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }

            if (u == -1) break; // Optimization: unreachable nodes
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u; // Record the path
                }
            }   
        }

        // Print result with Paths
        System.out.print("-------------------------------------");
        System.out.println("\nVertex \t Distance \t Path");
        System.out.println("-------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.print(src + " to " + i + " \t " + (dist[i] == INF ? "INF" : dist[i]) + " \t\t ");
            if (dist[i] == INF) {
                System.out.println("No Path");
            } else {
                printPath(parent, i);
                System.out.println();
            }
        }
    }

    static void printPath(int[] parent, int i) {
        // Base Case : If i is source
        if (parent[i] == -1) {
            System.out.print(i);
            return;
        }
        printPath(parent, parent[i]);
        System.out.print(" -> " + i);
    }
}



