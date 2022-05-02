import java.io.*;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static TreeSet<String> allLinks = new TreeSet<>();

    public static void main(String[] args) {

        Node node = new Node("https://lenta.ru/");
        Node result = new ForkJoinPool().invoke(new ParseLinks(node));

        recursiveLink(result);

        StringBuilder text = new StringBuilder();
        for (String st : allLinks) {
            long count = st.endsWith("/") ? st.chars().filter(ch -> ch == '/').count() - 3 : st.chars().filter(ch -> ch == '/').count() - 2;
            for (long i = 0; i < count; i++) {
                text.append("\t");
            }
            text.append(st).append("\r\n");
        }

        try (FileWriter writer = new FileWriter("data\\sitemap.txt", false)) {
            writer.write(text.toString());

            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void recursiveLink(Node node) {
        String link = node.getCurrentLink();

        Pattern pattern = Pattern.compile("/\\w+[[._-]?\\w+]+");
        Matcher matcher = pattern.matcher(link);

        while (matcher.find()) {
            int end = matcher.end();
            String searched = link.substring(0, end);

            if (searched.matches("[.][^/]+$")) {
                addLink(searched);
            } else {
                searched += "/";
                addLink(searched);
            }
        }

        if (node.getChildLinks().size() > 0) {
            for (Node child : node.getChildLinks()) {
                if (!allLinks.contains(child.getCurrentLink())) {
                    recursiveLink(child);
                }
            }
        }
    }

    private static void addLink(String link) {
        if (!allLinks.contains(link)) {
            System.out.println("Add new link to file: " + link);
            allLinks.add(link);
        }
    }
}
