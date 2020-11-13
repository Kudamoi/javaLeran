import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите путь к файлу который нужно перенести");
            String pathFrom = new Scanner(System.in).nextLine();
            System.out.println("Введите путь к файлу в который нужно перенести файл");
            String pathTo = new Scanner(System.in).nextLine();
            copy(pathFrom, pathTo);
        }
    }

    static void copy(String pathFrom, String pathTo) {
        File fileFrom = new File(pathFrom);
        File fileTo = new File(pathTo);

        int index = fileFrom.getPath().lastIndexOf(fileFrom.getName());

        if (!fileFrom.exists() || !fileTo.exists()) {
            System.out.println("Введенного вами пути не существует!");
            return;
        }
        copyDirectories(fileFrom, fileTo, index);
    }

    static void copyDirectories(File fileFrom, File fileTo, int index) {
        if (fileFrom.isFile()) {
            copyFile(fileFrom, fileTo, index);
            return;
        } else new File(fileTo.getPath(), fileFrom.getPath().substring(index)).mkdirs();

        try {
            for (File file : fileFrom.listFiles()) {
                if (file.isDirectory()) {
                    copyDirectories(file, fileTo, index);
                    new File(fileTo.getPath(), file.getPath().substring(index)).mkdirs();
                } else copyFile(file, fileTo, index);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    static void copyFile(File fileFrom, File fileTo, int index) {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            inputStream = new FileInputStream(fileFrom);
            outputStream = new FileOutputStream(fileTo + "\\" + fileFrom.getPath().substring(index));

            int bytes;
            while ((bytes = inputStream.read()) != -1) {
                outputStream.write(bytes);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
