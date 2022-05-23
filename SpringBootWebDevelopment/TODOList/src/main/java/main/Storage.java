package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static HashMap<Integer, Case> storage = new HashMap<>();
    private static int counter = 0;

    public static List<Case> getAllCases() {
        return new ArrayList<>(storage.values());
    }

    public static Case add(Case cas) {
        cas.setId(++counter);
        storage.put(counter, cas);
        return cas;
    }

    public static int delete(int key) {
        if(storage.containsKey(key)) {
            storage.remove(key);
            return key;
        }
        return -1;
    }
}
