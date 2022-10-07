import java.io.FileOutputStream;

public class Enumerator extends Thread {

    private final char[] letters;
    private final int regionCode;
    private final String path;

    public Enumerator(int regionCode, char[] letters, ThreadGroup group) {
        super(group, String.valueOf(regionCode));

        this.letters = letters;
        this.regionCode = regionCode;

        if(regionCode % 4 == 0) {
            this.path = "res/numbersForRegion" + regionCode + ".txt";
        } else {
            this.path = "C:/res/numbersForRegion" + regionCode + ".txt";
        }
    }

    @Override
    public void run() {
        try {
            FileOutputStream writer = new FileOutputStream(this.path);

            StringBuilder stringBuilder = new StringBuilder();

            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : this.letters) {
                    for (char secondLetter : this.letters) {
                        for (char thirdLetter : this.letters) {
                            stringBuilder.append(firstLetter).append(padNumber(number, 3)).append(secondLetter).append(thirdLetter).append(padNumber(this.regionCode, 2)).append('\n');
                        }
                    }
                }

                writer.write(stringBuilder.toString().getBytes());
                stringBuilder.delete(0, stringBuilder.length());
            }

            writer.flush();
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.append('0').append(numberStr);
        }

        return numberStr.toString();
    }
}
