//LAB1_25DEC29/Program.java :: Simple Inheritance Example
package LAB1_25DEC29;

class Krishna
{
    int a = 10;
}
class Naveen extends Krishna
{
    void tem()
    {
        a = 2;
    }
}

public class Program
{
    public static void main(String[] args)
    {
        Krishna t = new Krishna();
        Naveen tr = new Naveen();
        tr.tem();
        System.out.println(t.a);
    }
    
}
