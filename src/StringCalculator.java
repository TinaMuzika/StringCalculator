import java.util.Scanner;

public class StringCalculator {
    public static void main(String[] args) throws Exception {
        Scanner line = new Scanner(System.in);
        String text = line.nextLine();

        line.close();

        Calculate.operations(text);
    }


}
