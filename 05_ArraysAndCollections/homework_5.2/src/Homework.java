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

            String[] forSearchCommand = input.split("\\s+");
            String command = forSearchCommand[0];
            if (command.equals("LIST")) {
                for (int index = 0; index < myAffair.size(); index++)
                    System.out.println(index + " - " + myAffair.get(index));
                continue;
            }
            if (command.equals("DELETE")) {
                try {
                    if (forSearchCommand[1].matches("\\d+")) {
                        myAffair.remove(Integer.parseInt(forSearchCommand[1]));
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Команда введена не правильно! Повторите попытку");
                    continue;
                }
            }
            if (command.equals("EDIT")) {
                try {
                    if (forSearchCommand[1].matches("\\d+")) {
                        myAffair.add(Integer.parseInt(forSearchCommand[1]), input.substring(command.length() + forSearchCommand[1].length() + 2));
                        myAffair.remove(Integer.parseInt(forSearchCommand[1]) + 1);
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Команда введена не правильно! Повторите попытку");
                    continue;
                }
            }
            if (command.equals("ADD")) {
                try {
                    if (forSearchCommand[1].matches("\\d+") && Integer.parseInt(forSearchCommand[1]) > myAffair.size())
                        myAffair.add(input.substring(command.length() + forSearchCommand[1].length() + 2));
                    else if (forSearchCommand[1].matches("\\d+"))
                        myAffair.add(Integer.parseInt(forSearchCommand[1]), input.substring(command.length() + forSearchCommand[1].length() + 2));
                    else
                        myAffair.add(input.substring(command.length() + 1));

                    continue;
                } catch (Exception e) {
                    System.out.println("Команда введена не правильно! Повторите попытку");
                    continue;
                }
            }
            System.out.println("Команда введена не правильно! Повторите попытку");
        }
    }
}
