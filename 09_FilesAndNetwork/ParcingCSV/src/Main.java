import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    static final String path = "D:\\SkillBox\\Git\\java_basics\\09_FilesAndNetwork\\files\\movementList.csv";

    public static void main(String[] args) {
        parsing(path);
    }

    static void parsing(String path) {
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Path.of(path));
        } catch (Exception e) {
            e.getMessage();
        }

        double income = 0;
        double expenditure = 0;

        HashMap<String, Double> expenditureOrganization = new HashMap<>();

        for (String str : list) {
            String[] arr = str.replaceAll("[\"]", "").split(",");
            String[] operation = arr[5].split("\\s\\s\\s+");

            if (arr[6].matches("\\W+"))
                continue;
            if (arr.length == 9)
                arr[7] = arr[7] + "." + arr[8];

            income += Double.parseDouble(arr[6]);
            expenditure += Double.parseDouble(arr[7]);

            if (expenditureOrganization.containsKey(operation[1]))
                expenditureOrganization.put(operation[1], expenditureOrganization.get(operation[1]) + Double.parseDouble(arr[7]));
            else expenditureOrganization.put(operation[1], Double.parseDouble(arr[7]));
        }

        System.out.println("Сумма расходов: " + expenditure + " руб.");
        System.out.println("Сумма доходов: " + income + " руб.\n");

        System.out.println("Суммы расходов по организациям:");
    for (String key : expenditureOrganization.keySet())
        System.out.format("%-50s %.2f руб.\n",key, expenditureOrganization.get(key));
    }
}
