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
        boolean result;
        long start = System.nanoTime();
        result = carNumber.contains(input);
        long duration = System.nanoTime() - start;
        System.out.println("Поиск перебором: номер " + (result?"найден":"не найден") + ", поиск занял " + duration + "нс");
    }

    public static void binarySearch(String input) {
        boolean result;
        long start = System.nanoTime();
        result = Collections.binarySearch(carNumber, input) != -1;
        long duration = System.nanoTime() - start;
        System.out.println("Бинарный поиск: номер " + (result?"найден":"не найден") + ", поиск занял " + duration + "нс");
    }

    public static void hashSearch(String input) {
        boolean result;
        long start = System.nanoTime();
        result = hashCarNumber.contains(input);
        long duration = System.nanoTime() - start;
        System.out.println("Поиск в HashSet: номер" + (result?"найден":"не найден") + ", поиск занял " + duration + "нс");
    }

    public static void treeSearch(String input) {
        boolean result;
        long start = System.nanoTime();
        result = treeCarNumber.contains(input);
        long duration = System.nanoTime() - start;
        System.out.println("Поиск в TreeSet: номер" + (result?"найден":"не найден") + ", поиск занял " + duration + "нс");

    }
}
