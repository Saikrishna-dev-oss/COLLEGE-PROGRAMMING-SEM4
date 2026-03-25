package LAB3_26JAN12;

import java.util.*;

class Power {
    static int count = 0;

    static int power(int a, int n) {
        if (n == 0) return 1;
        if (n == 1) return a;
        int temp = power(a, n/2);
        count++;
        if (n % 2 == 0) return temp * temp;
        else {
            count++;
            return temp * temp * a;
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter base (a): ");
        int a = sc.nextInt();
        System.out.print("Enter exponent (n): ");
        int n = sc.nextInt();
        sc.close();
        int result = power(a, n);
        System.out.println(a + "^" + n + " = " + result);
        System.out.println("Total multiplications = " + count);
    }
}
