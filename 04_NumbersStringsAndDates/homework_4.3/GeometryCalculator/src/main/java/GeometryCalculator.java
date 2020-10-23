public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return (double) 4 / 3 * Math.PI * Math.abs(Math.pow(radius, 3));
    }

    public static boolean isTrianglePossible(double a, double b, double c) {
        double max = Math.max(a, Math.max(b, c));
        if (max == a) {
            if (max < b + c)
                return true;
            else return false;
        } else if (max == b) {
            if (max < a + c)
                return true;
            else return false;
        } else if (max < a + b)
            return true;
        else return false;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (isTrianglePossible(a, b, c)) {
            double p = (a + b + c) / 2;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        } else return -1;
    }
}
