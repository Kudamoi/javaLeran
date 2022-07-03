
public class Loader {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int regions = 100;
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        ThreadGroup group = new ThreadGroup("name");

        for (int regionCode = 1; regionCode < regions; regionCode++) {
            Enumerator regionWorker = new Enumerator(regionCode, letters, group);
            regionWorker.start();
        }

        while (group.activeCount() != 0) {

        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
