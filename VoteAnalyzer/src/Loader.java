import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Loader {
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        String fileName = "res/data-1572M.xml";

        long start = System.currentTimeMillis();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        long parseTime = System.currentTimeMillis() - start;
        DBConnection.executeMultiInsert(handler.generateInsertQuery());
        DBConnection.printVoterCounts();

        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.println("\r\nВремя парсинга файла: " + parseTime + " мс");
        System.out.println("\r\nИспользовано памяти:\r\n\t " + (usage) + " б \r\n\t " + (usage / 1024) + " Кб \r\n\t " + (usage / 1024 / 1024) + " Мб ");
    }
}