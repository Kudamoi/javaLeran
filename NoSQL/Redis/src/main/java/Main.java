import jodd.util.ThreadUtil;

public class Main {
    public static void main(String[] args) {

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