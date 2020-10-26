public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        while (checkRectangleBracket(text))
            text = text.replace(text.substring(text.indexOf("<"), text.indexOf(">") + 1), placeholder);
        return text;
    }

    public static boolean checkRectangleBracket(String text) {
        boolean leftRectangularBracket = false;
        boolean rightRectangularBracket = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '<')
                leftRectangularBracket = true;
            if (text.charAt(i) == '>')
                rightRectangularBracket = true;
        }
        return leftRectangularBracket == rightRectangularBracket && rightRectangularBracket;
    }
}