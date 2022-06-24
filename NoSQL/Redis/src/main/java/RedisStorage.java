import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.sql.Timestamp;
import java.util.HashMap;

import static java.lang.System.out;

public class RedisStorage {

    private RedissonClient redisson;
    private RScoredSortedSet<String> registeredUsers;
    private HashMap<Integer, String> map = new HashMap<>();

    void init(String key) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }

        RKeys rKeys = redisson.getKeys();
        registeredUsers = redisson.getScoredSortedSet(key);
        rKeys.delete(key);
    }

    void registerUser(String user, boolean newUser) {
        if(newUser) {
            map.put(registeredUsers.count(Double.NEGATIVE_INFINITY, true, Double.POSITIVE_INFINITY, true), String.valueOf(user));
        }
        registeredUsers.add(System.currentTimeMillis(), String.valueOf(user));
    }


    void showFirstUser() {
        String user = registeredUsers.first();
        out.println("— На главной странице отображен " + user);

        registeredUsers.remove(user);
        this.registerUser(user, false);
    }

    void userPay() {
        int randomUser = randomPay();
        System.out.println("> " + map.get(randomUser) + " оплатил платную услугу");
        registeredUsers.add(0, map.get(randomUser));
    }

    int randomPay() {
        return (int) Math.floor(Math.random() * registeredUsers.count(Double.NEGATIVE_INFINITY, true, Double.POSITIVE_INFINITY, true));
    }

    void generateUsers(int count) {
        for (int i = 0; i < count; i++) {
            registerUser("Пользователь " + i, true);
        }
    }
}