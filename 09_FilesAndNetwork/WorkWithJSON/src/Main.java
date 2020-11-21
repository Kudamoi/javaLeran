import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static final String path = "https://www.moscowmap.ru/metro.html#lines";

    public static void main(String[] args) {
        download();
        parsing();
        readStation();

        writeConnection();
        readConnection();
    }

    static void download() {
        try {
            InputStreamReader reader = new InputStreamReader(new URL(Main.path).openStream());
            new File("data").mkdirs();
            Writer writer = new FileWriter("data\\file.html");
            int element;
            while ((element = reader.read()) != -1)
                writer.write(element);
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void readStation() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        Map<String, Integer> countLines = new HashMap<>();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("data\\map.json"));

            JSONArray arr = (JSONArray) jsonObject.get("stations");
            arr.forEach(element -> {
                if (countLines.containsKey(((JSONObject) element).get("stationLineNumber").toString()))
                    countLines.put(((JSONObject) element).get("stationLineNumber").toString(), countLines.get(((JSONObject) element).get("stationLineNumber").toString()) + 1);
                else countLines.put(((JSONObject) element).get("stationLineNumber").toString(), 1);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String key :countLines.keySet())
            System.out.println("На линии номер " + key + " расположено " + countLines.get(key) + " станций!");

    }

    static void parsing() {

        String html = parseFile();
        JSONObject jsonObject = new JSONObject();
        JSONArray stations = new JSONArray();
        JSONArray lines = new JSONArray();

        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByAttributeValueContaining("class", "js-metro-line");

        elements.forEach(element ->  {
            try {
                JSONObject line = new JSONObject();
                line.put("number", element.attr("data-line"));
                line.put("name", element.text());
                lines.add(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        elements = doc.getElementsByAttributeValueContaining("class", "js-metro-station");
        elements.forEach(element ->{
            Elements name = element.getElementsByAttributeValueContaining("class", "name");
            name.forEach(text -> {
                JSONObject station = new JSONObject();
                try {
                    station.put("stationName", text.text());
                    station.put("stationLineNumber", element.attr("data-line"));
                    stations.add(station);
                } catch (Exception e) {

                }
            });
        });

        try (FileWriter writer = new FileWriter("data\\map.json")){
            jsonObject.put("stations", stations);
            jsonObject.put("lines", lines);
            writer.write(jsonObject.toJSONString());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String parseFile() {
        StringBuilder result = new StringBuilder();

        try {
            List<String> lines = Files.readAllLines(Paths.get("data\\file.html"));
            lines.forEach(line -> result.append(line).append("\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }


    static void writeConnection() {
        String html = parseFile();

        Document doc = Jsoup.parse(html);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("data\\map.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray connections = new JSONArray();

        Elements elements = doc.getElementsByAttributeValueMatching("class", "js-metro-stations");

        elements.forEach(element -> {
            String lineNumberFrom = element.attr("data-line");
            element.select("a").forEach(el -> {

                String nameFrom = null;
                String nameTo = null;

                String lineNumberTo = null;
                if (el.toString().matches(".+t-icon-metroln.+")) {

                    JSONObject connectFrom = new JSONObject();
                    JSONObject connectTo = new JSONObject();
                    JSONArray connect = new JSONArray();

                    nameFrom = el.getElementsByAttributeValueContaining("class", "name").text();
                    nameTo = el.getElementsByAttributeValueMatching("title", "переход.+").attr("title")
                            .substring(el.getElementsByAttributeValueMatching("title", "переход.+").attr("title").indexOf("«") + 1,
                                    el.getElementsByAttributeValueMatching("title", "переход.+").attr("title").lastIndexOf("»"));
                    lineNumberTo = el.getElementsByAttributeValueMatching("title", "переход.+").attr("class")
                            .substring(el.getElementsByAttributeValueMatching("title", "переход.+").attr("class").indexOf("ln-") + 3);

                    connectFrom.put("line", lineNumberFrom);
                    connectFrom.put("name", nameFrom);
                    connectTo.put("line", lineNumberTo);
                    connectTo.put("name", nameTo);

                    connect.add(connectFrom);
                    connect.add(connectTo);

                    boolean checker = false;

                    for (Object o : connections) {
                        if (o.getClass().equals(JSONArray.class))
                            if (((JSONArray) o).contains(connectFrom) && ((JSONArray) o).contains(connectTo))
                                checker = true;
                    }
                    if (!checker) {
                        connections.add(connect);
                    }
                }
            });
        });
        jsonObject.put("connections", connections);
        try (FileWriter fileWriter = new FileWriter("data\\map.json")){
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void readConnection() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("data\\map.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonArray = (JSONArray) jsonObject.get("connections");
        System.out.println("Количество переходов на сайте Московского метро (" + Main.path + ") равно " + jsonArray.size());
    }
}
