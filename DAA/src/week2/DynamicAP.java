package week2;

import java.util.*;

public class DynamicAP {
    static int timer = 1;

    static void findAP(int u, int p, List<List<Integer>> adj, int[] dfn, int[] low, boolean[] isAP) {
        dfn[u] = low[u] = timer++;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == p) continue;
            if (dfn[v] != 0) {
                low[u] = Math.min(low[u], dfn[v]);
            } else {
                children++;
                findAP(v, u, adj, dfn, low, isAP);
                low[u] = Math.min(low[u], low[v]);
                if (p != -1 && low[v] >= dfn[u]) isAP[u] = true;
            }
        }
        if (p == -1 && children > 1) isAP[u] = true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] dfn = new int[n + 1];
        int[] low = new int[n + 1];
        boolean[] isAP = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (dfn[i] == 0) findAP(i, -1, adj, dfn, low, isAP);
        }

        System.out.print("Articulation Points: ");
        for (int i = 1; i <= n; i++) if (isAP[i]) System.out.print(i + " ");
        sc.close();
    }
}