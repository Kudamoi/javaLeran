import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseLinks extends RecursiveTask<Node> {
    public static HashSet<String> activeLink = new HashSet<>();
    private final Node node;
    public ParseLinks(Node node) {
        this.node = node;
    }

    @Override
    protected Node compute() {
        try {

            if (node.getCurrentLink().endsWith("/")) {
                Document document = Jsoup.connect(node.getCurrentLink()).get();

                Elements tegA = document.select("a");

                List<ParseLinks> taskList = new ArrayList<>();

                Matcher matcher;
                String link;

                for (Element element : tegA) {
                    link = element.attr("href").substring(0, element.attr("href").contains("?") ? element.attr("href").indexOf("?") : element.attr("href").length()).trim();

                    if (!link.startsWith("mailto:") && !link.startsWith("tel:") && !link.startsWith("tg:") && !link.startsWith("wa:")) {

                        matcher = Pattern.compile("#").matcher(link);
                        if (matcher.find()) {
                            link = link.substring(0, matcher.start());
                        }

                        Thread.sleep(150);

                        matcher = Pattern.compile("http(s?)://").matcher(link);

                        if (matcher.find()) {
                            matcher = Pattern.compile("http(s?)://" + node.getHost()).matcher(link);
                            if (matcher.find()) {

                                matcher = Pattern.compile("http(s?)://" + node.getHost() + "(.*).([^/]*$)").matcher(link);

                                if (!matcher.find()) {
                                    if (!link.endsWith("/"))
                                        link += "/";
                                }

                                link = node.getProtocol() + node.getHost() + link.substring((node.getProtocol() + node.getHost()).length()).replaceAll("(/+)", "/");

                                if (!ParseLinks.activeLink.contains(link)) {
                                    ParseLinks.activeLink.add(link);
                                    ParseLinks task = new ParseLinks(new Node(link));
                                    task.fork();
                                    taskList.add(task);
                                }

                            }
                        } else {
                            matcher = Pattern.compile("(.*[.][^/]*$)").matcher(link);
                            if (!matcher.find()) {
                                if (!link.endsWith("/"))
                                    link += "/";
                            }

                            if (link.startsWith("/")) {
                                link = node.getProtocol() + node.getHost() + link.replaceAll("(/+)", "/");
                            } else {
                                link = node.getCurrentLink() + link.substring(1).replaceAll("(/+)", "/");
                            }


                            if (!ParseLinks.activeLink.contains(link)) {
                                ParseLinks.activeLink.add(link);
                                ParseLinks task = new ParseLinks(new Node(link));
                                task.fork();
                                taskList.add(task);
                            }

                        }
                    }
                }

                for (ParseLinks task : taskList) {
                    node.addLink(task.join());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return node;
    }
}
