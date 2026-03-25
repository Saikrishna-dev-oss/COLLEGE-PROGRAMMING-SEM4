//LAB1_25DEC29/InputUser.java :: Simple program to take user input and display it
package LAB1_25DEC29;
import java.util.Scanner;

public class InputUser
{
    public static void main(String[] args)
    {
        int tUser;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 'a' value: ");
        tUser = sc.nextInt();
        int tOut = tUser;
        System.out.println("Value of 'a' entered is:"+tOut);
        sc.close();
    }
    
}
