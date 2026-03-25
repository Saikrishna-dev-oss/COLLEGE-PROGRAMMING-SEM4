package practise;

import java.util.Scanner;

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

        int[] parent = new int[n];
        int[] key = new int[n];
        boolean[] mstSet = new boolean[n];  //Just like visited array in DIJKSHTRA

        // Initialize values
        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
            parent[i] = -1;
        }

        key[0] = 0;      // Start from first vertex
        // parent[0] = -1;  // Root node

        // Prim's algorithm
        for (int count = 0; count < n - 1; count++) {

            int u = -1;
            int min = Integer.MAX_VALUE;

            // Find minimum key vertex
            for (int v = 0; v < n; v++) {
                if (!mstSet[v] && key[v] < min) {
                    min = key[v];
                    u = v;
                }
            }

            if(u==-1)break;
            
            mstSet[u] = true;

            // Update key values
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print MST
        System.out.println("Edges in Minimum Spanning Tree:");
        int total_weight = 0;
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + " - " + i + " : " + graph[i][parent[i]]);
            total_weight += graph[i][parent[i]];
        }
        System.out.println("Total MST Weight: " + total_weight);
        sc.close();
    }
}