package LAB3_26JAN12;

import java.util.Scanner;
import java.util.Stack;

public class DFS
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        int[][] adj = new int[n][n];
    
        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
            {
                adj[i][j] = sc.nextInt();
            }
        }
    
        System.out.print("Enter start vertex: ");
        int start = sc.nextInt();
    
        System.out.println("DFS traversal:");
        dfs(start, adj, n);
    
        sc.close();
    }
    
    static void dfs(int start, int[][] adj, int n)
    {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];

        stack.push(start);
        visited[start] = true;
        System.out.print(start + " ");

        while (!stack.isEmpty())
            {
            int v = stack.peek();
            int i;
            for (i = 0; i < n; i++) {
                if (adj[v][i] == 1 && !visited[i])
                {
                    stack.push(i);
                    visited[i] = true;
                    System.out.print(i + " ");
                    break;
                }
            }
            if (i == n) stack.pop();
        }
    }

}
