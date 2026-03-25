import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class UserHandler extends DefaultHandler {

    private String searchId;
    private boolean found = false;
    private boolean isUserid = false;
    private boolean isName = false;
    private boolean isGender = false;
    private boolean isMarks = false;

    private String currentId = "";

    public UserHandler(String searchId) {
        this.searchId = searchId;
    }

    public boolean isFound() {
        return found;
    }

    @Override
    public void startElement(String url, String localName,String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("userid"))
            isUserid = true;
        else if (qName.equalsIgnoreCase("name"))
            isName = true;
        else if (qName.equalsIgnoreCase("gender"))
            isGender = true;
        else if (qName.equalsIgnoreCase("marks"))
            isMarks = true;
    }

    @Override
    public void characters(char ch[], int start, int length) {

        if (isUserid) {
            currentId = new String(ch, start, length);
            isUserid = false;

            if (currentId.equals(searchId)) {
                found = true;
                System.out.println("\nUser Details:");
                System.out.println("User ID : " + currentId);
            }
        } else if (found && isName) {
            System.out.println("Name    : " + new String(ch, start, length));
            isName = false;
        } else if (found && isGender) {
            System.out.println("Gender  : " + new String(ch, start, length));
            isGender = false;
        } else if (found && isMarks) {
            System.out.println("Marks   : " + new String(ch, start, length));
            isMarks = false;
        }
    }

    public void endElement(String url, String localName, String qName) {
        if (qName.equalsIgnoreCase("student")) {
            System.out.println("...........................");
        }
    }
}