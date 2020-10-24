import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      for (int i = 0; i < input.length(); i++) {
        if (input.charAt(i) == ' ' || input.charAt(i) == '-')
          continue;
        if (!Character.isLetter(input.charAt(i))) {
          System.out.println("Введенная строка не является ФИО");
          return;
        }
      }
      String firstName = "", secondName = "", thirdName = "";
      try {
        firstName = input.substring(0, input.indexOf(" "));
        String firstPath = input.substring(firstName.length()).trim();
        secondName = firstPath.substring(0, firstPath.indexOf(" ")).trim();
        if (firstPath.indexOf(" ") == firstPath.lastIndexOf(" "))
          thirdName = firstPath.substring(firstPath.lastIndexOf(" ")).trim();
        else {
          System.out.println("Введенная строка не является ФИО");
          return;
        }
        System.out.println("Фамилия: " + firstName);
        System.out.println("Имя: " + secondName);
        System.out.println("Отчество: " + thirdName);
      }
      catch (Exception e) {
        System.out.println("Введенная строка не является ФИО");
        return;
      }

      return;
      /*if (input.equals("0")) {
        break;
      }*/
      //TODO:напишите ваш код тут, результат вывести в консоль.
      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
    }
  }

}