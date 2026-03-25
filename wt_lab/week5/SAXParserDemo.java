import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserDemo {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter User ID: ");
            String uid = sc.next();

            File inputFile = new File("input.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            UserHandler handler = new UserHandler(uid);
            saxParser.parse(inputFile, handler);

            if (!handler.isFound()) {
                System.out.println("User ID " + uid + " not found.");
            }

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
