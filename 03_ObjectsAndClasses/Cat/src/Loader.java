
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();

        System.out.println(cat.getStatus() + " - it's not my cat \n");

        Cat vasya = new Cat();
        Cat murka = new Cat();
        Cat murzik = new Cat();
        Cat rizhik = new Cat();
        Cat sonya = new Cat();
        Cat totoshka = new Cat();
        Cat zefir = new Cat();

        System.out.println(vasya.getWeight() + " - it's start weight vasya");
        System.out.println(murka.getWeight() + " - it's start weight murka");
        System.out.println(murzik.getWeight());
        System.out.println(rizhik.getWeight());
        System.out.println(sonya.getWeight());
        System.out.println(totoshka.getWeight());
        System.out.println(zefir.getWeight() + " - it's start weight zefir\n");

        vasya.feed(10.0);
        murka.feed(15.2);

        System.out.println(vasya.getWeight() + " - it's weight vasya after feed");
        System.out.println(murka.getWeight() + " - it's weight murka after feed\n");

        while (!murzik.getStatus().contains("Exploded"))
            murzik.feed(10.0);
        System.out.println("Murzik " + murzik.getStatus() + " becouse him weight = " + murzik.getWeight() + "\n");

        while (!rizhik.getStatus().equals("Dead"))
            rizhik.meow();
        System.out.println("Rizhik " + rizhik.getStatus() + " becouse him weight = " + rizhik.getWeight());
    }
}