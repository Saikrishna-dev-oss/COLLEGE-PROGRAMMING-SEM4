package practise;

//Kruskals ALgorithm
import java.util.*;

public class KruskalAlgorithm {

    // Simple Edge class
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Parent array for Union-Find
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        // Input number of edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        // Create array to store all edges
        Edge[] edges = new Edge[E];

        // Input edges
        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(u, v, w);
        }

        // Run Kruskal's algorithm
        kruskalsAlgorithm(edges, V, E);

        sc.close();
    }

    static void kruskalsAlgorithm(Edge[] edges, int V, int E) {
        // STEP 1: Sort all edges by weight
        Arrays.sort(edges, (a, b) -> a.weight - b.weight);

        // Initialize parent array (each vertex is its own parent initially)
        parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        System.out.println("\n=== KRUSKAL'S ALGORITHM ===");
        System.out.println("Edges in MST:");

        int totalWeight = 0;
        int edgeCount = 0;

        // STEP 2: Process edges one by one
        for (int i = 0; i < E && edgeCount < V - 1; i++) {
            Edge current = edges[i];

            // STEP 3: Find the root of both vertices
            int rootSrc = find(current.src);
            int rootDest = find(current.dest);

            // STEP 4: If they have different roots, add this edge
            // (Adding edge won't create a cycle)
            if (rootSrc != rootDest) {
                System.out.println(current.src + " -- " + current.dest + "  (Weight: " + current.weight + ")");

                totalWeight += current.weight;
                edgeCount++;

                // STEP 5: Union - merge the two sets
                union(rootSrc, rootDest);
            }
            // If roots are same, skip (would create cycle)
        }

        System.out.println("\nTotal MST Weight: " + totalWeight);
    }

    // Find operation: Find the root of a vertex
    static int find(int vertex) {
        // If vertex is not its own parent, keep going up
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent[vertex]); // Path compression
        }
        return parent[vertex];
    }

    // Union operation: Merge two sets
    static void union(int root1, int root2) {
        parent[root1] = root2;
    }
}
