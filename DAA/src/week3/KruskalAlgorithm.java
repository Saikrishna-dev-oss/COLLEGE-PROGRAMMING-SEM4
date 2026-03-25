package week3;

import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class DisjointSet {
    int[] parent, rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

public class KruskalAlgorithm {
    public static void kruskalMST(List<Edge> edges, int vertices) {
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(vertices);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);

            if (rootSrc != rootDest) {
                mst.add(edge);
                ds.union(rootSrc, rootDest);
            }
        }

        System.out.println("\nEdges in the Minimum Spanning Tree:");
        for (Edge e : mst) {
            System.out.println(e.src + " - " + e.dest + " : " + e.weight);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int edgesCount = sc.nextInt();

        List<Edge> edges = new ArrayList<>();

        System.out.println("Enter edges in format: src dest weight");
        for (int i = 0; i < edgesCount; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            edges.add(new Edge(src, dest, weight));
        }

        kruskalMST(edges, vertices);
        sc.close();
    }
}
