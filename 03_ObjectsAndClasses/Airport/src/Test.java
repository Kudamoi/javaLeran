import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {

        Airport airport = Airport.getInstance();

        System.out.println(airport.getAllAircrafts());
        System.out.println(airport.getAllAircrafts().size() + " - количество самолетов в аэропорту");

        System.out.println(airport.getTerminals());
        System.out.println(airport.getTerminals().size());

        for (Terminal terminal : airport.getTerminals())
            terminal.getFlights()
                    .stream().filter(b -> {
                Date flight = b.getDate();
                Date date = new Date();
                if (flight.getHours() - date.getHours() <= 2 && flight.getHours() - date.getHours() >= 0)
                    return flight.getHours() == date.getHours() && flight.getMinutes() >= date.getMinutes() || flight.getHours() == date.getHours() + 2 && flight.getMinutes() <= date.getMinutes();
                return false;
            }).forEach(System.out::println);
    }

}
