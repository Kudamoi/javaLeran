public class Main {

  public static void main(String[] args) {

  }

  public static int calculateSalarySum(String text){
    //TODO: реализуйте метод
    int result = 0;
    String[] earnings = text.split("руб");
    for (int i = 0; i < earnings.length - 1; i++)
     result += Integer.parseInt(earnings[i].substring(earnings[i].trim().lastIndexOf(" ")).trim());

    return result;
  }

}