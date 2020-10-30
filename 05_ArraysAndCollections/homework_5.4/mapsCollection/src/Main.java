import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        enter();
    }

    static HashMap<String, String> telephoneBook = new HashMap<>();

    public static void enter() {
        while (true) {
            System.out.println("Введите номер телефона или имя с телефонной книги");
            String input = new Scanner(System.in).nextLine();

            if (input.matches("^LIST\\s?+.?+")) {
                TreeMap<String, String> telephoneBookAlphabet = new TreeMap<>(telephoneBook);
                for (String name : telephoneBookAlphabet.keySet()) {
                    System.out.format("Телефон: %-19s Имя: %s%n", telephoneBookAlphabet.get(name), name);
                }
                continue;
            }

            if (input.matches("[+]?7?8?\\s?[(]?\\d\\d\\d[)]?\\s?\\d\\d\\d\\s?[-]?\\d\\d[-]?\\s?\\d\\d"))
                if (telephoneBook.containsValue(input)) {
                    for (String name : telephoneBook.keySet()) {
                        if (telephoneBook.get(name).equals(input)) {
                            System.out.println("Имя: " + name + " Телефон: " + telephoneBook.get(name));
                            break;
                        }
                    }
                    continue;
                } else {
                    System.out.println("Введите имя");
                    String name = new Scanner(System.in).nextLine();
                    boolean checkTelephone = false;
                    for (String checkName : telephoneBook.keySet())
                        if (checkName.equals(name)) {
                            System.out.println("Такой контакт уже существует");
                            System.out.println("Имя: " + checkName + " Телефон: " + telephoneBook.get(checkName));
                            checkTelephone = true;
                            break;
                        }
                    if (!checkTelephone)
                        telephoneBook.put(name, input);
                    continue;
                }
            searchName(input);
        }
    }

    public static void searchName(String input) {
        for (String forSearchName : telephoneBook.keySet())
            if (input.matches(forSearchName)) {
                System.out.println("Имя: " + forSearchName + " Телефон: " + telephoneBook.get(forSearchName));
                return;
            }

        System.out.println("Введите номер телефона");
        String telephone = new Scanner(System.in).nextLine();
        if (telephone.matches("[+]?7?8?\\s?[(]?\\d\\d\\d[)]?\\s?\\d\\d\\d\\s?[-]?\\d\\d[-]?\\s?\\d\\d")) {
            boolean checkTelephone = false;
            for (String forSearchName : telephoneBook.keySet())
                if (telephoneBook.get(forSearchName).equals(telephone)) {
                    System.out.println("Контакт с таким телефоном уже существует");
                    System.out.println("Имя: " + forSearchName + " Телефон: " + telephoneBook.get(forSearchName));
                    checkTelephone = true;
                    break;
                }
            if (!checkTelephone)
                telephoneBook.put(input, telephone);
        } else
            System.out.println("Телефон был введен не верно");
    }
}
