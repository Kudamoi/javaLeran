import java.util.ArrayList;
import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {
        affairList();
    }

    static ArrayList<String> myAffair = new ArrayList<>();

    public static void affairList() {
        while (true) {
            String input = new Scanner(System.in).nextLine();
            String command = null;

            try {
                command = input.trim().substring(0, input.trim().indexOf(" "));
            } catch (Exception e) {

                if (input.equals("LIST"))
                    for (int i = 0; i < myAffair.size(); i++)
                        System.out.println(i + " - " + myAffair.get(i));
                else System.out.println("Неверно введенная команда! Попробуйте снова");

                affairList();
            }

            String[] forSearchIndex = input.split("\\s+");

            if (command.equals("ADD"))
                if (!forSearchIndex[1].matches("\\d+") || Integer.parseInt(forSearchIndex[1]) == 0)
                    myAffair.add(input.substring(command.length() + 1));
                else if (forSearchIndex[1].matches("\\d+") && Integer.parseInt(forSearchIndex[1]) < myAffair.size())
                    myAffair.add(Integer.parseInt(forSearchIndex[1]), input.substring(command.length() + forSearchIndex[1].length() + 2));

            if (command.equals("EDIT"))
                if (forSearchIndex[1].matches("\\d+") && Integer.parseInt(forSearchIndex[1]) < myAffair.size()) {
                    myAffair.remove(Integer.parseInt(forSearchIndex[1]));
                    myAffair.add(Integer.parseInt(forSearchIndex[1]), input.substring(command.length() + forSearchIndex[1].length() + 2));
                } else {
                    System.out.println("Неверно введенная команда! Попробуйте снова");
                }

            if (command.equals("DELETE"))
                if (forSearchIndex[1].matches("\\d+") && Integer.parseInt(forSearchIndex[1]) < myAffair.size())
                    myAffair.remove(Integer.parseInt(forSearchIndex[1]));
                else {
                    System.out.println("Неверно введенная команда! Попробуйте снова");
                }
        }
    }
}
