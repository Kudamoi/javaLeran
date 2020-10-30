import java.util.*;

public class Main {
    public static void main(String[] args) {
        generatorCarNumber();
        System.out.println(carNumber.size());

        String searchCarNumber = new Scanner(System.in).nextLine();
        search(searchCarNumber);
        binarySearch(searchCarNumber);
        hashSearch(searchCarNumber);
        treeSearch(searchCarNumber);
    }

    static ArrayList<String> carNumber = new ArrayList<>();
    static HashSet<String> hashCarNumber = new HashSet<>();
    static TreeSet<String> treeCarNumber = new TreeSet<>();
    static final String[] russianLetter = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};

    //A B C E H K M O P T X Y
    public static void generatorCarNumber() {
        int count = 0;
        for (String letter : russianLetter) {
            for (int blat = 0; blat <= 999; blat += 111) {
                for (String letter1 : russianLetter) {
                    for (String letter2 : russianLetter) {
                        for (int region = 1; region <= 197; region++) {
                            carNumber.add(letter + (blat == 0 ? "000" : blat) + letter1 + letter2 + region);
                            count += 1;
                        }
                    }
                }
            }
        }
        Collections.sort(carNumber);
        System.out.println("Сгенерировано номеров " + count);

        hashCarNumber.addAll(carNumber);
        treeCarNumber.addAll(carNumber);
    }



    public static void search(String input) {
        long start = System.nanoTime();
        System.out.print("Поиск перебором: номер " + (carNumber.contains(input)?"найден":"не найден") + ", поиск занял ");
        long duration = System.nanoTime() - start;
        System.out.println(duration + "нс");
    }

    public static void binarySearch(String input) {
        long start = System.nanoTime();
        System.out.print("Бинарный поиск: номер " + (Collections.binarySearch(carNumber, input) != -1 ? "найден":"не найден") + ", поиск занял ");
        long duration = System.nanoTime() - start;
        System.out.println(duration + "нс");
    }

    public static void hashSearch(String input) {
        long start = System.nanoTime();
        System.out.print("Поиск в HashSet: номер " + (hashCarNumber.contains(input)? "найден":"не найден") + ", поиск занял ");
        long duration = System.nanoTime() - start;
        System.out.println(duration + "нс");

        boolean check = false;
        start = System.nanoTime();
        for (String item : hashCarNumber)
            if (item.equals(input)) {
                check = true;
                break;
            }
        duration = System.nanoTime() - start;
        System.out.println("Поиск в HashSet перебором: номер " + (check? "найден":"не найден") + ", поиск занял " + duration);
    }

    public static void treeSearch(String input) {
        long start = System.nanoTime();
        System.out.print("Поиск в TreeSet: номер " + (treeCarNumber.contains(input)? "найден":"не найден") + ", поиск занял ");
        long duration = System.nanoTime() - start;
        System.out.println(duration + "нс");

        boolean check = false;
        start = System.nanoTime();
        for (String item : treeCarNumber)
            if (item.equals(input)) {
                check = true;
                break;
            }
        duration = System.nanoTime() - start;
        System.out.println("Поиск в TreeSet перебором: номер " + (check? "найден":"не найден") + ", поиск занял " + duration);
    }
}
