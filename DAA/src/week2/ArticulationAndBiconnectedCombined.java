package week2;
import java.util.*;

public class ArticulationAndBiconnectedCombined {
    // ---------- Global variables ----------
    static int V;
    static int[][] adj;

    static int[] dfn, low, parent;
    static boolean[] visited, ap;
    static int time;

    static Stack<int[]> stack; // for biconnected components
    // ---------- DFS FUNCTION ----------
    static void DFS(int u) {

        visited[u] = true;
        dfn[u] = low[u] = ++time;

        int children = 0;

        for (int v = 0; v < V; v++) {

            if (adj[u][v] == 1) {   // edge exists

                // -------- TREE EDGE --------
                if (!visited[v]) {

                    children++;
                    parent[v] = u;

                    stack.push(new int[]{u, v});

                    DFS(v);

                    // update low value
                    low[u] = Math.min(low[u], low[v]);

                    // -------- ARTICULATION POINT CHECK --------
                    if (parent[u] == -1 && children > 1)
                        ap[u] = true;

                    if (parent[u] != -1 && low[v] >= dfn[u])
                        ap[u] = true;

                    // -------- BICONNECTED COMPONENT --------
                    if (low[v] >= dfn[u]) {
                        System.out.print("Biconnected Component: ");
                        int[] e;
                        do {
                            e = stack.pop();
                            System.out.print("(" + (e[0]+1) + "," + (e[1]+1) + ") ");
                        } while (!(e[0] == u && e[1] == v));
                        System.out.println();
                    }
                }

                // -------- BACK EDGE --------
                else if (v != parent[u] && dfn[v] < dfn[u]) {

                    stack.push(new int[]{u, v});
                    low[u] = Math.min(low[u], dfn[v]);
                }
            }
        }
    }
    // ---------- MAIN ----------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();

        adj = new int[V][V];

        System.out.println("Enter adjacency matrix (0/1):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                adj[i][j] = sc.nextInt();
            }
        }

        dfn = new int[V];
        low = new int[V];
        parent = new int[V];
        visited = new boolean[V];
        ap = new boolean[V];

        Arrays.fill(parent, -1);

        stack = new Stack<>();
        time = 0;

        // Run DFS from vertex 0 (as in PDF)
        DFS(0);

        // ---------- PRINT DFN & LOW ----------
        System.out.println("\nVertex   DFN   LOW");
        for (int i = 0; i < V; i++) {
            System.out.println((i+1) + "        " + dfn[i] + "     " + low[i]);
        }

        // ---------- PRINT ARTICULATION POINTS ----------
        System.out.println("\nArticulation Points:");
        boolean found = false;
        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.println(i+1);
                found = true;
            }
        }

        if (!found)
            System.out.println("None");

        sc.close();
    }
}
