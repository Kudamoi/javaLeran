import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            String[] FIO = input.split(" ");

            if (FIO.length != 3 || input.matches(".+\\d.+"))
                System.out.println("Введенная строка не является ФИО");
            else {
                System.out.println("Фамилия: " + FIO[0]);
                System.out.println("Имя: " + FIO[1]);
                System.out.println("Отчество: " + FIO[2]);
            }

            break;
        }
    }

}