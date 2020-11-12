import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            String path = new Scanner(System.in).nextLine();
        convertByte(checkFolder(path), path);
        }
    }

    static long checkFolder(String path) {
        File files = new File(path);
        if (!files.exists())
            return -1;
        long size = 0;
        if (files.listFiles() != null)
        for (File file : files.listFiles()) {
            if (file.isFile())
                size += file.length();
            else if (file.isDirectory())
                size += checkFolder(file.getPath());
        }
        return size;
    }

    static void convertByte(long sizeFolder, String path) {
        if (sizeFolder == -1) {
            System.out.println("Такой папки нет!");
            return;
        }
        if (sizeFolder < 1024)
            System.out.println("Размер папки " + path + " составляет " + sizeFolder + " байт");
        else {
            double convert = sizeFolder / (double) 1024;
            if (convert < 1024)
                System.out.format("Размер папки %s составляет %.2f КБ \n", path, convert);
            else {
                convert /= 1024;
                if (convert < 1024)
                    System.out.format("Размер папки %s составляет %.2f МБ \n", path, convert);
                else {
                    convert /= 1024;
                    if (convert < 1024)
                        System.out.format("Размер папки %s составляет %.2f ГБ \n", path, convert);
                    else {
                        convert /= 1024;
                        System.out.format("Размер папки %s составляет %.2f ТБ \n", path, convert);
                    }
                }
            }
        }
    }
}
