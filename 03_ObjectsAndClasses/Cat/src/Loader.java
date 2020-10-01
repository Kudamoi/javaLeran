
public class Loader
{
    public static void main(String[] args)
    {
        Cat vasya = getKitten();
        Cat murka = getKitten();
        Cat barsik = getKitten();

        System.out.println(vasya.getWeight());
    }

    private static Cat getKitten() {
        Cat cat = new Cat(1100.00);
        return cat;
    }
}