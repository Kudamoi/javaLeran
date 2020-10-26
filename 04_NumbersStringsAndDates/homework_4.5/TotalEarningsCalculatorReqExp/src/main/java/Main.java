public class Main {

  public static void main(String[] args) {
calculateSalarySum("Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей");
  }

  public static int calculateSalarySum(String text){
    int result = 0;
    System.out.println(text);
    text = text.replaceAll("[^0-9^\\s]", "").trim();
    String[] earnings = text.split(" ");
    for (int i = 0; i < earnings.length; i++)
      if (!earnings[i].isEmpty())
        result += Integer.parseInt(earnings[i]);
    return result;
  }

}