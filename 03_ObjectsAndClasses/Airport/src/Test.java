import com.skillbox.airport.Airport;

public class Test {

    public static void main(String[] args) {

        Airport airport = Airport.getInstance();

        System.out.println(airport.getAllAircrafts());
        System.out.println(airport.getAllAircrafts().size() + " - количество самолетов в аэропорту");

        System.out.println(airport.getTerminals());
        System.out.println(airport.getTerminals().size());

    }

}
