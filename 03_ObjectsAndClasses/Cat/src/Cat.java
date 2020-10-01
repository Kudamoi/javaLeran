import java.util.Objects;

public class Cat
{
    private double originWeight;
    private double weight;

    private double sumFeed;

    public static int count;

    private static final Double maxWeight = 9000.0;
    private static final Double minWeight = 1000.0;
    private static final int eye = 2;

    enum Color { Black, Gray, White, Red }

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        count += 1;
    }

    public Cat(Double weight) {
        this.weight = weight;
        originWeight = this.weight;
        count += 1;
    }

    public void meow()
    {
        if (weight >= minWeight && weight <= maxWeight) {
            weight = weight - 1;
            System.out.println("Meow");
            if (weight < minWeight) {
                count -= 1;
                Objects.requireNonNull(this);
            }
        }

    }

    public void feed(Double amount)
    {
        if (weight >= minWeight && weight <= maxWeight) {
            weight = weight + amount;
            sumFeed += amount;
            if (weight > maxWeight) {
                count -= 1;
            }
        }
    }

    public void drink(Double amount)
    {
        if (weight >= minWeight && weight <= maxWeight) {
            weight = weight + amount;
            if (weight > maxWeight) {
                count -= 1;
            }
        }
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    public double getFeedMass() {
        return sumFeed;
    }

    public void pee(Double amount) {
        if (weight >= minWeight && weight <= maxWeight) {
            weight = weight - amount;
            System.out.println("KEK");
            if (weight < minWeight) {
                count -= 1;
            }
        }
    }

    public static int getCount() {
        return count;
    }
}