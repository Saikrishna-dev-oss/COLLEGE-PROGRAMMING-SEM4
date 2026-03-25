import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DParser {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter User ID: ");
            String uid = sc.next();

            File inputFile = new File("input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("student");
            boolean found = false;

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String id = eElement.getElementsByTagName("userid").item(0).getTextContent();

                    if (id.equals(uid)) {
                        System.out.println("\nUser Details:");
                        System.out.println("User ID : " + id);
                        System.out.println("Name    : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                        System.out.println("Gender  : " + eElement.getElementsByTagName("gender").item(0).getTextContent());
                        System.out.println("Marks   : " + eElement.getElementsByTagName("marks").item(0).getTextContent());
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("User ID " + uid + " not found.");
            }

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
