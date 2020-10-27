import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        workWithEmail();
    }

    static HashSet<String> emailList = new HashSet<>();

    public static void workWithEmail() {

        while (true) {
            String input = (new Scanner(System.in)).nextLine();
            String command = null;

            String[] inputArray = input.split(" ");
            command = inputArray[0];

            if (inputArray.length > 2) {
                System.out.println("Команда введена не верно! Попробуйте еще раз");
                workWithEmail();
            }
            switch (command) {
                case "LIST":
                    for (String item : emailList) {
                        System.out.println(item);
                    }
                    break;
                case "ADD":
                    if (inputArray[1].contains("@"))
                        emailList.add(input.substring(command.length() + 1));
                    else System.out.println("В лист может быть добавлена только почта!");
                    break;
                default:
                    System.out.println("Команда введена не верно! Попробуйте еще раз");
            }
        }
    }
}
