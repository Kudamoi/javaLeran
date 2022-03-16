import java.io.File;

public class Main {

    public static void main(String[] args) {
        String srcFolder = "D:\\folder1";
        String dstFolder = "D:\\folder2";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int cores = Runtime.getRuntime().availableProcessors();
        int fileInThread = files.length / cores;
        int lostFile = files.length - fileInThread * cores;

        int startCopyArray = 0;
        for (int i = 0; i < cores; i++) {

            int countFileThread = fileInThread;
            if (lostFile > i) {
                countFileThread++;
            }

            if(countFileThread == 0) {
                break;
            }

            File[] fileThread = new File[countFileThread];
            System.arraycopy(files, startCopyArray, fileThread, 0, countFileThread);
            startCopyArray += countFileThread;

            Resizer resizer = new Resizer(fileThread, dstFolder, 300, 300);
            new Thread(resizer).start();
        }

    }
}
