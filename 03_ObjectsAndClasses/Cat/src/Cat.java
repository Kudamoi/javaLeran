import java.util.Objects;

public class Cat
{
    private double originWeight;
    private double weight;

    private double sumFeed;

    private String Color;

    public static int count;

    private static final Double MAX_WEIGHT = 9000.0;
    private static final Double MIN_WEIGHT = 1000.0;
    private static final int EYE = 2;

    private enum color { BLACK, GRAY, WHITE, RED }

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
        if (weight >= MIN_WEIGHT && weight <= MAX_WEIGHT) {
            weight = weight - 1;
            System.out.println("Meow");
            if (weight < MIN_WEIGHT) {
                count -= 1;
            }
        }

    }

    public void feed(Double amount)
    {
        if (weight >= MIN_WEIGHT && weight <= MAX_WEIGHT) {
            weight = weight + amount;
            sumFeed += amount;
            if (weight > MAX_WEIGHT) {
                count -= 1;
            }
        }
    }

    public void drink(Double amount)
    {
        if (weight >= MIN_WEIGHT && weight <= MAX_WEIGHT) {
            weight = weight + amount;
            if (weight > MAX_WEIGHT) {
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
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
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
        if (weight >= MIN_WEIGHT && weight <= MAX_WEIGHT) {
            weight = weight - amount;
            System.out.println("KEK");
            if (weight < MIN_WEIGHT) {
                count -= 1;
            }
        }
    }

    public static int getCount() {
        return count;
    }

    public Double getMaxWeight() {
        return MAX_WEIGHT;
    }

    public static Double getMinWeight() {
        return MIN_WEIGHT;
    }

    public double getOriginWeight() {
        return originWeight;
    }

    public static int getEYE() {
        return EYE;
    }

    public static void setCount(int count) {
        Cat.count = count;
    }
    public void setOriginWeight(double originWeight) {
        this.originWeight = originWeight;
    }
    public void setSumFeed(double sumFeed) {
        this.sumFeed = sumFeed;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return Color;
    }
    public void setColor(String Color) {
        boolean checkColor = true;
        try {
            color.valueOf(Color);
        } catch (Exception e) {
            checkColor = false;
            System.out.println("Такого цвета не существует введите другой");
        }
        if (checkColor == true)
            this.Color = Color;
    }
}