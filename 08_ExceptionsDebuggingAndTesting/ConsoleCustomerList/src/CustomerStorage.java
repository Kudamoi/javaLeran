import java.util.HashMap;
import java.util.stream.Stream;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        String name = "";
        if (components.length != 4)
            throw new IllegalArgumentException("Не верно введено количество аргументов.");
        else {
            System.out.println(components[0] + " " + components[1] + " " + components[2] + " " + components[3]);
            if (components[0].matches("[а-яА-Я]+") && components[1].matches("[а-яА-Я]+"))
                name = components[0] + " " + components[1];
            else throw new IllegalArgumentException("Не верно введены имя и/или отчество");
            if (components[2].matches(".+[@].+") && components[3].matches("[+]?[78]?[0-9]{11}"))
                storage.put(name, new Customer(name, components[3], components[2]));
            else throw new IllegalArgumentException("Не верно введен номер и/или email");
        }
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}