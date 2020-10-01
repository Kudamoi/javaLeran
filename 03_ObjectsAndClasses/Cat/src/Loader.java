
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();

        cat.feed(150.0);
        cat.pee(10.);
        cat.pee(20.);
        cat.pee(140.);

        System.out.println(cat.getFeedMass());
    }
}