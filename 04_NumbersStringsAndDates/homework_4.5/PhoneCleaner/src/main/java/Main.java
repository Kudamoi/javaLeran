import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            String clear = input.replaceAll("[^0-9]", "");
            StringBuilder clearTelephone = new StringBuilder(clear);
            switch (clear.length()) {
                case 10:
                    clearTelephone.replace(0, 0, "7");
                    break;
                case 11:
                    if (clearTelephone.indexOf("7") == 0 || clearTelephone.indexOf("8") == 0)
                        clearTelephone.replace(0, 1, "7");
                    else
                        clearTelephone.delete(0, clearTelephone.length()).append("Неверный формат номера");
                    break;
                default:
                    clearTelephone.delete(0, clearTelephone.length()).append("Неверный формат номера");
            }
            System.out.println(clearTelephone.toString());
            break;
        }
    }

}
