public class Main {
    public static void main(String[] args) {
        for (int i = 65; i <= 90; i++) {
            System.out.print("Символ - ");
            System.out.print(Character.toChars(i));
            System.out.println(" Код - " + i);
        }
        System.out.println();
        for (int i = 97; i <= 122; i++) {
            System.out.print("Символ - ");
            System.out.print(Character.toChars(i));
            System.out.println(" Код - " + i);
        }

    }
}
