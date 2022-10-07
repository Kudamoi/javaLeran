import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class XMLHandler extends DefaultHandler {

    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private HashMap<Voter, Integer> voterCounts;

    public XMLHandler() {
        voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter") && voter == null) {
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDay);
            } else if (qName.equals("visit") && voter != null) {
                int count = voterCounts.getOrDefault(voter, 0);
                voterCounts.put(voter, count + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public ArrayList<String> generateInsertQuery() {
        StringBuilder query = new StringBuilder();

        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        Set<Voter> keyVoters = voterCounts.keySet();
        ArrayList<String> queryValuesList = new ArrayList<>();

        for (Voter voter : keyVoters) {

            if (query.length() > 3000000) {
                queryValuesList.add(query.toString());
                query.setLength(0);
            }

            query.append((query.length() == 0 ? "" : ",") + "('" + voter.getName() + "', '" + dayFormat.format(voter.getBirthDay()) + "', 1)");
        }

        return queryValuesList;
    }
}
