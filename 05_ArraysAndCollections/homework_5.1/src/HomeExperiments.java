import java.util.Arrays;

public class HomeExperiments {
    public static void main(String[] args) {
        String test1 = "Каждый охотник желает знать, где сидит фазан.";
        flipArrayFromString(test1);

        patientTemperature();

        drawX();
    }

    public static String[] flipArrayFromString(String input) {
        System.out.println("Задание 5.1.1:");
        System.out.println("Строка на входе: " + input);

        String[] array = input.replaceAll("[.;]", "").split(",?\\s+");
        String[] arrayFlipped = new String[array.length];

        for (int indexArray = array.length - 1, indexArrayFlipped = 0; indexArray >= 0; indexArray--, indexArrayFlipped++)
            arrayFlipped[indexArrayFlipped] = array[indexArray];

        System.out.println("Массив на выходе: " + Arrays.toString(arrayFlipped));

        return arrayFlipped;
    }

    public static void patientTemperature() {
        System.out.println();
        System.out.println("Задание 5.1.2:");

        final int MIN_TEMPERATURE = 32;
        final int MAX_TEMPERATURE = 40;
        final float MIN_HEALTHY_TEMPERATURE = 36.2f;
        final float MAX_HEALTHY_TEMPERATURE = 36.9f;
        final int COUNT_PATIENT = 30;

        int countHealthyPatient = 0;
        float averageTemperature = 0;

        float[] temperaturePatient = new float[COUNT_PATIENT];
        for (int index = 0; index < temperaturePatient.length; index++) {
            temperaturePatient[index] = (float) (Math.floor(((Math.random() * (MAX_TEMPERATURE - MIN_TEMPERATURE)) + MIN_TEMPERATURE) * 10) / 10);
        }

        System.out.println("Температуры пациентов: " + Arrays.toString(temperaturePatient).replaceAll("\\[", "").replaceAll("]", ""));
        for (float patientTemperature : temperaturePatient) {
            if (MAX_HEALTHY_TEMPERATURE >= patientTemperature && MIN_HEALTHY_TEMPERATURE <= patientTemperature)
                countHealthyPatient += 1;
            averageTemperature += patientTemperature;
        }
        averageTemperature /= (float) COUNT_PATIENT;

        System.out.println(String.format("Средняя температура: %.2f", averageTemperature));
        System.out.println("Количество здоровых: " + countHealthyPatient);
    }

    public static void drawX() {
        System.out.println();
        System.out.println("Задание 5.1.3:");

        char[][] cross = new char[7][7];
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                if (Math.abs(i) == Math.abs(j)) {
                    cross[i + 3][j + 3] = 'X';
                } else {
                    cross[i + 3][j + 3] = ' ';
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            System.out.println(Arrays.toString(cross[i]).replaceAll("]", "").replaceAll("\\[", "").replaceAll(",", ""));
        }
    }

}
