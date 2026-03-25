//LAB3_26JAN12/FibonacciRecursive.java :: Recursive function to calculate nth Fibonacci number
package LAB3_26JAN12;
import java.util.Scanner;

public class FibonacciRecursive
{
    static int fib(int n)
    {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int terms = sc.nextInt();

        System.out.println("Fibonacci Series:");
        for (int i = 0; i < terms; i++) {
            System.out.print(fib(i) + " ");
        }
        sc.close();
    }
}
