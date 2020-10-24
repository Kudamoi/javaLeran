public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    //TODO: напишите ваш код, результат вывести в консоль
    String first = text.substring(text.indexOf(" ") + 1);
    String sub = text.substring(text.indexOf("-") + 1, text.lastIndexOf("руб")).trim();

    System.out.println(Integer.parseInt(first.substring(first.indexOf(" "), first.indexOf("руб")).trim()) + (int) Integer.parseInt(sub.substring(0, sub.indexOf("руб")).trim()) + Integer.parseInt(sub.substring(sub.lastIndexOf(" ")).trim()));
  }

}