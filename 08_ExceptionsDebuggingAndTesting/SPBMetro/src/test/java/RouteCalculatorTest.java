import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RouteCalculatorTest extends TestCase {

    List<Station> route;

    StationIndex stationIndex = new StationIndex();

    Line line1 = new Line(1, "Первая");
    Line line2 = new Line(2, "Вторая");
    Line line3 = new Line(3, "Третья");
    Line line4 = new Line(4, "Четвертая");

    Station first = new Station("Первая", line1);
    Station second = new Station("Вторая", line1);
    Station third = new Station("Третья", line2);
    Station fourth = new Station("Втора", line2);
    Station fives = new Station("Четвертая", line2);
    Station sixth = new Station("Пята", line2);
    Station seventh = new Station("Шестая", line3);
    Station eights = new Station("Пятая", line3);
    Station nines = new Station("Седьмая", line3);
    Station ten = new Station("Десятая", line4);
    Station eleven = new Station("Одинадцатая", line4);
    Station twelve = new Station("Двенадцатая", line4);

    List<Station> connections1 = new ArrayList<Station>() {{
        add(second);
        add(fourth);
    }};

    List<Station> connections2 = new ArrayList<Station>() {{
        add(sixth);
        add(eights);
    }};

    @Override
    protected void setUp() throws Exception {

        route = new ArrayList<>();

        route.add(first);
        route.add(second);
        route.add(fourth);
        route.add(fives);
        route.add(sixth);
        route.add(eights);
        route.add(nines);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(first);
        stationIndex.addStation(second);
        stationIndex.addStation(third);
        stationIndex.addStation(fourth);
        stationIndex.addStation(fives);
        stationIndex.addStation(sixth);
        stationIndex.addStation(seventh);
        stationIndex.addStation(eights);
        stationIndex.addStation(nines);
        stationIndex.addStation(ten);
        stationIndex.addStation(eleven);
        stationIndex.addStation(twelve);

        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections2);

        line1.addStation(first);
        line1.addStation(second);
        line2.addStation(third);
        line2.addStation(fourth);
        line2.addStation(fives);
        line2.addStation(sixth);
        line3.addStation(seventh);
        line3.addStation(eights);
        line3.addStation(nines);

        line4.addStation(ten);
        line4.addStation(eleven);
        line4.addStation(twelve);

    }

    public void testCalculateDuration() {
        double excepted = 17;
        double actual = RouteCalculator.calculateDuration(route);
        assertEquals(excepted, actual);
    }

    public void testGetShortestRouteOnOneLine() {
        RouteCalculator test = new RouteCalculator(stationIndex);
        List<Station> excepted = new ArrayList<Station>() {{
            add(first);
            add(second);
        }};
        List<Station> actual = test.getShortestRoute(first, second);
        assertEquals(excepted, actual);
    }
    public void testGetShortestRouteOnTwoLine() {
        RouteCalculator test = new RouteCalculator(stationIndex);
        List<Station> excepted = new ArrayList<Station>() {{
            add(first);
            add(second);
            add(fourth);
            add(third);
        }};
        List<Station> actual = test.getShortestRoute(first, third);
        assertEquals(excepted, actual);
    }
    public void testGetShortestRouteWithoutConnection() {
        RouteCalculator test = new RouteCalculator(stationIndex);
        List<Station> excepted = new ArrayList<Station>() {{

        }};
        List<Station> actual = test.getShortestRoute(first, twelve);
        assertEquals(excepted, actual);
    }
    public void testGetShortestRouteOnOneLineTwo() {
        RouteCalculator test = new RouteCalculator(stationIndex);
        List<Station> excepted = new ArrayList<Station>() {{
            add(ten);
            add(eleven);
            add(twelve);
        }};
        List<Station> actual = test.getShortestRoute(ten, twelve);
        assertEquals(excepted, actual);
    }
    public void testGetShortestRouteOnThreeLine() {
        RouteCalculator test = new RouteCalculator(stationIndex);
        List<Station> excepted = new ArrayList<Station>() {{
           add(first);
           add(second);
           add(fourth);
           add(fives);
           add(sixth);
           add(eights);
           add(nines);
        }};
        List<Station> actual = test.getShortestRoute(first, nines);
        assertEquals(excepted, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
