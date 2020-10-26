public class Main {

    public static void main(String[] args) {

    }

    public static String splitTextInToWords(String text) {
        return text.replaceAll("[0-9,.;-]", " ").trim().replaceAll("\\s+", " ").replaceAll("[\\s]", "\r\n");
    }

}