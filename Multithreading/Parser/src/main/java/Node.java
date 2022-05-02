import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node {
    private HashSet<Node> childLinks;
    private final String currentLink;
    private String host = null;
    private String protocol = null;

    public Node(String link) {
        this.currentLink = link;
        this.childLinks = new HashSet<>();

        Matcher matcher = Pattern.compile("http(s?)://").matcher(link);

        if (matcher.find()) {
            this.protocol = link.substring(matcher.start(), matcher.end());
        } else {
            System.exit(404);
        }

        link = link.replace(this.protocol, "");
        matcher = Pattern.compile("(.*?/)").matcher(link);
        if (matcher.find()) {
            this.host = link.substring(matcher.start(), matcher.end() - 1);
        } else {
            System.exit(404);
        }

    }

    public void addLink(Node link) {
        System.out.println("New Node in recursion: " + link.getCurrentLink());
        this.childLinks.add(link);
    }

    public HashSet<Node> getChildLinks() {
        return this.childLinks != null ? this.childLinks : new HashSet<>();
    }

    public String getCurrentLink() {
        return this.currentLink;
    }

    public String getHost() {
        return this.host;
    }

    public String getProtocol() {
        return this.protocol;
    }
}
