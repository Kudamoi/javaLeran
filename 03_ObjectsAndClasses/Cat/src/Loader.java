
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();

        System.out.println(cat.getCount());

        System.out.println(cat.getWeight());

        cat.pee(9999.);
        System.out.println(cat.getWeight());
        System.out.println(cat.getStatus());
        cat.meow();

        cat.drink(2000.);
        System.out.println(cat.getWeight());
        System.out.println(cat.getStatus());

        cat1.drink(2000.);
        System.out.println(Cat.getCount());
        cat2.pee(10000.);
        System.out.println(Cat.getCount());
        cat3.meow();

        System.out.println(Cat.getCount());
    }
}