import jodd.util.ThreadUtil;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
//        RedissonClient redisson;
//        RKeys rKeys;
//        RScoredSortedSet<String> cities;
//
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//
//        try {
//            redisson = Redisson.create(config);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        assert redisson != null;
//        rKeys = redisson.getKeys();
//        cities = redisson.getScoredSortedSet("cities");
//
//        for (String a : cities) {
//            System.out.println(a + " "  + rKeys.getSlot(a));
//        }


        RedisStorage redis = new RedisStorage();
        redis.init("USERS");

        redis.generateUsers(10);

        int startCounter = 10;
        int counter = startCounter;
        int timer = 1000;

        while (true) {
            ThreadUtil.sleep(timer);

            long probability = Math.round(Math.random() * counter);
            if (probability == 0) {
                redis.userPay();
                counter = startCounter;
            } else {
                counter--;
            }

            redis.showFirstUser();

        }

    }

}