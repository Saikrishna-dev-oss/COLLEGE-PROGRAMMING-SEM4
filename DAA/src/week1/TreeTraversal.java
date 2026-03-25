package week1;

import java.util.Scanner;
import java.util.Stack;

class TreeNode {
    char val;
    TreeNode left, right;

    TreeNode(char v) {
        val = v;
        left = right = null;
    }
}

public class TreeTraversal {

    // BUILD TREE USING x FOR NULL
    TreeNode readTree(Scanner sc) {
        System.out.print("Enter node value (x for NULL): ");
        char data = sc.next().charAt(0);

        if (data == 'x')
            return null;

        TreeNode node = new TreeNode(data);

        System.out.println("Enter left child of " + data);
        node.left = readTree(sc);

        System.out.println("Enter right child of " + data);
        node.right = readTree(sc);

        return node;
    }

    // INORDER TRAVERSAL (NON-RECURSIVE)
    void inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;

        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            temp = stack.pop();
            System.out.print(temp.val + " ");
            temp = temp.right;
        }
    }

    // POSTORDER TRAVERSAL (NON-RECURSIVE)
    void postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        TreeNode prev = null;

        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            TreeNode curr = stack.peek();

            if (curr.right == null || curr.right == prev) {
                stack.pop();
                System.out.print(curr.val + " ");
                prev = curr;
                temp = null;
            } else {
                temp = curr.right;
            }
        }
    }

    // MAIN
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeTraversal tree = new TreeTraversal();

        System.out.println("Build your binary tree:");
        TreeNode root = tree.readTree(sc);

        System.out.println("\nInorder Traversal (Non-Recursive):");
        tree.inorderTraversal(root);

        System.out.println("\n\nPostorder Traversal (Non-Recursive):");
        tree.postorderTraversal(root);

        sc.close();
    }
}