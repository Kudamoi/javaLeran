import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int countBoxes = 0;
        try {
            countBoxes = Integer.parseInt(boxes);
        } catch (Exception e) {
            System.out.println("В числе найден недопустимый знак либо введено не целое число!");
        }


        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t
        int container = (int) Math.ceil((double) countBoxes / 27);
        int cargo = (int) Math.ceil((double) container / 12);
        int cargoIndex = 0, containerIndex = 1, boxesIndex = 1;
        int outCargo = cargo;
        int outContainer = container;

        while (cargo > 0) {
            cargoIndex += 1;
            cargo -= 1;
            System.out.println("Грузовик: " + cargoIndex);
            for (int j = 0; j < 12 && containerIndex <= container; j++, containerIndex++) {
                System.out.println("\tКонтейнер: " + containerIndex);
                for (int i = 0; i < 27 && boxesIndex <= countBoxes; i++, boxesIndex++) {
                    System.out.println("\t\tЯщик: " + boxesIndex);
                }
            }
        }
        System.out.println("Необходимо:");
        System.out.println("грузовиков - " + outCargo + " шт.");
        System.out.println("контейнеров - " + outContainer + " шт.");
        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */
    }

}
