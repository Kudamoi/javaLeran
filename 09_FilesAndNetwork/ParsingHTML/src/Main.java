import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    static final String url = "https://lenta.ru";
    public static void main(String[] args) {
        download(url);
        parsing();
    }

    static void parsing() {
        String html = parseFile("data\\file.html");

        Document document = Jsoup.parse(html);
        Elements elements = document.select("img");
        elements.forEach(element -> {
            downloadFile(element.attr("src"));
        });
    }

    static void downloadFile(String url) {
        new File("images").mkdirs();
        try {
            BufferedImage image;
            image = ImageIO.read(new URL(url));
            ImageIO.write(image, url.substring(url.lastIndexOf(".") + 1), new File("images\\" + url.substring(url.lastIndexOf("/") + 1)));
            System.out.println(url.substring(url.lastIndexOf("/") + 1));
        }catch (Exception e) {
            System.out.println(e.getMessage() + " не является картинкой!");
        }
    }

    static void download(String url) {
        try {
            URL link = new URL(url);
            InputStreamReader inputStreamReader = new InputStreamReader(link.openStream());
            new File("data").mkdirs();
            File create = new File("data\\file.html");
            create.createNewFile();
            Writer writer = new FileWriter("data\\file.html");
            int line;
            while ((line = inputStreamReader.read()) != -1)
                writer.write(line);
            writer.flush();
            writer.close();
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String parseFile(String path) {
        StringBuilder result = new StringBuilder();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> result.append(line).append("\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
