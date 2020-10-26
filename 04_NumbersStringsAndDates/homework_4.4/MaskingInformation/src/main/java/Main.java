public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
        while (checkRectangleBracket(text)){
            text = text.replace(text.substring(text.indexOf("<"), text.indexOf(">") + 1), placeholder);
        }
        System.out.println(text);
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
        if (leftRectangularBracket != rightRectangularBracket || leftRectangularBracket == false && rightRectangularBracket == false)
            return false;
        return true;
    }

}